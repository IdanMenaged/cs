"""
Idan Menaged
"""

from scapy.layers.inet import IP, UDP
import time

from scapy.sendrecv import send

DST_IP = '127.0.0.1'
SRC_PORT = 1234  # source port
SLEEP_TIME = 0.1
EOM = '-'  # end of message

msg = input('enter message: ')
for ch in msg:
    port = ord(ch)
    packet = IP(dst=DST_IP) / UDP(sport=SRC_PORT, dport=port)
    send(packet)

    time.sleep(SLEEP_TIME)

send(
    IP(dst=DST_IP) / UDP(sport=SRC_PORT, dport=ord(EOM))
)
