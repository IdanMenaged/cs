from PIL import ImageGrab
import glob

SCREENSHOT_PATH = r'c:\technitian_server\screenshot.png'


def main():
    dir(r'c:\technitian_server')


def take_screenshot():
    im = ImageGrab.grab()  # :0 means the first monitor
    im.save(SCREENSHOT_PATH)
    return 'screenshot taken'


def send_file(file_path):
    with open(file_path, 'rb') as file:
        content = file.read()
    return content.decode()


def dir(path):
    return str(glob.glob(rf'{path}\*.*'))


def quit():
    return 'quit'


def exit():
    return 'exit'


if __name__ == '__main__':
    main()
