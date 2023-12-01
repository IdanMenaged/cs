"""
Idan Menaged
"""

import socket

IP = '127.0.0.1'
PORT = 1234
MSG_LEN = 1024


def main():
    try:
        client_socket = initiate_client_socket()
        handle_user_input(client_socket)
        client_socket.close()
    except socket.error as err:
        print('socket error: ', err)
    except Exception as err:
        print('general error: ', err)


def initiate_client_socket(ip=IP, port=PORT):
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


def handle_user_input(my_socket):
    """
    takes input from user, sends it to the server, deals with the response, repeats
    :param my_socket: socket for communication with server
    """
    req = input("please enter a request ")
    while req.lower() != 'exit':
        res = talk_to_server(my_socket, req)

        print('print bytes: ', res)
        print("print string: ", res.decode())

        req = input("please enter a request ")


if __name__ == '__main__':
    main()
