import socket
import protocol
from constants import *
import methods
import os

SERVER_IP = '127.0.0.1'


def main():
    """
    take requests from a user until an 'exit' or 'quit' command
    send requests to server and print response
    """
    sock = init()

    while True:
        res = request(sock, input('please enter a request '))
        print(res)

        if res in EXIT_CODES:
            break

    sock.close()


def init():
    """
    create a new socket and connect it to the server
    :return: socket
    """
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((SERVER_IP, PORT))

    return sock


def request(sock, req):
    """
    send a request to the server and get a response
    :param sock: socket
    :param req: request
    :return: response
    """
    if not valid_request(req):
        return 'illegal request'

    try:
        protocol.send(sock, req)

        # determine protocol
        if req.split()[0] in BIN_METHODS:
            res = protocol.receive_bin(sock)
            if res == b'illegal command':
                res = res.decode()
        else:
            res = protocol.receive(sock)

        # special exception
        if req.split()[0] == 'send_file' and res != 'illegal command':
            methods.save_to_file(os.path.join(SAVE_FILE_TO, os.path.basename(req.split()[1])), res)
            res = 'file sent'
        elif req == 'reload':
            res = handle_reload(sock)
    except:
        res = 'quit'

    return res


def valid_request(req):
    """
    check if a request is valid (i.e. exists on the server and has the correct number of params)
    :return: true if valid, else false
    """
    cmd, *params = req.split()
    return cmd in PARAM_COUNTS.keys() and len(params) == PARAM_COUNTS[cmd]


def handle_reload(sock):
    """
    to be called whenever a user wants to reload
    sends the server the contents of 'methods.py' and get a response
    :param sock: socket
    :return: server response
    """
    content = methods.send_file(METHODS_PATH)
    protocol.send_bin(sock, content)
    return protocol.receive(sock)


if __name__ == '__main__':
    main()
