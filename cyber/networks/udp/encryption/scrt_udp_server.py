"""
Idan Menaged
"""
from scapy.layers.inet import UDP
from scapy.sendrecv import sniff

SNIFF_COUNT = 1
EOM = '-'  # end of message

msg = ''


def udp_filter(packet):
    """
    :param packet: packet sent
    :return: is packet udp?
    """
    return UDP in packet


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
