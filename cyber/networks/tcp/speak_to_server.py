"""
Idan Menaged
"""

from scapy.layers.inet import IP, TCP
from scapy.sendrecv import sr1, send


def speak_with_server(seq, url):
	"""
	commence syc-ack
	:param seq: starting sequence number
	:param url: url to contact
	"""
	packet = IP(dst=url) / TCP(seq=seq)
	res = sr1(packet)
	seq, ack = res.ack, res.seq + 1
	packet = IP(dst=url) / TCP(seq=seq, flags='A', ack=ack)
	send(packet)
