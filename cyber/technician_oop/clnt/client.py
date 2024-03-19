"""
Idan Menaged
"""

import socket
import sys
from technician_oop.srvr.protocol import Protocol
from technician_oop.srvr.constants import *

IP = '127.0.0.1'


def main():
    """
    constructs a client and runs it
    """
    client = Client(IP, PORT)
    client.handle_user_input()


class Client:
    def __init__(self, ip, port):
        try:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.sock.connect((ip, port))
        except socket.error as msg:
            print(f'Connection failure: {msg}\nterminating program')
            sys.exit(1)

    @staticmethod
    def valid_request(req):
        """
        :param req: request
        :return: is req valid
        """
        cmd, *params = req.split()
        return cmd in PARAM_COUNTS.keys() and len(params) == PARAM_COUNTS[cmd]

    def send_request_to_server(self, req):
        """
        :param req: request
        sends req to server
        """
        Protocol.send(self.sock, req)

    def handle_server_response(self):
        """
        recv and handle server response
        :return: response
        """
        res = Protocol.receive(self.sock)
        return res

    def handle_user_input(self):
        """
        recv and send requests to server
        """
        req = ''
        while req not in EXIT_CODES:
            req = input('please enter a request ')

            if not self.valid_request(req):
                print('illegal request')
            else:
                self.send_request_to_server(req)
                res = self.handle_server_response()
                print(res)


if __name__ == '__main__':
    main()
