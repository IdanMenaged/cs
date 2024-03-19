"""
Idan Menaged
"""

from scapy.layers.inet import IP, ICMP
from scapy.sendrecv import sr1

ECHO_REPLY = 0
TIMEOUT = 10


def main():
	"""
	trace route to google.com
	"""
	trace_rt('google.com')


def send_ping(target, ttl):
	"""
	ping target and print response
	:param target: either url or ip of target
	:param ttl: time to live. n of hops packet is allowed to make.
	:return: response
	"""
	req = IP(dst=target, ttl=ttl) / ICMP()
	res = sr1(req, timeout=TIMEOUT, verbose=0)
	if res is None:
		print("..........")
	else:
		print(res[IP].src)
		return res


def trace_rt(target):
	"""
	print ips of computers on the route to target
	:param target: either url or ip of target
	"""
	ttl = 1
	while True:
		res = send_ping(target, ttl)

		if res is None or res[ICMP].type == ECHO_REPLY:
			break

		ttl += 1


if __name__ == '__main__':
	main()
