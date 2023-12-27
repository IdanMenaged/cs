import socket
import protocol
from constants import *
import methods
import os

SERVER_IP = '127.0.0.1'


def main():
    sock = init()
    print('starting client')

    while True:
        res = request(sock, input('enter cmd: '))
        print(f'received res {res}')

        if res in EXIT_CODES:
            break

    sock.close()


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((SERVER_IP, PORT))

    return sock


def request(sock, req):
    protocol.send(sock, req)
    print('sending req')

    # determine protocol
    if req.split()[0] in BIN_METHODS:
        res = protocol.receive_bin(sock)
    else:
        res = protocol.receive(sock)

    # special exceptions
    if req.split()[0] == 'send_file':
        res = methods.save_to_file(os.path.join(SAVE_FILE_TO, req.split()[1]), res)
    return res


if __name__ == '__main__':
    main()
