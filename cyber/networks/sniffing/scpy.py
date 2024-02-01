from scapy.all import *
from scapy.layers.inet import IP

N_SNIFFS = 5


def main():
    my_sniffs()


def include_http(p):
    """
    checks if a packet uses the http protocol
    :param p: packet
    :return: true if p uses http, else false
    """
    return Raw in p and p[Raw].load[:3] == b'GET'


def print_url_n_host(p):
    """
    print the ip to which a packet is sent
    :param p: an http packet
    """
    print(p[IP].dst)


def my_sniffs():
    """
    sniff N_SNIFFS http packets and print their destination ips
    """
    sniff(count=N_SNIFFS, lfilter=include_http, prn=print_url_n_host)


if __name__ == '__main__':
    main()
