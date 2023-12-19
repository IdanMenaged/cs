import socket
import random
import protocol
import methods
IP = "0.0.0.0"
PORT = 3333
MSG_LEN = 1024

def initiate_server_socket(ip, port):
    """
    Initializes and returns a server socket bound to the specified IP address and port,
    ready to accept incoming client connections.
    :param ip:
    :param port:
    """
    try:
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind((ip, port))
        server_socket.listen(1)
        return server_socket
    except socket.error as msg:
        print("socket error: ", msg)
    except Exception as msg:
        print("general error: ", msg)

def handle_clients(server_socket):
    """
    Listens for incoming client connection
    handle_single_client to manage client communication.
    :param server_socket:
    :return:
    """
    done = False
    try:
        while not done:
            client_socket, address = server_socket.accept()  # wait for new client
            done = handle_single_client(client_socket)  # mange client
        client_socket.close()
    except socket.error as msg:
        print("socket error: ", msg)
    except Exception as msg:
        print("general error: ", msg)

def receive_client_request(client_socket):
    """"
    Receives and decodes the client's
    request using the specified socket, returning the decoded data.
    """
    # receiving data
    data = protocol.recv(client_socket)
    # sending the same data
    data = data.encode()
    return data

def handle_client_request(request, client_socket):
    """
    Invokes the appropriate method from the methods module based on the client's request, sends the response back to the client, and returns
     a boolean indicating whether to continue handling the client.
    :param request:
    :param client_socket:
    :return:
    """
    try:
        response, done = getattr(methods, request)()
    except Exception as e:
        response = "command illegal"
        done = False
    send_response_to_client(response, client_socket)
    return done

def send_response_to_client(response,client_socket):
    """
    Encodes and sends the response string to the client using the socket.
    :param response: 
    :param client_socket: 
    :return: 
    """
    response_bytes = response.encode()
    protocol.send(client_socket,response_bytes)


def handle_single_client(client_socket):
    """
    Manages communication with a single client, handling
     their requests until done becomes True or an error occurs.
    :param client_socket:
    :return:
    """
    done = False
    try:
        while not done:
            request = receive_client_request(client_socket)
            done = handle_client_request(request, client_socket)
        client_socket.close()
    except socket.error as msg:
        print("socket error: ", msg)
    except Exception as msg:
        print("general error: ", msg)


def main():
    """
    initiates a server socket, listens for client
    connections, and handles clients using the handle_clients function.
    :return:
    """
    server_socket = initiate_server_socket(IP, PORT)
    handle_clients(server_socket)

if __name__ == '__main__':
    main()
