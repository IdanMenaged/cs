import socket
import protocol
import methods
from constants import *
import importlib
import sys

IP = '0.0.0.0'
SIM_USERS = 1  # n of simultaneous users


def main():
    sock = init()
    handle_clients(sock)
    sock.close()


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((IP, PORT))
    sock.listen(SIM_USERS)

    return sock


def handle_clients(sock):
    while True:
        should_exit = handle_client(sock)

        if should_exit:
            break


def handle_client(server_socket):
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
