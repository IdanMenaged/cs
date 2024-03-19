"""
Idan Menaged
"""
from scapy.layers.inet import IP, ICMP
from scapy.sendrecv import sr1


def main():
    send_ping('google.com')


def send_ping(target):
    """
    send ping request to target and print response
    :param target: either ip or url of target
    """
    req = IP(dst=target) / ICMP()
    res = sr1(req)
    res.show()


if __name__ == '__main__':
    main()
