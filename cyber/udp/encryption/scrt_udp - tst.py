"""
    udp test - for windows
    tests udp server-client and checksum
    Ayelet Mashiah
"""
folder = "."

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

# termination constatnts
TERMINATION_SLEEP_TIME = 1


if len(sys.argv) > PATH_WITH_FOLDER_MIN_LEN:
    folder = sys.argv[PATH_INDEX]

# management constants
SERVER_OUTPUT = "srvr_out.txt"
CLIENT_INPUT = "clnt_input.txt"
CLIENT_OUTPUT = "clnt_output.txt"
EXECUTE_RES_FILE = folder + "\\exe_out.txt"
SLEEP_BEFORE_TERMINATION = 20
INPUT_STRING = "please enter a mesaage "


# is alive constants
class IsAliveStates:
    SERVER_ON = 1
    SERVER_OFF = 2
    CLIENT_ON = 4
    CLIENT_OFF = 8

##############################################################################
#                           testing utilities                                #
##############################################################################


class CD:
    """ Context manager for changing the current working directory """
    def __init__(self, new_path):
        """ constructor """
        self.new_path = new_path
        self.saved_path = ''

    def __enter__(self):
        """ keep current dir and change dir """
        self.saved_path = os.getcwd()
        os.chdir(self.new_path)

    def __exit__(self, etype, value, traceback):
        """ return to old dir """
        os.chdir(self.saved_path)


def clear_io_data():
    """ clears standart input and output files"""
    with CD(folder):
        if os.path.exists(SERVER_OUTPUT):
            os.remove(SERVER_OUTPUT)
        if os.path.exists(CLIENT_INPUT):
            os.remove(CLIENT_INPUT)
        if os.path.exists(CLIENT_OUTPUT):
            os.remove(CLIENT_OUTPUT)
        if os.path.exists(EXECUTE_RES_FILE):
            os.remove(EXECUTE_RES_FILE)


class ProcessHandler():
    """
    process generator object
    """
    def __init__(self, name, out_f_name):
        """ contructor - keeps name and opens stdout """
        self.name = name
        self.output_f_name = folder + "\\" + out_f_name
        self.output_f = open(self.output_f_name, "w")
        self.input_f = None
        self.runner = None

    def close(self):
        """ terminates procees and opens input and output file """
        print("closing", self.name)
        self.output_f.flush()
        time.sleep(1)
        self.output_f.close()
        if self.runner is not None:
            self.runner.terminate()
        if self.input_f is not None:
            self.input_f.close()

    def is_alive(self):
        """ tests whether the process is alive """
        time.sleep(TERMINATION_SLEEP_TIME)
        # to let the process die peacefully :)
        on = self.runner is not None and psutil.pid_exists(self.runner.pid)
        return on

    def run(self, my_env=None):
        """ runs the process """
        print("starting", self.name)
        cmd = ["python", folder + "\\" + self.name + ".py"]
        # print("my env", my_env)
        self.runner = subprocess.Popen(cmd, stdout=self.output_f,
                                       stdin=self.input_f, env=my_env)

    def get_output(self):
        """ returns process' output """
        out_f = open(self.output_f_name, "r")
        o = out_f.read()
        out_f.close()
        return o.split("\n")


class ServerHandler(ProcessHandler):
    """
    server's handler
    """
    def __init__(self, out_f_name):
        super(ServerHandler, self).__init__("scrt_udp_server", out_f_name)


class ClientHandler(ProcessHandler):
    """
    client handler
    """
    def __init__(self, out_f_name):
        """ constructor """
        super(ClientHandler, self).__init__("scrt_udp_client", out_f_name)

    def add_command(self, command):
        """ adds a single command to client's input """
        self. input_f.write(command + "\n")

    def generate_client_command(self, inp_name, commands):
        """ generates client's expected command - stdin """
        clnt_input_f_name = folder + "\\" + inp_name
        self.input_f = open(clnt_input_f_name, "w")
        for cmd in commands:
            self.add_command(cmd)
        self.input_f.close()
        self.input_f = open(clnt_input_f_name, "r")


##############################################################################
#                       Session Runners                                      #
##############################################################################

class Runner():
    """
    base class - runs a test session od the server and the client
    """
    def __init__(self, test_name, commands, expected,
                 outuut_file_name='', big_file=False):
        """
        constructor - generate test data
        keeps: command list, expected output,
                test name and output file nam
        """
        self.commands = commands
        self.expected = expected
        self.name = test_name
        self.res_file = outuut_file_name
        self.server = None
        self.client = None

    def run(self):
        """ runs the session: server  and client """
        self.server = ServerHandler(SERVER_OUTPUT)
        self.client = ClientHandler(CLIENT_OUTPUT)
        self.client.generate_client_command(CLIENT_INPUT, self.commands)

        self.server.run()
        self.client.run()

        time.sleep(SLEEP_BEFORE_TERMINATION)
        self.client.close()
        self.server.close()

    def check_results(self):
        """ check actual output vs expected one """
        # print(client.get_output()[0])
        o = self.server.get_output()[:-1]
        # print(o)
        actual = ''
        for res in o:
            res = res.replace(INPUT_STRING, "").lower()
            actual += res + '\n'
        actual = actual[:-1]
        # print(st1, "\n", st2)
        # for i in range(len(st1)):
        #    print(st1[i], st2[i], st1[i] == st2[i])

        file_msg = ''
        ok = True
        if self.res_file != '':
            ok = os.path.getsize(self.res_file) > 0
            file_msg = " and " + self.res_file + \
                       " might be empty or not exist "

        assert self.expected == actual and ok, "test " + self.name  \
            + ": excpected = " + self.expected \
            + " actual = " + actual \
            + file_msg


def test_encrypt():
    """
    tests single command
    input: tralala
    output
    tralala
    """
    test_runner = Runner("encrypt", ["tralala"], "tralala")

    test_runner.run()
    test_runner.check_results()


def main():
    """
    running client server tests
    """
    # detailed output
    pytest.main(["scrt_udp - tst.py"])
    # test_encrypt()
    # clear_io_data()

    # test_single_command()

    # summery
    # pytest.main(["technician_server - tst.py", "-v", "--tb=no"])


if __name__ == '__main__':
    main()
