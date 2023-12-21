from PIL import ImageGrab


def main():
    take_screenshot()


def take_screenshot():
    im = ImageGrab.grab(xdisplay=':0')  # :0 means the first monitor
    return im


def send_file(file_path):
    with open(file_path, 'rb') as file:
        content = file.read()
    return content.decode()


if __name__ == '__main__':
    main()
