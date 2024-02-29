folder = "."
mods = ["open_ports"]
import sys
import pytest
import os
import importlib
import socket

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

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

    def mock_print(self,s1, s2 = None, s3 = None, s4 = None, s5 = None):
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
        self.output.append("\n")




##############################################################################
#                               tests                                        #
##############################################################################
def test_ip():
    """
    tests the method test_all_ports
    inputs: url - ynet's ip
    output: port 80 heidad
    """
    expected_out = 'port 80 heidad\n'
    input_values = []
    io_handler = IOHandler(open_ports, input_values, expected_out)

    open_ports.test_all_ports(socket.gethostbyname("www.ynet.co.il"), 78, 82)
    out_str = "".join(io_handler.output)

    # output assertion
    # print("out list: ", io_handler.output)

    assert out_str ==  expected_out,  "Your output is incorrect, should be:\n" + expected_out + "\nwhile it is:\n" + out_str

def test_url():
    """
    tests the method test_all_ports
    inputs: url - "www.google.com"
    output: port 80 heidad
    """
    expected_out = 'port 80 heidad\n'
    input_values = []
    io_handler = IOHandler(open_ports, input_values, expected_out)

    open_ports.test_all_ports("www.google.com", 78, 82)
    out_str = "".join(io_handler.output)

    # output assertion
    # print("out list: ", io_handler.output)

    assert out_str ==  expected_out,  "Your output is incorrect, should be:\n" + expected_out + "\nwhile it is:\n" + out_str



def main():
    """
    calling pytest module for the above methods
    """
    # print(socket.gethostbyname("www.ynet.co.il"))
    # pytest.main(["correct_write - tst.py", "-v", "--tb=no"])

    pytest.main(["open_ports - tst.py"])


if __name__ == '__main__':
    main()