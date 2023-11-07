"""
    wordcount test
    Ayelet Mashoah

    to run this test you have to construct a folder c:\\cyber and put
    alice-chapter-1.txt in it
"""
folder = "."
mods = ["wordcount"]

import sys
import pytest
import importlib

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

# wordcount constants
FILE_NAME = "c:\\cyber\\alice-chapter-1.txt"


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
def test_print_top():
    """
    tests the method print_top
    inputs:
        file name (full path)
    output:
        None
    prints the 20 most common words in the given file
    """

    expected_out1 = "107 the\n80 she\n77 to\n68 and\n64 it\n59 a\n54 was\n" + \
        "52 of\n36 alice\n33 i\n32 in\n27 her\n26 that\n25 but\n" + \
        "24 very\n24 down\n23 for\n22 you\n21 had\n19 not\n"

    expected_out2 = "107 the\n80 she\n77 to\n68 and\n64 it\n59 a\n54 was\n" +\
        "52 of\n36 alice\n33 i\n32 in\n27 her\n26 that\n25 but\n" +\
        "24 down\n24 very\n23 for\n22 you\n21 had\n19 not\n"

    io_habdler = IOHandler(wordcount, [])

    wordcount.print_top(FILE_NAME)

    output = "".join(io_habdler.output)
    assert expected_out1 == output or expected_out2 == output, \
        "expected output is:\n" + expected_out1 + \
        "or:\n" + expected_out2 + \
        "while it is:\n" + output


def test_main_top_count():
    """
    test main with flag top_count

    changes system argv before calling main
    and rollbacks the change after the call

    input:
        givin in argv
    output:
        none
    printout the 20 most common words in the given file
    """

    expected_out1 = "107 the\n80 she\n77 to\n68 and\n64 it\n59 a\n54 was\n" +\
        "52 of\n36 alice\n33 i\n32 in\n27 her\n26 that\n25 but\n" +\
        "24 very\n24 down\n23 for\n22 you\n21 had\n19 not\n"

    expected_out2 = "107 the\n80 she\n77 to\n68 and\n64 it\n59 a\n54 was\n" +\
        "52 of\n36 alice\n33 i\n32 in\n27 her\n26 that\n25 but\n" +\
        "24 down\n24 very\n23 for\n22 you\n21 had\n19 not\n"

    io_habdler = IOHandler(wordcount, [])

    sys_argv = sys.argv
    sys.argv = [sys_argv[0], "--topcount", FILE_NAME]

    wordcount.main()

    sys.argv = sys_argv

    output = "".join(io_habdler.output)
    assert expected_out1 == output or expected_out2 == output, \
        "expected output is:\n" + expected_out1 + \
        "or:\n" + expected_out2 + \
        "while it is:\n" + output


def main():
    """
    calling pytest module for the above methods
    """
    # pytest.main(["wordcount - tst.py", "-v", "--tb=no"])

    pytest.main(["wordcount - tst.py"])


if __name__ == '__main__':
    main()
