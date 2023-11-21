"""
    Pasta tester
    Ayelet Mashoah

    The below tests test copying source file to a destination directory using
    methods:
    1 - copying the file in on piece
    2 - copying the file in chunks.

    Note - before running the test the you should construct folders:
    DEST_DIR = "c:\\cyber\\cpypst\\"
    DEST_DIR_CHUNKS = "c:\\cyber\\cpypst-chunks\\"
    SOURCE_DIR = "c:\\cyber\\"
    and the files
    SOURCE_FILE_TEXT_SMALL = "txt_small.txt"
    SOURCE_FILE_TEXT_BIG =  "txt_big.txt"
    SOURCE_FILE_BIN = "bin.jpg"
    SOURCE_FILE_NO_EXISTS = "no_such_file"
"""
folder = "."
mods = ["pasta"]

import sys
import pytest
import importlib
import os

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

# copy paste files constants
ROOT_DIR = "c:\\cyber"
DEST_DIR = ROOT_DIR + "\\cpypst"
DEST_DIR_CHUNKS = ROOT_DIR + "\\cpypst-chunks"
DEST_NO_EXIST = "c:/blablabla"
SOURCE_DIR = ROOT_DIR
SOURCE_FILE_TEXT_SMALL = "txt_small.txt"
SOURCE_FILE_TEXT_BIG = "txt_big.txt"
SOURCE_FILE_BIN = "bin.jpg"
SOURCE_FILE_NO_EXISTS = "no_such_file"

print("testing: ", [m for m in mods])

if len(sys.argv) > PATH_WITH_FOLDER_MIN_LEN:
    folder = sys.argv[PATH_INDEX]
# import tested modukes
sys_path = sys.path
sys.path.insert(0, folder)
for m in mods:
    try:
        vars()[m] = importlib.import_module(m)
    except Exception as e:
        print("Faile to load module m", e)

sys.path = sys_path


##############################################################################
#                         IOHandler class                                    #
##############################################################################
class IOHandler():
    def __init__(self, mod, inputs, expected):
        self.mod = mod
        self.inputs = inputs
        self.output = []
        self.expected = expected
        # replacing print and input methods in correct_write
        mod.input = self.mock_input
        mod.print = self.mock_print

    def mock_input(self, s):
        """
        overrides input function in correct_write for testing sake
        """
        # self.output.append(s)
        # self.output.append("\n")
        return self.inputs.pop(0)

    def mock_print(self, s1, s2=None, s3=None, s4=None, s5=None, end='\n'):
        """
        overrides input function in correct_write for testing sake
        """
        self.output.append(str(s1))
        # print("s1 = ", s1, self.output)
        if s2 is not None:
            # print("s2 = ", s2, self.output)
            self.output.append(" " + str(s2))
        if s3 is not None:
            # print("s3 = ", s3, self.output)
            self.output.append(" " + str(s3))
        if s4 is not None:
            # print("s4 = ", s4, self.output)
            self.output.append(" " + str(s4))
        if s5 is not None:
            # print("s5 = ", s5, self.output)
            self.output.append(" " + str(s5))
        self.output.append(end)


#############################################################################
#                               File Operations                             #
############################################################################
def compare_files(f1_name, f2_name):
    """
    receives 2 file name and compare their content
    returns True if their content is the same amd Fa;s otherwis
    """
    content1 = b''
    content2 = b''
    try:
        f1 = open(f1_name, 'rb')
        f2 = open(f2_name, 'rb')
        content1 = f1.read()
        content2 = f2.read()
        f1.close()
        f2.close()
    except Exception as e:
        print("compare_files", e)
        return False
    return content1 == content2


def clean_dir(dir_name):
    """
    removes a directory neme and removes all files from it
    """
    files1remove = [os.path.join(dir_name, f) for f in os.listdir(dir_name)]
    for f in files1remove:
        os.remove(f)


##############################################################################
#                               tests                                        #
##############################################################################

def test_write_to_file():
    """
    tests the method write_to_file
    inputs: name of destination file, content
    output:
       None
    Tests if the file generated with the correct contenet
    """
    dest_file = DEST_DIR + "/" + "write2file.txt"
    txt = "Hello darkness, my old friend\n" + \
          "I've come to talk with you again\n" + \
          "Because a vision softly creeping\n" + \
          "Left its seeds while I was sleeping\n" + \
          "And the vision that was planted in my brain\n" + \
          "Still remains\n" + \
          "Within the sound of silence."
    try:
        pasta.write_to_file(dest_file, txt)
        with open(dest_file, 'r') as f:
            res_txt = f.read()
    except Exception as e:
        assert False, "exception in write_to_file " + str(e)
    assert txt == res_txt, \
        "written file should contain: " + txt + \
        " while it contains: " + res_txt


