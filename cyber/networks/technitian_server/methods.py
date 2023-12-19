import numpy as np
import cv2
import pyautogui


def main():
    take_screenshot()


def take_screenshot():
    image = pyautogui.screenshot()
    image = cv2.cvtColor(np.array(image), cv2.COLOR_RGB2BGR)   # convert from PIL to numpy array
    cv2.imwrite('screenshot.png', image)


def send_file(socket, file_path):
    with open(file_path, 'rb') as file:
        content = file.read()


if __name__ == '__main__':
    main()
