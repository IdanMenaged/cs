import socket

IP = '127.0.0.1'
PORT = 1234


def main():
    init_client()


def init_client(ip=IP, port=PORT):
    """
    create a socket and connect to server
    :return: socket
    """
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_socket.connect((ip, port))

    return my_socket


if __name__ == '__main__':
    main()