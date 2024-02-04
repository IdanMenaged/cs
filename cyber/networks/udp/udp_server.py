"""
Idan Menaged
"""

import socket

IP = '0.0.0.0'
PORT = 1234
DATA_SIZE = 1024


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp/ip
    sock.bind((IP, PORT))

    done = False
    while not done:
        try:
            # recv
            data, addr = sock.recvfrom(DATA_SIZE)

            # send
            sock.sendto(data, addr)
        except socket.error:
            # destroy socket and create a new one to replace it
            sock.close()
            sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp/ip
            sock.bind((IP, PORT))
        except:
            done = True

    sock.close()


if __name__ == '__main__':
    main()
