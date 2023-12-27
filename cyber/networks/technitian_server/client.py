import socket
import protocol
from constants import *

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
    res = protocol.receive_bin(sock)
    return res


if __name__ == '__main__':
    main()
