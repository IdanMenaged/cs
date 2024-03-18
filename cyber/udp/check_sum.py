"""
Idan Menaged
"""


def main():
    print(checksum('idan', calculate_checksum('IDAN')))


def calculate_checksum(st):
    """
    :param st: a string
    :return: sum of ascii values
    """
    sum = 0
    for c in st:
        sum += ord(c)
    return sum


def checksum(data, num):
    """
    :param data: a string with the data
    :param num: the supposed checksum
    :return: does the checksum of data equal num
    """
    return calculate_checksum(data) == num


if __name__ == '__main__':
    main()
