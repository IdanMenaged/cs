import datetime
import socket

IP = '127.0.0.1'
PORT = 40
MSG_LEN = 1024


def main():
    try:
        server_socket = initiate_server_socket(IP, PORT)
        while True:
            handle_clients(server_socket)
        # server_socket.close()
    except socket.error as msg:
        print(f'socket error: {msg}')
    except Exception as msg:
        print(f'general error: ', msg)


def initiate_server_socket(ip, port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((ip, port))
    server_socket.listen(1)
    return server_socket


def handle_clients(server_socket):
    client_socket, address = server_socket.accept()
    done = handle_single_client(client_socket)
    client_socket.close()


def handle_single_client(client_socket):
    request = client_socket.recv(MSG_LEN)

    while request.decode() != '' and request.decode().upper() != 'EXIT':
        if request.decode().upper() == 'TIME':
            # client_socket.send(datetime.time())

    if request.decode() == 'EXIT':
        return True
    return False


if __name__ == '__main__':
    main()
