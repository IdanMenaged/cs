"""
Idan Menaged
"""

import socket

IP = '0.0.0.0'
PORT = 40
MSG_LEN = 1024


def main():
    try:
        my_socket = initiate_client_socket(IP, PORT)

        handle_user_input(my_socket)
        my_socket.close()
    except socket.error:
        print("server is down")


def initiate_client_socket(ip, port):
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_socket.connect((ip, port))
    return my_socket


def handle_user_input(my_socket):
    msg = input("please enter a request ")
    while msg.upper() != 'EXIT':
        my_socket.send(msg.encode())
        data = my_socket.recv(MSG_LEN)
        print("print bytes: ", data)
        print("print string: ", data.decode())

        msg = input("please enter a request ")


if __name__ == '__main__':
    main()
