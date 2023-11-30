"""
Idan Menaged
"""

import socket

IP = '0.0.0.0'
PORT = 1234
MSG_LEN = 1024
SIMULTANEOUS_CLIENTS = 1


def main():
    try:
        server_socket = init_server()
        handle_clients(server_socket)
        server_socket.close()
    except socket.error as err:
        print('socket error: ', err)
    except Exception as err:
        print("general error: ", err)


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


def handle_clients(server_socket):
    """
    handles multiple clients
    :param server_socket: socket for the server
    """
    while True:
        client_socket, address = server_socket.accept()
        handle_client(client_socket)


def handle_client(client_socket):
    """
    accept req from client and send/receive data
    :param client_socket: socket for the client
    :return: True if server is to be terminated (aka client sent 'exit')
    """
    data = client_socket.recv(MSG_LEN)
    while data.decode() != '' and data.decode().lower() != 'exit':
        client_socket.send(data)
        data = client_socket.recv(MSG_LEN)

    client_socket.close()


if __name__ == '__main__':
    main()
