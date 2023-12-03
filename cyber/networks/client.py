"""
Idan Menaged
"""

import socket

IP = '127.0.0.1'
PORT = 1234
MSG_LEN = 1024
VALID_REQUESTS = {'time', 'name', 'rand', 'quit', 'exit'}


def main():
    try:
        client_socket = initiate_client_socket()
        handle_user_input(client_socket)
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


def valid_request(request):
    """
    checks if a request is a valid one
    :return: true if valid, otherwise false
    """
    return request.lower() in VALID_REQUESTS


def send_request_to_server(my_socket, request):
    """
    sends a request to the server
    :param my_socket: socket for communication with server
    :param request: string representing the request
    """
    my_socket.send(request.encode())


def handle_server_response(my_socket):
    """
    receives server response and prints it as bytes and as string
    :param my_socket: socket for communication with client
    """
    res = my_socket.recv(MSG_LEN)

    print('print bytes: ', res)
    print('print string: ', res.decode())


def handle_user_input(my_socket):
    """
    takes input from user, sends it to the server, deals with the response, repeats
    :param my_socket: socket for communication with server
    """
    req = input("please enter a request ").lower()
    while req != 'exit' and req != 'quit':
        if valid_request(req):
            send_request_to_server(my_socket, req)
            handle_server_response(my_socket)
        else:
            print('illegal request')

        req = input("please enter a request ").lower()

    my_socket.close()


if __name__ == '__main__':
    main()
