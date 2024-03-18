"""
    contacts test
    Ayelet Mashoah
"""
folder = "."
mods = ["scpy"]

import sys
import pytest
import importlib
import urllib.request
import socket
import threading
import time

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

# wait before senging get messages
WAIT_4_SCAPY = 5

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
    """
    handles user input and output
    clollects holds lists of inputs and outputs which can be compared
    to the expected outputs for testin
    """
    def __init__(self, mod, inputs):
        """
        constructor - initiating inputs outputs and expected output
        overriding module add to module print and input methods
        which overrides general print and input methods
        """
        self.mod = mod
        self.inputs = inputs
        self.output = []
        # replacing print and input methods in correct_write
        mod.input = self.mock_input
        mod.print = self.mock_print

    def mock_input(self, s):
        """
        overrides input function for testing sake
        """
        # self.output.append(s)
        # self.output.append("\n")
        return self.inputs.pop(0)

    def mock_print(self, s1, s2=None, s3=None, s4=None, s5=None, end="\n"):
        """
        overrides print function testing sake
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


##############################################################################
#                               tests                                        #
##############################################################################

def simulate_get():
    """ simulates 5 get from moo moo site"""
    time.sleep(WAIT_4_SCAPY)
    urllib.request.urlopen("http://games.moomoo.co.il/")
    urllib.request.urlopen("http://www.moomoo.co.il/fld/gifts.php")
    urllib.request.urlopen("http://www.moomoo.co.il/fld/dolls.php")
    urllib.request.urlopen("http://www.moomoo.co.il/fld/index.php")
    urllib.request.urlopen("http://www.moomoo.co.il/fld/ice.php")


def test_my_sniffs():
    """
    tests the method my_sniffs for the following urls
    inputs: http://games.moomoo.co.il/
    http://polls.moomoo.co.il/
    http://www.moomoo.co.il/fld/dolls.php
    http://www.moomoo.co.il/fld/ice.php
    output:
        158.106.133.146 -- /
        158.106.133.146 -- /fld/gifts.php
        158.106.133.146 -- /fld/dolls.php
        158.106.133.146 -- /fld/index.php
        158.106.133.146 -- /fld/ice.php

    Note: the ip is the ip of http://www.moomoo.co.il/
    which is retrieved at the beginning of the test

    """

    io_handler = IOHandler(scpy, [])
    moo_moo_ip = socket.gethostbyname("www.moomoo.co.il")

    expected_out = moo_moo_ip + " -- /\n" + \
        moo_moo_ip + " -- /fld/gifts.php\n" + \
        moo_moo_ip + " -- /fld/dolls.php\n" + \
        moo_moo_ip + " -- /fld/index.php\n" + \
        moo_moo_ip + " -- /fld/ice.php\n"

    gets = threading.Thread(target=simulate_get)
    gets.start()
    scpy.my_sniffs()

    output = "".join(io_handler.output)

    assert expected_out == output, \
        "expected output is:\n" + \
        expected_out + "\nwhile it is:\n" + output


def main():
    """
    calling pytest module for the above method
    """
    # pytest.main(["scpy - tst.py", "-v", "--tb=no"])

    pytest.main(["scpy - tst.py"])

if __name__ == '__main__':
    main()
