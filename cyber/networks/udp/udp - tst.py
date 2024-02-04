"""
    udp test - for windows
    tests udp server-client and checksum
    Ayelet Mashiah
"""
folder = "."
mods = ["check_sum"]

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

# termination constatnts
TERMINATION_SLEEP_TIME = 1


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

# management constants
SERVER_OUTPUT = "srvr_out.txt"
CLIENT_INPUT = "clnt_input.txt"
CLIENT_OUTPUT = "clnt_output.txt"
EXECUTE_RES_FILE = folder + "\\exe_out.txt"
SLEEP_BEFORE_TERMINATION = 1
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
        print("my env", my_env)
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
        super(ServerHandler, self).__init__("udp_server", out_f_name)


class ClientHandler(ProcessHandler):
    """
    client handler
    """
    def __init__(self, out_f_name):
        """ constructor """
        super(ClientHandler, self).__init__("udp_client", out_f_name)

    def add_command(self, command):
        """ adds a single command to client's input """
        self. input_f.write(command + "\n")

    def generate_client_command(self, inp_name, commands):
        """ generates client's expected command - stdin """
        clnt_input_f_name = folder + "\\" + inp_name
        self.input_f = open(clnt_input_f_name, "w")
        for cmd in commands:
            self.add_command(cmd)
        self.add_command("exit\n")
        self.input_f.close()
        self.input_f = open(clnt_input_f_name, "r")


class StreamClientHandler(ClientHandler):
    """
    handles a clien session reading its stdout and stderr
    """
    def __init__(self, out_f_name):
        """ constructor """
        super(StreamClientHandler, self).__init__(out_f_name)
        self.command_string = b''

    def generate_client_command(self, inp_name, commands):
        """ generates command string out of commands list """
        if len(commands) == 0:
            return
        self.command_string = "\n".join(commands).encode() + b"\n"
        # self.add_command("quit\n")

    def send_command(self, cmd):
        """ runs a single commands """
        self.runner.stdin.write(cmd.encode() + b"\n")
        self.runner.stdin.flush()

    def run(self):
        """ runs the client process """
        print("starting", self.name)
        cmd = ["python", folder + "\\" + self.name + ".py"]
        self.runner = subprocess.Popen(cmd, stdout=self.output_f,
                                       stdin=subprocess.PIPE,
                                       stderr=subprocess.PIPE)
        if self.command_string == b'':
            self.close()
        else:
            self.runner.stdin.write(self.command_string)
            self.runner.stdin.flush()

    def get_error(self):
        """ returns stderr content """
        try:
            o, e = self.runner.communicate(timeout=5)
            print(o, e)
        except Excetion as err:
            print(err)
            self.runner.close()
            o, e = self.runner.communicate(timeout=5)
            print(e)
            e = b'client did not terminate'
        return e.decode()


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
        o = self.client.get_output()[:-2]
        print(o)
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


class RunnerIsAlive(Runner):
    """
    runner that tests if server and client are shutdown properly
    """
    def __init__(self, test_name, commands, expected, final_state):
        """ constructir - keeps expected finla state """
        super(RunnerIsAlive, self).__init__(test_name, commands, expected)
        self.final_state = final_state

    def run(self, kill_server=False):
        """ runs server and client - kills server if kill_server is True """
        self.server = ServerHandler(SERVER_OUTPUT)
        self.client = StreamClientHandler(CLIENT_OUTPUT)
        self.client.generate_client_command(CLIENT_INPUT, self.commands)

        self.server.run()
        self.client.run()

        if kill_server:
            time.sleep(1)
            self.server.close()
            time.sleep(1)
            self.client.send_command("take_screenshot")

        time.sleep(SLEEP_BEFORE_TERMINATION)

    def check_results(self):
        """
        checks if server and/or client are up/down
        as defined in the final_state
        """
        ok = True
        msg = ''
        if ok and \
                self.final_state & \
                IsAliveStates.SERVER_ON == IsAliveStates.SERVER_ON:
            ok = self.server.is_alive()
            if not ok:
                msg = "server should be on but it is not alive"
        if ok and self.final_state & \
                IsAliveStates.SERVER_OFF == IsAliveStates.SERVER_OFF:
            ok = not self.server.is_alive()
            if not ok:
                msg = "server should mot be on but it is"
        if ok and self.final_state & \
                IsAliveStates.CLIENT_ON == IsAliveStates.CLIENT_ON:
            ok = self.client.is_alive()
            if not ok:
                msg = "client should be on but it is not alive"
        if ok and self.final_state & \
                IsAliveStates.CLIENT_OFF == IsAliveStates.CLIENT_OFF:
            ok = not self.client.is_alive()
            if not ok:
                msg = "client should be off but it is alive"

        self.client.close()
        e = self.client.get_error()
        self.server.close()

        if e != '':
            msg += "\nclient error:\n" + e

        assert ok and e == '', "test " + self.name + ": " + msg


def test_single_command():
    """
    tests single command
    input: tralala
    output
    tralala
    """
    test_runner = RunnerIsAlive("single_command", ["tralala"], "tralala",
                                IsAliveStates.SERVER_ON +
                                IsAliveStates.CLIENT_ON)

    test_runner.run()
    test_runner.check_results()


def test_2_command():
    """
    tests single command
    input:
        tralala
        tralali
    output
        tralala
        tralali

    """
    test_runner = RunnerIsAlive("2 command", ["tralala", "tralalai"],
                                "tralala\ntralali",
                                IsAliveStates.SERVER_ON +
                                IsAliveStates.CLIENT_ON)

    test_runner.run()
    test_runner.check_results()


def test_single_exit():
    """
    tests single command
    input: exit
    output
            exit
    """
    test_runner = RunnerIsAlive("single exit", ["exit"], "exit",
                                IsAliveStates.SERVER_ON +
                                IsAliveStates.CLIENT_OFF)

    test_runner.run()
    test_runner.check_results()


def test_command_and_exit():
    """
    tests single command
    input:
        tralala
        exit
    output
        tralala
        exit
    """
    test_runner = RunnerIsAlive("command and exit", ["tralala", "exit"],
                                "tralala\nexit",
                                IsAliveStates.SERVER_ON +
                                IsAliveStates.CLIENT_OFF)

    test_runner.run()
    test_runner.check_results()

##############################################################################
#                       checksum tests                                       #
##############################################################################


def my_calculate_checksum(st):
    """ calculates checksum in order to compare it to tested methods """
    ch_sm = 0
    for ch in st:
        ch_sm += ord(ch)
    return ch_sm


def test_check_sum_correct():
    """
    tests check sum
    input: a string and its chcksum
    output: true
    """
    expected_sum = my_calculate_checksum("Hello World")
    res = check_sum.checksum("Hello World", expected_sum)
    assert res, \
        "your check sum should return " + str(expected_sum) + \
        " while it is: " + res


def test_check_sum_incorrect():
    """
    tests check sum
    input: a string and a  wrong cheksum number
    output: false
    """
    expected_sum = my_calculate_checksum("Hello World")
    res = check_sum.checksum("Hello World", expected_sum)
    assert res, \
        "your check sum should return " + str(expected_sum) + \
        " while it is: " + res


def main():
    """
    running client server tests
    """
    # detailed output
    pytest.main(["udp - tst.py"])
    clear_io_data()

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
