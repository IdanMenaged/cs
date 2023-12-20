import socket
import protocol

IP = '0.0.0.0'
PORT = 4000
SIM_USERS = 1  # n of simultaneous users


def main():
    sock = init()
    print('starting server')
    handle_client(sock)


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((IP, PORT))
    sock.listen(SIM_USERS)

    return sock


def handle_client(server_socket):
    client_socket, addr = server_socket.accept()
    print('client accepted')

    content = protocol.receive(client_socket)
    print('msg received')
    protocol.send(client_socket, content)
    print('msg sent')


if __name__ == '__main__':
    main()
