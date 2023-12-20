MSG_LEN_PADDING = 4  # n of bytes to put in front of the content to show it's len


def send(socket, content):
    """
    :param socket: comm socket
    :param content: string with the content to be sent
    :return: None
    """
    content = content.encode()  # convert to bytes
    content_len = len(content)
    content_len = str(content_len).zfill(MSG_LEN_PADDING).encode()  # zfill and convert to bytes
    content = content_len + content  # join the len in front of the content

    socket.send(content)


def receive(socket):
    """
    :param socket: comm socket
    :return: string with content
    """
    content_len = int(socket.recv(MSG_LEN_PADDING).decode())  # might want to put in a loop
    len_received = 0
    content = ""
    while len_received < content_len:
        packet = socket.recv(content_len)
        len_received += len(packet)
        content += packet.decode()
    return content
