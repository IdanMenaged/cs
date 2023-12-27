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
    print('starting server')
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
    print('client accepted')

    while True:
        req = protocol.receive(client_socket).lower()
        print('msg received')

        res = handle_req(req)

        if req.split()[0] in BIN_METHODS:
            protocol.send_bin(client_socket, res)
        else:
            protocol.send(client_socket, res)
        print('msg sent')

        if res in EXIT_CODES:
            break

    client_socket.close()
    return res == 'exit'


def handle_req(req):
    try:
        cmd, *params = req.split()
        res = getattr(methods, cmd)(*params)

        # special exception
        if cmd == 'reload':
            res = on_reload()

        return res

    except AttributeError or ValueError:
        return 'illegal request'


def on_reload():
    importlib.reload(sys.modules[__name__])
    return 'reloaded'


if __name__ == '__main__':
    main()
