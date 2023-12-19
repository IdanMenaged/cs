MSG_LEN_BYTE_SIZE = 4  # the number of bytes where the msg length is stored


def send(socket, data):
    encoded_data = data.encode()
    l = len(encoded_data)
    ll = str(l).zfill(MSG_LEN_BYTE_SIZE)
    llll = ll.encode()
    socket.send(llll + encoded_data)


def recv(socket):
    # data_size_str = b""
    # i = 0
    # while i < FIRSTLOOPSIZE:
    #     data_receive = socket.recv()
    #     data_size_str += data_receive
    #     i += len(data_receive)
    #
    # data_size = int(data_size_str.decode())
    # data = b""
    # while data_size > 0:
    #     data_receive = socket.recv(data_size)
    #     data += data_receive
    #     data_size -= len(data_receive)
    #
    # return data.decode()
    size = int(socket.recv(MSG_LEN_BYTE_SIZE).decode())

    tot_data = b''
    while int(size) > 0:
        data = socket.recv(size)
        size -= len(data)
        tot_data += data