def test_write_to_file_no_exist():
    """
    tests the method write_to_file
    inputs: name of a none-existing file, content
    output:
       None
    Tests exception handling
    """
    dest_file = DEST_NO_EXIST + "/" + "write2file.txt"
    txt = "stam"
    try:
        pasta.write_to_file(dest_file, txt)
    except Exception as e:
        assert False, "exception in write_to_file " + str(e)
    assert True


def test_read_from_file():
    """
    tests the method read_from_file
    inputs: name of destination file
    output:
       destination file content
    tests if the correct content is printed
    """
    src_file = SOURCE_DIR + "/" + "txt_small.txt"
    expected_out = 'hello\nwwww\n'
    input_values = []
    io_handler = IOHandler(pasta, input_values, expected_out)

    try:
        pasta.read_from_file(src_file)
        out_str = "".join(io_handler.output)

    except Exception as e:
        assert False, "exception in read_from_file " + str(e)
    assert expected_out == out_str, \
        "printed text should be: " + expected_out + \
        " while it is: " + out_str


def test_read_from_file_no_exist():
    """
    tests the method read_fron_file
    inputs: name of a none-existing file
    output:
       None
    Tests exception handling
    """
    src_file = SOURCE_DIR + "/" + SOURCE_FILE_NO_EXISTS

    try:
        pasta.read_from_file(src_file)

    except Exception as e:
        assert False, "exception in read_from_file " + str(e)
    assert True


def test_read_3_lines_more_then_3_lines():
    """
    tests the method read_3_lines for a file withh more than 3 lines
    inputs: name of destination file
    output:
       destination file 3 first lines
    tests if the correct content is printed
    """
    src_file = SOURCE_DIR + "/" + "lines5.txt"
    expected_out = 'hello\nwwww\naa a\n'
    input_values = []
    io_handler = IOHandler(pasta, input_values, expected_out)

    try:
        pasta.read_3_lines(src_file)
        out_str = "".join(io_handler.output)

    except Exception as e:
        assert False, "exception in read_3_lines " + str(e)
    assert expected_out == out_str, \
        "printed text should be: " + expected_out + \
        " while it is: " + out_str


def test_read_3_lines_less_then_3_lines():
    """
    tests the method read_3_lines for a file with less than 3 lines
    inputs: name of destination file
    output:
       destination file all (2) lines
    tests if the correct content is printed
    """
    src_file = SOURCE_DIR + "/" + "txt_small.txt"
    expected_out = 'hello\nwwww'
    input_values = []
    io_handler = IOHandler(pasta, input_values, expected_out)

    try:
        pasta.read_3_lines(src_file)
        out_str = "".join(io_handler.output)

    except Exception as e:
        assert False, "exception in read_3_lines " + str(e)
    assert expected_out == out_str, \
        "printed text should be: " + expected_out + \
        " while it is: " + out_str


def test_read_3_lines_no_exist():
    """
    tests the method test_3_lines
    inputs: name of a none-existing file
    output:
       None
    Tests exception handling
    """
    src_file = SOURCE_DIR + "/" + SOURCE_FILE_NO_EXISTS

    try:
        pasta.read_3_lines(src_file)

    except Exception as e:
        assert False, "exception in read_3_lines " + str(e)
    assert True


def test_copy_paste_small_txt():
    """
    tests the method copy_paste
    inputs: name of a small text file, destination folder
    output:
       None
    Tests if the file was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_TEXT_SMALL
    dest_file = DEST_DIR + "/" + SOURCE_FILE_TEXT_SMALL
    try:
        pasta.copy_paste(source_file, DEST_DIR)
    except Exception as e:
        assert False, "exception in copy_pase " + str(e)
    assert compare_files(source_file, dest_file), \
        "small text files do not mach"


def test_copy_paste_big_txt():
    """
    tests the method copy_paste
    inputs: name of a big text file, destination folder
    output:
       None
    Tests if the fie was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_TEXT_BIG
    dest_file = DEST_DIR + "/" + SOURCE_FILE_TEXT_BIG
    try:
        pasta.copy_paste(source_file, DEST_DIR)
    except Exception as e:
        assert False, "exception in copy_pase " + str(e)
    assert compare_files(source_file, dest_file), "big text files do not mach"


