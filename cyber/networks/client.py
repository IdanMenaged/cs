"""
Idan Menaged
"""

import socket

IP = '127.0.0.1'
PORT = 1234
MSG_LEN = 1024


def main():
    server_socket = init_client()
    res = talk_to_server(server_socket, 'Homer Simpson')
    print('print bytes: ', res)
    print("print string: ", res.decode())
    server_socket.close()


def init_client(ip=IP, port=PORT):
    f"""
    create a socket and connect to server
    :param ip: ip, defaults to {IP}
    :param port: port, defaults to {PORT}
    :return: socket
    """
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_socket.connect((ip, port))

    return my_socket


def talk_to_server(server_socket, msg):
    """
    sends a message to the server and receives a response
    :param server_socket: socket to talk to
    :param msg: message to send
    :return: server response
    """
    server_socket.send(msg.encode())
    return server_socket.recv(MSG_LEN)


if __name__ == '__main__':
    main()