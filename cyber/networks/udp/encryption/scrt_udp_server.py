"""
Idan Menaged
"""
from scapy.layers.inet import UDP, IP
from scapy.sendrecv import sniff

SNIFF_COUNT = 1
EOM = '-'  # end of message

msg = ''


def udp_filter(packet):
    """
    :param packet: packet sent
    :return: is packet udp?
    """
    if UDP in packet and IP in packet and packet[IP].src == packet[IP].dst:
        print(packet[IP].src)

    return UDP in packet and IP in packet and packet[IP].src == '0.0.0.0' and packet[IP].dst == '0.0.0.0'


def add_to_message(packet):
    """
    add a character to msg based on the packet port
    :param packet: packet sent
    """
    global msg
    c = packet[UDP].dport
    ch = chr(c)
    msg += ch


while True:
    sniff(count=SNIFF_COUNT, lfilter=udp_filter, prn=add_to_message)
    if msg[-1] == EOM:
        msg = msg[:-1]
        break
    print(msg)

print(msg)