def test_copy_paste_bin():
    """
    tests the method copy_paste
    inputs: name of a binary file, destinatio folder
    output:
       None
    Tests if the fie was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_BIN
    dest_file = DEST_DIR + "/" + SOURCE_FILE_BIN
    try:
        pasta.copy_paste(source_file, DEST_DIR)
    except Exception as e:
        assert False, "exception in copy_pase " + str(e)
    assert compare_files(source_file, dest_file), "small bin files do not mach"


def test_copy_paste_no_exist():
    """
    tests the method copy_paste
    inputs: name of a none-existing file, destination folder
    output:
       None
    Tests exception handling
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_NO_EXISTS
    try:
        pasta.copy_paste(source_file, DEST_DIR)
    except Exception as e:
        assert False, "exception in copy_pase " + str(e)
    assert True


def test_copy_paste_no_exist_folder():
    """
    tests the method copy_paste
    inputs: name of a none-existing file, destination folder
    output:
       None
    Tests exception handling
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_TEXT_SMALL
    destination = DEST_NO_EXIST
    try:
        pasta.copy_paste(source_file, destination)
    except Exception as e:
        assert False, "exception in copy_paste " + \
                      "destination foler does not exist" + str(e)
    assert True


def test_copy_paste_chunks_small_txt():
    """
    tests the method copy_paste
    inputs: name of a small text file, destinatio folder
    output:
       None
    Tests if the fie was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_TEXT_SMALL
    dest_file = DEST_DIR_CHUNKS + "/" + SOURCE_FILE_TEXT_SMALL
    try:
        pasta.copy_paste_chunks(source_file, DEST_DIR_CHUNKS)
    except Exception as e:
        assert False, "exception in copy_paste_chunks " + str(e)
    assert compare_files(source_file, dest_file), \
        "small text files do not mach"


def test_copy_paste_chunks_big_txt():
    """
    tests the method copy_paste
    inputs: name of a small text file, destinatio folder
    output:
       None
    Tests if the fie was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_TEXT_BIG
    dest_file = DEST_DIR_CHUNKS + "/" + SOURCE_FILE_TEXT_BIG
    try:
        pasta.copy_paste_chunks(source_file, DEST_DIR_CHUNKS)
    except Exception as e:
        assert False, "exception in copy_paste_chunks " + str(e)
    assert compare_files(source_file, dest_file), "big text files do not mach"


def test_copy_paste_chunks_bin():
    """
    tests the method copy_paste
    inputs: name of a binary file, destination folder
    output:
       None
    Tests if the fie was transferred to the folder
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_BIN
    dest_file = DEST_DIR_CHUNKS + "/" + SOURCE_FILE_BIN
    try:
        pasta.copy_paste_chunks(source_file, DEST_DIR_CHUNKS)
    except Exception as e:
        assert False, "exception in copy_pase_chunks " + str(e)
    assert compare_files(source_file, dest_file), "small bin files do not mach"


def test_copy_paste_chunks_no_exist():
    """
    tests the method copy_paste
    inputs: name of a none existing file, destination folder
    output:
       None
    Tests exception handling
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_NO_EXISTS
    try:
        pasta.copy_paste_chunks(source_file, DEST_DIR_CHUNKS)
    except Exception as e:
        assert False, "exception in copy_paste_chunks " + str(e)
    assert True


def test_copy_paste_chunks_no_exist_folder():
    """
    tests the method copy_paste
    inputs: name of a none existing file, destination folder
    output:
       None
    Tests exception handling
    """
    source_file = SOURCE_DIR + "\\" + SOURCE_FILE_NO_EXISTS
    destination = DEST_NO_EXIST
    try:
        pasta.copy_paste_chunks(source_file, destination)
    except Exception as e:
        assert False, "exception in copy_paste_chunks destination " + \
                      "does not exists" + str(e)
    assert True


def main():
    """
    calling pytest module for the above methods
    """
    # pytest.main(["_write - tst.py", "-v", "--tb=no"])
    clean_dir(DEST_DIR)
    clean_dir(DEST_DIR_CHUNKS)

    pytest.main(["pasta - tst.py"])


if __name__ == '__main__':
    main()
