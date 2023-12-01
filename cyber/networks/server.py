"""
Idan Menaged
"""
import datetime
import random
import socket
import time

IP = '0.0.0.0'
PORT = 1234
MSG_LEN = 1024
SIMULTANEOUS_CLIENTS = 1
SERVER_NAME = "idan's amazing server"
RAND_RANGE = (1, 11)


def main():
    try:
        server_socket = initiate_server_socket()
        handle_clients(server_socket)
        server_socket.close()
    except socket.error as err:
        print('socket error: ', err)
    except Exception as err:
        print("general error: ", err)


def initiate_server_socket(ip=IP, port=PORT):
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
        handle_single_client(client_socket)


def handle_single_client(client_socket):
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


def send_time(client_socket):
    """
    sends client the current date and time
    :param client_socket: client socket
    """
    client_socket.send(str(datetime.datetime.now()).encode())


def send_name(client_socket):
    """
    sends the name of the server
    :param client_socket: client socket
    """
    client_socket.send(SERVER_NAME)


def send_rand(client_socket):
    """
    sends a random number between 1 and 10
    :param client_socket: client socket
    """
    client_socket.send(random.randrange(RAND_RANGE[0], RAND_RANGE[1]))


def quit_client_connection(client_socket):
    """
    ends connection with client and waits for another connection
    :param client_socket: client socket
    """
    client_socket.close()


def exit_server(server_socket, client_socket):
    """
    ends connection with client and also terminate the server
    :param server_socket: the socket handling the whole server
    :param client_socket: the socket handling a specific connection
    """
    client_socket.close()
    server_socket.close()


if __name__ == '__main__':
    main()
