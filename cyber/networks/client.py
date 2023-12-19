import socket
import protocol
import methods
IP = "127.0.0.1"
PORT = 3333
MSG_LEN = 1024


def initiate_client_socket(ip, port):
    """
    Initializes and returns a client socket connected to the specified IP address and port
    """
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_socket.connect((IP, PORT))
    return my_socket


def handle_user_input(my_socket):
    """
accepts user input for a request, checks its validity, sends it to the server
 and prints the server's response.
 """
    try:
        request = input('please enter a request ')
        request = request.upper()
        if valid_request(request):
            send_request_to_server(my_socket, request)
            handle_server_response(my_socket)
        else:
            print("illegal request")
        my_socket.close()
    except socket.error as msg:
        print("socket error: ", msg)
        my_socket.close()


def valid_request(request):
    """

    Checks if the user's request is valid (TIME, NAME, RAND, EXIT, or QUIT)
    and returns a boolean indicating validity.
    """
    done = False
    if request == "TIME":
        done = True
    elif request == "NAME":
        done = True
    elif request == "RAND":
        done = True
    elif request.upper() == "EXIT":
        done = True
    elif request.upper() == "QUIT":
        done = True
    return done


def send_request_to_server(my_socket, request):
    """

    Encodes and sends
    the user's request to the server using the socket.
    """
    protocol.send(my_socket, request.encode())


def handle_server_response(my_socket):
    """
    receives and decodes the server's response
     from the specified socket and prints the response string.
    """
    response = protocol.recv(my_socket)
    print("string: ", response.decode())


def main():
    """
   initiates a client socket, handles user input
     and prints errors.
    :return:
    """
    my_socket = initiate_client_socket(IP, PORT)
    try:
        handle_user_input(my_socket)
    except socket.error as e:
        print("Socket error")
    except Exception as e:
        print("Error")


if __name__ == '__main__':
    main()
