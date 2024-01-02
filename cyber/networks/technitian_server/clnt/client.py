import socket
import protocol
from constants import *
import methods
import os

SERVER_IP = '127.0.0.1'


def main():
    sock = init()

    while True:
        res = request(sock, input('please enter a request '))
        print(res)

        if res in EXIT_CODES:
            break

    sock.close()


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((SERVER_IP, PORT))

    return sock


def request(sock, req):
    if not valid_request(req):
        return 'illegal request'

    protocol.send(sock, req)

    # determine protocol
    if req.split()[0] in BIN_METHODS:
        res = protocol.receive_bin(sock)
    else:
        res = protocol.receive(sock)

    # special exception
    if req.split()[0] == 'send_file':
        methods.save_to_file(os.path.join(SAVE_FILE_TO, os.path.basename(req.split()[1])), res)
        # methods.save_to_file(os.path.join(SAVE_FILE_TO, req.split()[1], res))
        res = 'file sent'
    elif req == 'reload':
        res = handle_reload(sock)
    return res


def valid_request(req):
    cmd, *params = req.split()
    return cmd in PARAM_COUNTS.keys() and len(params) == PARAM_COUNTS[cmd]


def handle_reload(sock):
    content = methods.send_file(METHODS_PATH)
    protocol.send_bin(sock, content)
    return protocol.receive(sock)


if __name__ == '__main__':
    main()
