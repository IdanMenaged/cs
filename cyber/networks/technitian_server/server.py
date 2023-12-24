import socket
import protocol
import methods

IP = '0.0.0.0'
PORT = 4000
SIM_USERS = 1  # n of simultaneous users
EXIT_CODES = {'quit', 'exit', 'illegal request'}


def main():
    sock = init()
    print('starting server')
    handle_clients(sock)
    sock.close()


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((IP, PORT))
    sock.listen(SIM_USERS)

    return sock


def handle_clients(sock):
    while True:
        should_exit = handle_client(sock)

        if should_exit:
            break


def handle_client(server_socket):
    client_socket, addr = server_socket.accept()
    print('client accepted')

    while True:
        req = protocol.receive(client_socket)
        print('msg received')

        res = handle_req(req)

        protocol.send(client_socket, res)
        print('msg sent')

        if res in EXIT_CODES:
            break

    client_socket.close()
    return res == 'exit'


def handle_req(req):
    req = req.lower()
    try:
        cmd, *params = req.split()
        return getattr(methods, cmd)(*params)
    except AttributeError or ValueError:
        return 'illegal request'


if __name__ == '__main__':
    main()
