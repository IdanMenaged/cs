"""
Idan Menaged
"""

import socket
import sys
from protocol import Protocol
from constants import *
from methods import Methods

IP = '0.0.0.0'
SIM_USERS = 1


def main():
    """
    construct a server and run it
    """
    server = Server(IP, PORT)
    server.handle_clients()


class Server:
    def __init__(self, ip, port):
        try:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.sock.bind((ip, port))
            self.sock.listen(SIM_USERS)
        except socket.error as msg:
            print(f'Connection failure {msg}\nterminating program')
            sys.exit(1)

    def handle_clients(self):
        """
        handle clients until an 'exit' code is sent
        """
        while True:
            should_exit = self.handle_client()

            if should_exit:
                break

    def handle_client(self):
        """
        handle a single client and send them a response based on their request
        :return: should server terminate?
        """
        client_socket, addr = self.sock.accept()

        while True:
            req = Protocol.receive(client_socket).lower()

            res = self.handle_req(client_socket, req)

            if req.split()[0] in BIN_METHODS:
                Protocol.send_bin(client_socket, res)
            else:
                Protocol.send(client_socket, res)

            if res in EXIT_CODES:
                break

        client_socket.close()
        return res == 'exit'

    @staticmethod
    def handle_req(client_socket, req):
        """
        determines a response based on a request
        :param client_socket: client socket
        :param req: request
        :return: response
        """
        cmd, *params = req.split()

        # special exception
        if cmd == 'reload':
            res = Methods.handle_reload(client_socket)
        else:
            try:
                res = getattr(Methods, cmd)(*params)
            except:
                if cmd in BIN_METHODS:
                    res = b'illegal command'
                else:
                    res = 'illegal command'

        return res


if __name__ == '__main__':
    main()
