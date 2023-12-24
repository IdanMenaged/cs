import socket
import protocol
import methods

IP = '0.0.0.0'
PORT = 4000
SIM_USERS = 1  # n of simultaneous users


def main():
    sock = init()
    print('starting server')
    handle_client(sock)
    sock.close()


def init():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((IP, PORT))
    sock.listen(SIM_USERS)

    return sock


def handle_client(server_socket):
    client_socket, addr = server_socket.accept()
    print('client accepted')

    req = protocol.receive(client_socket)
    print('msg received')

    res = handle_req(req)
    protocol.send(client_socket, res)
    print('msg sent')

    client_socket.close()


def handle_req(req):
    req = req.lower()
    cmd, *params = req.split()
    return getattr(methods, cmd)(*params)


if __name__ == '__main__':
    main()
