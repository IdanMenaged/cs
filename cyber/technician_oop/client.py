"""
Idan Menaged
"""

import socket
import sys
import protocol

IP = '127.0.0.1'
PORT = 4000
PARAM_COUNTS = {
    'take_screenshot': 0,
    'send_file': 1,
    'dir': 1,
    'delete': 1,
    'copy': 2,
    'execute': 1,
    'echo': 1,
    'quit': 0,
    'exit': 0,
    'reload': 0
}
EXIT_CODES = {'quit', 'exit'}


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
        protocol.send(self.sock, req)

    def handle_server_response(self):
        """
        recv and handle server response
        :return: response
        """
        res = protocol.receive(self.sock)
        return res

    def handle_user_input(self):
        """
        recv and send requests to server
        """
        req = ''
        while req not in EXIT_CODES:
            req = input('enter request: ')

            if not self.valid_request(req):
                print(f'illegal request: {req}')
            else:
                self.send_request_to_server(req)
                res = self.handle_server_response()
                print(f'response: {res}')


if __name__ == '__main__':
    main()
