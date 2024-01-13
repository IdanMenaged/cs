import socket

IP = '0.0.0.0'
PORT = 80
SOCKET_TIMEOUT = 1
MSG_SIZE = 1024
WEBROOT = r'C:\cyber\webroot'
EOL = '\r\n'  # end of line
DEFAULT_RES = WEBROOT + r'\index.html'

EXTENSION_TO_TYPE = {
    'html': 'text/html; charset=utf-8',
    'txt': 'text/html; charset=utf-8',
    'jpg': 'image/jpeg',
    'ico': 'image/jpeg',
    'gif': 'image/jpeg',
    'png': 'image/jpeg',
    'css': 'text/css',
    'js': 'text/javascript; charset=utf-8'
}
# convert between old file names and the updated files
REDIRECTION_DICTIONARY = {
    '/js/box1.js': '/js/box.js',
    '/imgs/abstract1.jpg': '/imgs/abstract.jpg'
}


def main():
    """
    server main - receives a message returns it to
    client
    """
    try:
        server_socket = initiate_server_socket()
        handle_clients(server_socket)
        server_socket.close()
    except socket.error as msg:
        print("socket failure: ", msg)
    except Exception as msg:
        print("exception: ", msg)


def initiate_server_socket():
    """
    start a server socket
    :return: socket
    """
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((IP, PORT))
    server_socket.listen(10)
    print("Listening for connections on port %d" % PORT)
    return server_socket


def handle_clients(server_socket):
    """ loop forever while waiting for clients """
    client_socket = None
    while True:
        try:
            client_socket, client_address = server_socket.accept()
            print('New connection received')
            client_socket.settimeout(SOCKET_TIMEOUT)
            handle_client(client_socket)
        except socket.error as e:
            print("client failed", e)
        client_socket.close()


def handle_client(client_socket):
    """
    handle a client until connection is closed
    """
    print('Client connected')
    done = False
    while not done:
        raw_client_request = client_socket.recv(MSG_SIZE)
        client_request = raw_client_request.decode()
        done = (client_request == '')
        if not done:
            status, resource = validate_http_request(client_request)
            handle_client_request(resource, client_socket, status)
        else:
            print('connection closed')
    print('Closing connection')
    client_socket.close()


def validate_http_request(request):
    """
    validates the http request:
    1- starts with GET
    2 - ends with HTTP/1.1
    3 - holds a valid resource name between both of them
    returns a tuple -
    status code
    retrieved resource name
    """
    # look only at the first line
    request = request.splitlines()[0]

    # check request type and protocol version
    if request.split()[0] != 'GET' or request.split()[-1] != 'HTTP/1.1':
        return '500 Server Internal Error', None

    # check for default
    if request.split()[1] == '/':
        return '200 OK', DEFAULT_RES

    # check if it is a moved resource
    if request.split()[1] in REDIRECTION_DICTIONARY.keys():
        return '302 Moved Temporarily', request.split()[1]

    # check if is a function
    if 'calculate-next' in request.split()[1]:
        num = request.split()[1].split('num=')[1]
        return '200 OK', f'calc-next {num}'
    if 'calculate-area' in request.split()[1]:
        h = request.split()[1].split('height=')[1].split('&')[0]
        w = request.split()[1].split('width=')[1]
        return '200 OK', f'calc-area {h} {w}'

    # check path
    path = WEBROOT + request.split()[1].replace('/', '\\')
    try:
        with open(path):
            return '200 OK', path
    except FileNotFoundError:
        return '404 Not Found', None
    except:
        return '500 Server Internal Error', None


def file_request(path):
    """
    given the path, get the content and content type of the file
    """
    with open(path, 'rb') as res:
        return res.read(), EXTENSION_TO_TYPE[path.split('.')[-1]]


def handle_client_request(resource, client_socket, status):
    """
    given resource, socket, and status, handle and send the correct response
    """
    if status == '200 OK':
        print('requested resource: ' + resource)

        # check for functions
        if 'calc-next' in resource or 'calc-area' in resource:
            func, *params = resource.split()
            try:
                params = [int(param) for param in params]  # convert params to int
            except ValueError:
                # invalid params
                client_socket.send(f'HTTP/1.1 500 Server Internal Error{EOL}{EOL}'.encode())
                return
            if func == 'calc-next':
                data = calc_next(*params)
                content_type = 'text/plain'
            elif func == 'calc-area':
                data = calc_area(*params)
                content_type = 'text/plain'
        else:
            # retrieve file content and content type
            data, content_type = file_request(resource)
        data_size = str(len(data))
        # finally constructing header
        http_header = 'HTTP/1.1 ' + "200 OK " + EOL + 'Content-Length: ' + data_size + EOL + 'Content-Type: ' +\
                      content_type + EOL + EOL
        print(http_header)
        http_response = http_header.encode() + data
    elif status == '302 Moved Temporarily':
        http_response = f'HTTP/1.1 {status}{EOL}Location: {REDIRECTION_DICTIONARY[resource]}{EOL}{EOL}'.encode()
    else:
        http_response = f'HTTP/1.1 {status}{EOL}{EOL}'.encode()
    client_socket.send(http_response)


# functions to be called from the url


def calc_next(x):
    """
    calc the next number
    """
    return str(x + 1).encode()


def calc_area(w, h):
    """
    calc the area of a triangle of dimensions w, h
    """
    return str((w * h) / 2).encode()


if __name__ == '__main__':
    main()
