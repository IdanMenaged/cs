import socket
import protocol
import methods
from constants import *

IP = '0.0.0.0'
SIM_USERS = 1  # n of simultaneous users


def main():
    """
    create a server socket and handle client requests until a 'quit' or 'exit'
    """
    sock = init()
    handle_clients(sock)
    sock.close()


def init():
    """
    create a server socket and bind it to IP:PORT
    :return: socket
    """
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((IP, PORT))
    sock.listen(SIM_USERS)

    return sock


def handle_clients(sock):
    """
    handle clients until an 'exit' code is sent
    """
    while True:
        should_exit = handle_client(sock)

        if should_exit:
            break


def handle_client(server_socket):
    """
    handle a single client and send them a response based on their request
    :return: should server terminate?
    """
    client_socket, addr = server_socket.accept()

    while True:
        req = protocol.receive(client_socket).lower()

        res = handle_req(client_socket, req)

        if req.split()[0] in BIN_METHODS:
            protocol.send_bin(client_socket, res)
        else:
            protocol.send(client_socket, res)

        if res in EXIT_CODES:
            break

    client_socket.close()
    return res == 'exit'


def handle_req(sock, req):
    """
    determines a response based on a request
    :param sock: socket
    :param req: request
    :return: response
    """
    cmd, *params = req.split()

    # special exception
    if cmd == 'reload':
        res = methods.handle_reload(sock)
    else:
        try:
            res = getattr(methods, cmd)(*params)
        except:
            if cmd in BIN_METHODS:
                res = b'illegal command'
            else:
                res = 'illegal command'

    return res


if __name__ == '__main__':
    main()
