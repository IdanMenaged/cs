from PIL import ImageGrab
import os

SCREENSHOT_PATH = os.path.join('res', 'screenshot.png')


def main():
    take_screenshot()


def take_screenshot():
    im = ImageGrab.grab(xdisplay=':0')  # :0 means the first monitor
    im.save(SCREENSHOT_PATH)
    return 'screenshot taken'


def send_file(file_path):
    with open(file_path, 'rb') as file:
        content = file.read()
    return content.decode()


if __name__ == '__main__':
    main()
