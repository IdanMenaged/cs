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
ILLEGAL_COMMAND_CODE = 'illegal command'


def main():
    server_socket = initiate_server_socket()
    handle_clients(server_socket)


# server operation functions
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
    done = False
    while not done:
        client_socket, address = server_socket.accept()
        done = handle_single_client(client_socket)
    server_socket.close()


def handle_single_client(client_socket):
    """
    accept req from client and send/receive data
    :param client_socket: socket for the client
    :return: True if server is to be terminated (aka client sent 'exit')
    """
    req = 'a'
    try:
        while req != '' and req != 'exit':
            request = receive_client_request(client_socket).lower()
            response = handle_client_request(request).lower()
            send_response_to_client(response, client_socket)
    except socket.error as err:
        print('socket error: ', err)
    except Exception as err:
        print('general error: ', err)

    return req == 'exit'


def receive_client_request(client_socket):
    """
    receives a request from the client
    :param client_socket: socket for communication with client
    :return: a string representing client request
    """
    return client_socket.recv(MSG_LEN).decode()


def handle_client_request(request):
    """
    creates a response appropriate to the request
    :param request: a string representing client's request
    :return: a string representing server response
    """
    response = ''
    match request.lower():
        case 'time':
            response = TIME()
        case 'name':
            response = NAME()
        case 'rand':
            response = RAND()
        case 'quit':
            response = QUIT()
        case 'exit':
            response = EXIT()
        case _:
            response = ILLEGAL_COMMAND_CODE
    return response


def send_response_to_client(response, client_socket):
    """
    sends a response to the client
    :param response: string representing response to send
    :param client_socket: socket for communication with client
    """
    client_socket.send(response.encode())


# command functions
def TIME():
    """
    gets the current time
    :return: string representing the date and time
    """
    return str(datetime.datetime.now())


def NAME():
    """
    get server's name
    """
    return SERVER_NAME


def RAND():
    """
    gets number between 1 and 10
    :return: random number
    """
    return str(random.randrange(RAND_RANGE[0], RAND_RANGE[1]))


def QUIT():
    """
    create a string that will cause connection to quit if sent to send_response_to_client
    :return: 'quit'
    """
    return 'quit'


def EXIT():
    """
    create a string that will cause server to terminate if sent to send_response_to_client
    :return: 'exit'
    """
    return 'exit'


if __name__ == '__main__':
    main()
