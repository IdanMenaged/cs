import importlib
import sys

from PIL import ImageGrab
import glob
import os
import shutil
import subprocess
from constants import *
from networks.technitian_server.srvr import protocol

SCREENSHOT_PATH = 'c:\\test_folder\\server\\screen.jpg'
FILE_PATH = 'c:\\test_folder\\client'


def main():
    pass


def take_screenshot():
    im = ImageGrab.grab()
    im.save(SCREENSHOT_PATH)
    return 'screenshot takenn'


def send_file(file_path):
    with open(file_path, 'rb') as file:
        content = file.read()
    return content


def dir(path):
    return str(glob.glob(rf'{path}\*.*'))


def delete(path):
    os.remove(path)
    return 'file deleted'


def copy(origin, destination):
    shutil.copy(origin, destination)
    return 'file copied'


def execute(program):
    subprocess.call(program)
    return 'program executed'


def echo(msg):
    return msg


def quit():
    return 'quit'


def exit():
    return 'exit'


def handle_reload(sock):
    protocol.send(sock, 'ready for reload')
    data = protocol.receive_bin(sock)
    save_to_file(METHODS_PATH, data)

    importlib.reload(sys.modules[__name__])
    return 'module reloaded'


def save_to_file(path, content):
    with open(path, 'wb') as file:
        file.write(content)
    return f'saved to {path}'


if __name__ == '__main__':
    main()
