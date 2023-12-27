from PIL import ImageGrab
import glob
import os
import shutil
import subprocess
import protocol

SCREENSHOT_PATH = r'c:\technitian_server\screenshot.png'
FILE_PATH = r'c:\technitian_server'


def main():
    pass


def take_screenshot():
    im = ImageGrab.grab()  # :0 means the first monitor
    im.save(SCREENSHOT_PATH)
    return 'screenshot taken'


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


def reload():
    send_file('.')
    return 'sent updated file'


def echo(msg):
    return msg


def quit():
    return 'quit'


def exit():
    return 'exit'


def save_to_file(path, content):
    with open(path, 'wb') as file:
        file.write(content)
    return f'saved to {path}'


if __name__ == '__main__':
    main()
