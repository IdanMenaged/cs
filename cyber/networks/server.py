"""
Idan Menaged
"""

import socket

IP = '0.0.0.0'
PORT = 1234
MSG_LEN = 1024
SIMULTANEOUS_CLIENTS = 1


def main():
    pass


def init_server(ip=IP, port=PORT):
    f"""
    creates a socket for the server and listen for incoming requests
    :param ip: ip, defaults to {IP}
    :param port: port, defaults to {PORT}
    """
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_socket.bind((ip, port))
    my_socket.listen(SIMULTANEOUS_CLIENTS)

    return my_socket


if __name__ == '__main__':
    main()