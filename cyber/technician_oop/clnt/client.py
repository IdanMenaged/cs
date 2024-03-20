"""
Idan Menaged
"""
import os.path
import socket
import sys
from technician_oop.srvr.protocol import Protocol
from technician_oop.srvr.constants import *
from methods import Methods
import methods

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

    def handle_server_response(self, req):
        """
        recv and handle server response
        :param req: request
        :return: response
        """
        if req.split()[0] in BIN_METHODS:
            res = Protocol.receive_bin(self.sock)
        else:
            res = Protocol.receive(self.sock)

        if req.split()[0] == 'send_file':
            base_name = os.path.basename(req.split()[1])
            save_to = os.path.join(methods.FILE_PATH, base_name)
            res = Methods.save_to_file(save_to, res)

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
                res = self.handle_server_response(req)
                print(res)


if __name__ == '__main__':
    main()
