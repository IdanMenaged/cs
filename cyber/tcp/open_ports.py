"""
Idan Menaged
"""

from scapy.layers.inet import IP, TCP
from scapy.sendrecv import sr1

TIMEOUT = 1


def test_all_ports(ip, min_port, max_port):
	"""
	print all open ports
	:param ip: ip to contact
	:param min_port: minimum port number
	:param max_port: max port number
	"""
	for i in range(min_port, max_port):
		packet = IP(dst=ip) / TCP(dport=i)
		res = sr1(packet, timeout=TIMEOUT, verbose=False)

		if res is not None:
			print(f'port {i} heidad')
