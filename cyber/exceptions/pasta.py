"""
Idan Menaged
"""

CHUNK = 1024


def main():
	copy_paste_chunks(r'Untitled Document 1.txt', 'destination_fold')


def read_from_file(path):
	"""
	print contents of a text file
	:param path:  the path to said file
	:return:  None
	"""
	try:
		with open(path, 'rb') as file:
			for line in file:
				print(line, end='')
	except IOError:
		pass
				

def write_to_file(path, content):
	"""
	writes text into a file, discards the previous text
	:param path:  path to a file
	:param content:  text to be inserted in the file
	:return: None
	"""
	with open(path, 'wb') as file:
		file.write(content)


def read_3_lines(path):
	"""
	read the first 3 lines (or less if there are less than 3)
	:param path: path to file
	:return: None
	"""
	try:
		with open(path, 'rb') as file:
			for i, line in enumerate(file):
				if i >= 3:
					break
				print(line, end='')
	except IOError:
		pass


def generate_path(dir, file_name):
	"""
	:param dir: path to the directory
	:param file_name: name of the file
	:return: path to file_name in dir
	"""
	return rf'{dir}\{file_name}'


def copy_paste(origin, destination):
	"""
	copies a file to a given location
	:param origin: path to a file
	:param destination: path to the folder to which the file will be
	copied
	:return: None
	"""
	file_name = origin.split('\\')[-1]   # only on windows, not linux
	try:
		with open(origin, 'rb') as origin_file:
			with open(generate_path(destination, file_name), 'wb') as \
					destination_file:  # once again, only windows :,(
				destination_file.write(origin_file.read())
	except IOError:
		pass


def copy_paste_chunks(origin, destination):
	"""
	copies a file to a given location one chunk at a time
	:param origin: path to a file
	:param destination: path to the folder to which the file will be
	copied
	:return: None
	"""
	file_name = origin.split('\\')[-1]  # only on windows, not linux
	try:
		with open(origin, 'rb') as origin_file:
			with open(generate_path(destination, file_name), 'wb') as \
					destination_file:  # once again, only windows :,(
				while True:
					chunk = origin_file.read(CHUNK)
					if not chunk:
						break
					destination_file.write(chunk)
	except IOError:
		pass


if __name__ == '__main__':
	main()
