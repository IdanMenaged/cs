"""
Idan Menaged
"""

import socket

IP = '127.0.0.1'
PORT = 1234
DATA_SIZE = 1024


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp/ip

    req = input('please enter message: ')
    while req != 'exit':
        # send
        sock.sendto(req.encode(), (IP, PORT))

        # recv
        raw_data, addr = sock.recvfrom(DATA_SIZE)

        # print
        print(f'The server sent: {raw_data.decode()}')

        req = input('please enter message: ')

    sock.close()


if __name__ == '__main__':
    main()
