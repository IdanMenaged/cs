"""
    trace route  test - for windows
    tests trace route
    Ayelet Mashiah
"""
folder = "."
mods = ["trace_route"]

# EXECUTE_RUNNABLE = "notepad"
# check_execute_result = None

import sys
import os
import subprocess
import psutil
import time
import pytest
import shutil
import importlib

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

# termination constatnts..........
TERMINATION_SLEEP_TIME = 1
DOTS_COUNT = 10
TIME_OUT = "." * DOTS_COUNT

if len(sys.argv) > PATH_WITH_FOLDER_MIN_LEN:
    folder = sys.argv[PATH_INDEX]

sys_path = sys.path
sys.path.insert(0, folder)
for m in mods:
    try:
        vars()[m] = importlib.import_module(m)
    except Exception as e:
        print("Faile to load module m", e)

sys.path = sys_path



INPUT_STRING = "please enter url"
TIME_OUT_INDICATION = ".........."
MAX_BYTE = 255

##############################################################################
#                           testing utilities                                #
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


def is_correct_ip(ip):
    """ is ip legal """
    if ip == TIME_OUT_INDICATION:
        return True
    nums = ip.split('.')

    return all(n.isdigit() and int(n) <= MAX_BYTE for n in nums)

def test_trace_route():
    """
    tests one trace result
    input: "www.ynet.co.il"
    output: rout list
    """

    input_values = []

    io_handler = IOHandler(trace_route, input_values, "")
    trace_route.trace_rt("www.ynet.co.il")
    actual_out = "".join(io_handler.output).split()

    ok = all(is_correct_ip(ip) for ip in actual_out) and len(actual_out) > 0

    assert ok, "returned ip list is illegal: " + str(actual_out)


def main():
    """
    running client server tests
    """
    # detailed output

    # test_trace_route()
    pytest.main(["trace_route - tst.py"])


    # test_single_command()

    # summery
    # pytest.main(["technician_server - tst.py", "-v", "--tb=no"])

    #############################
    #       single tests        #
    #############################

    # server = ServerHandler("srvr_out.txt")
    # client = ClientHandler("clnt_out.txt", ".txt")
    #
    # server.run()
    # client.run()
    #
    # time.sleep(5)
    #
    # server.close()
    # client.close()
    # test_dir()

    # test_take_screenshot()
    # test_take_screenshot_with_parameter()
    # test_dir()
    # test_delete_2_params()
    # test_execute()
    # test_execute_no_exist()
    # test_send_file()
    # test_few_command()
    # test_quit()
    # test_kill_server()
    # clear_io_data()
    # print(client.test_results())


if __name__ == '__main__':
    main()
