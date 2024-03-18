f"""
    technician server test - for windows
    Ayelet Mashiah
"""
folder = "."
EXECUTE_RUNNABLE = "exe_test.bat"


# EXECUTE_RUNNABLE = "notepad"
# check_execute_result = None

import sys
import os
import subprocess
import psutil

import time
import pytest
import shutil


PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

# termination constatnts
TERMINATION_SLEEP_TIME = 1

# file management constatnts
WORK_DIR = "c:\\test_folder"
SERVER_FOLDER = WORK_DIR + "\\server"
CLIENT_FOLDER = WORK_DIR + "\\client"
SERVER_COPY_FOLDER = SERVER_FOLDER + "\\copy_folder"
SCREENSHOT_FILE = SERVER_FOLDER + "\\screen.jpg"
FILE_2_DELETE = SERVER_FOLDER + "\\f2.txt"
FILE_2_SEND = SERVER_FOLDER + "\\file2send.txt"
SENT_FILE = CLIENT_FOLDER + "\\file2send.txt"
FILE_NO_EXIST = "c:\\tralala"
FOLDER_NO_EXIST = "c:\\tralala\\tralali"
SEND_FILE_SIZE = 4 * 1024

if len(sys.argv) > PATH_WITH_FOLDER_MIN_LEN:
    folder = sys.argv[PATH_INDEX]

# management constants
SERVER_OUTPUT = "srvr_out.txt"
CLIENT_INPUT = "clnt_input.txt"
CLIENT_OUTPUT = "clnt_output.txt"
EXECUTE_RES_FILE = folder + "\\srvr\\exe_out.txt"
SLEEP_BEFORE_TERMINATION = 1
INPUT_STRING = "please enter a request "


# is alive constants
class IsAliveStates:
    SERVER_ON = 1
    SERVER_OFF = 2
    CLIENT_ON = 4
    CLIENT_OFF = 8

##############################################################################
#                           testing utilities                                #
##############################################################################


def generate_file(f_name):
    """
    input: file path
    generates a file
    """
    if not os.path.exists(f_name):
        f = open(f_name, "w")
        f.write("hello")
        f.close()


def generate_batch(f_name):
    """
    input: file path
    generates a batc file
    content:
        @echo off
        echo hello >
    """
    if not os.path.exists(f_name):
        f = open(f_name, "w")
        f.write("@echo off\necho hello > exe_out.txt")
        f.close()


def generate_big_file(f_name):
    """
    input: file path
    generates a batc file
    content:
        @echo off
        echo hello >
    """
    if not os.path.exists(f_name):
        f = open(f_name, "w")
        f.write("a" * SEND_FILE_SIZE)
        f.close()


def generate_test_data(big_file):
    """
    generated files and folder for testing
                                    test_folder
                                    |
                        -------------------------
                        |          |            |
                    server       client     exe_test.bat
                       |
                -----------------------
                |      |      |        |
              f1.txt f2.txt f3.txr copy_folder

    big_file - a boolean indicating whether
    to generate a big file in the trr or not
    """
    clear_test_data()
    if not os.path.exists(WORK_DIR):
        os.mkdir(WORK_DIR)
    if not os.path.exists(SERVER_FOLDER):
        os.mkdir(SERVER_FOLDER)
    if not os.path.exists(CLIENT_FOLDER):
        os.mkdir(CLIENT_FOLDER)
    if not os.path.exists(SERVER_COPY_FOLDER):
        os.mkdir(SERVER_COPY_FOLDER)

    generate_file(SERVER_FOLDER + "\\f1.txt")
    generate_file(SERVER_FOLDER + "\\f2.txt")
    generate_file(SERVER_FOLDER + "\\f3.txt")
    generate_batch(WORK_DIR + "\\exe_test.bat")

    if big_file:
        generate_big_file(FILE_2_SEND)


class CD:
    """ Context manager for changing the current working directory """
    def __init__(self, new_path):
        """ constructor """
        self.new_path = new_path
        self.saved_path = ''

    def __enter__(self):
        """ entering scope """
        self.saved_path = os.getcwd()
        os.chdir(self.new_path)

    def __exit__(self, etype, value, traceback):
        """ exiting scope """
        os.chdir(self.saved_path)


def clear_test_data():
    """ clears files tree"""
    shutil.rmtree(WORK_DIR, ignore_errors=True)

    if os.path.exists(EXECUTE_RES_FILE):
        os.remove(EXECUTE_RES_FILE)


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
    def __init__(self, name, out_f_name, working_directory):
        """ contructor - keeps name and opens stdout """
        self.working_directory = working_directory
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
        # time.sleep(TERMINATION_SLEEP_TIME)
        # to let the process die peacefully :)
        on = self.runner is not None and psutil.pid_exists(self.runner.pid)
        return on

    def run(self, my_env=None):
        """ runs the process """
        with CD(self.working_directory):
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
        print(o)
        return o.split("\n")


class ServerHandler(ProcessHandler):
    """
    server's handler
    """
    def __init__(self, out_f_name):
        super(ServerHandler, self).__init__("server", out_f_name, "srvr")


class ClientHandler(ProcessHandler):
    """
    client handler
    """
    def __init__(self, out_f_name):
        """ constructor """
        super(ClientHandler, self).__init__("client", out_f_name, "clnt")

    def add_command(self, command):
        """ adds a single command to client's input """
        self. input_f.write(command + "\n")

    def generate_client_command(self, inp_name, commands):
        """ generates client's expected command - stdin """
        clnt_input_f_name = folder + "\\" + inp_name
        self.input_f = open(clnt_input_f_name, "w")
        for cmd in commands:
            self.add_command(cmd)
        self.add_command("quit\n")
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
        with CD(self.working_directory):
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
        except Exception as err:
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
        generate_test_data(big_file)
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
        clnt_out = self.client.get_output()
        print("****", clnt_out)
        if clnt_out[-1] == '':
            o = clnt_out[:-2]
        else:
            o = clnt_out[:-1]

        # o = self.client.get_output()[:-2]
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


class RunnerFileDelete(Runner):
    """
    runner of delete file session
    """
    def __init__(self, test_name, commands, expected, del_file_path):
        """ constructor - keeps the path of the file to be deletes """
        super(RunnerFileDelete, self).__init__(test_name, commands, expected)
        self.del_file = del_file_path

    def check_results(self):
        """
        checks if file was actually deleted and
        compares client's output to expected output
        """
        clnt_out = self.client.get_output()
        print("****", clnt_out)
        if clnt_out[-1] == '':
            o = clnt_out[:-2]
        else:
            o = clnt_out[:-1]

        print(o)
        actual = ''
        for res in o:
            res = res.replace(INPUT_STRING, "").lower()
            actual += res + '\n'
        actual = actual[:-1]

        file_ok = not os.path.exists(self.del_file)

        assert self.expected == actual and \
            file_ok, \
            "test " + self.name +  \
            ": excpected = " + self.expected + \
            " actual = " + actual + " and " + \
            self.del_file + \
            " might not be deleted "


class Runner4Execute(Runner):
    """
    runner of execute session
    """
    def __init__(self, test_name, commands, expected, result_checker=None):
        """ constructor - initiates results checker """
        super(Runner4Execute, self).__init__(test_name, commands, expected)
        self.result_checker = result_checker

    def run(self):
        """
        uses change current working directory context
        object to change current working directory and runs
        server and client with execute command
        """
        with CD(folder):
            self.server = ServerHandler(SERVER_OUTPUT)
            self.client = ClientHandler(CLIENT_OUTPUT)
            self.client.generate_client_command(CLIENT_INPUT, self.commands)
            my_env = os.environ.copy()
            my_env["PATH"] = WORK_DIR + ";" + my_env["PATH"]
            self.server.run(my_env)
            self.client.run()

            time.sleep(SLEEP_BEFORE_TERMINATION)
            self.client.close()
            self.server.close()

    def check_results(self):
        """
        runs executable and checks if it was run properly
        """
        clnt_out = self.client.get_output()
        print("****", clnt_out)
        if clnt_out[-1] == '':
            o = clnt_out[:-2]
        else:
            o = clnt_out[:-1]
        print(o)
        actual = ''
        for res in o:
            res = res.replace(INPUT_STRING, "").lower()
            actual += res + '\n'
        actual = actual[:-1]
        if self.result_checker is None:
            ok = True
        else:
            ok = self.result_checker()
        if os.path.exists(EXECUTE_RES_FILE):
            os.remove(EXECUTE_RES_FILE)
        assert ok and self.expected == actual, \
            "test " + self.name + \
            ": excpected = " + self.expected + \
            " actual = " + actual + " and program " + \
            "might not be executed"


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


def test_dir():
    """
    tests dir command
    input: dir c:\\test_folder\\server
    output
    ['c:\\\\test_folder\\\\server\\\\f1.txt',
    'c:\\\\test_folder\\\\server\\\\f2.txt',
    'c:\\\\test_folder\\\\server\\\\f3.txt']
    """
    test_runner = Runner("dir", ["dir " + SERVER_FOLDER],
                         "['c:\\\\test_folder\\\\server\\\\f1.txt', " +
                         "'c:\\\\test_folder\\\\server\\\\f2.txt', " +
                         "'c:\\\\test_folder\\\\server\\\\f3.txt']")

    test_runner.run()
    test_runner.check_results()


def test_dir_no_params():
    """
    tests dir without parameter command
    input: dir
    output
    illegal request
    """
    test_runner = Runner("dir", ["dir "], "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_take_screenshot():
    """
    tests take screenshot command
    input: take screenshot
    output
    screenshot takent
    """
    test_runner = Runner("take screenshot", ["take_screenshot"],
                         "screenshot taken", SCREENSHOT_FILE)
    test_runner.run()
    test_runner.check_results()


def test_take_screenshot_with_parameter():
    """
    tests take_screenshot  parameter command
    input: take_screenshot
    output
    illegal request
    """
    test_runner = Runner("take screenshot with parameter",
                         ["take_screenshot aaa"], "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_delete():
    """
    tests delete command
    input: delete c:\\test_folder\\server]f2.txt
    output
    file deleted
    file should be deleted
    """
    test_runner = RunnerFileDelete("delete file",
                                   ["delete " + FILE_2_DELETE],
                                   "file deleted", FILE_2_DELETE)

    test_runner.run()
    test_runner.check_results()


def test_delete_no_exist():
    """
    tests delet of none existing file command
    input: delete c:\\tralala
    output
    illegal command
    """
    test_runner = Runner("delete file doesnt exist",
                         ["delete " + FILE_NO_EXIST],
                         "illegal command")

    test_runner.run()
    test_runner.check_results()


def test_delete_2_params():
    """
    tests delete with 2 parameters command
    input: delete c:\\test_folder\\server]f2.txt c:\\test_folder\\server]f2.txt
    output
    illegal request
    """
    test_runner = Runner("delete file 2 parameters",
                         ["delete " + FILE_2_DELETE + " " + FILE_2_DELETE],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_delete_no_params():
    """
    tests delete with no parameter command
    input: delete
    output
    illegal request
    """
    test_runner = Runner("delete without parameters",
                         ["delete"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_copy():
    """
    tests copy command
    input: copy c:\\test_folder\\server\\f1.txt c:\\test_folder\\server\\copy_folder
    output
    file copied
    file should be copied
    """
    test_runner = Runner("copy",
                         ["copy " + SERVER_FOLDER + "\\f1.txt" + " " +
                          SERVER_COPY_FOLDER],
                         "file copied", SERVER_COPY_FOLDER + "\\f1.txt")

    test_runner.run()
    test_runner.check_results()


def test_copy_no_params():
    """
    tests copy with no parameter command
    input: copy
    output
    illegal request
    """
    if not os.path.exists(SERVER_COPY_FOLDER):
        os.mkdir(SERVER_COPY_FOLDER)

    test_runner = Runner("copy no parameters",
                         ["copy"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_copy_3_params():
    """
    tests copy with no parameter command
    input: copy a b c
    output
    illegal request
    """
    test_runner = Runner("copy 3 parameters",
                         ["copy a b c"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_copy_file_no_exist():
    """
    tests copy with none existing file command
    input: copy c:\\tralala
    output
    illegal command
    """
    test_runner = Runner("copy file does not exists",
                         ["copy " + " " + FILE_NO_EXIST +
                          " " + SERVER_COPY_FOLDER],
                         "illegal command")

    test_runner.run()
    test_runner.check_results()


def test_copy_folder_no_exist():
    """
    tests copy command with none existing folder
    input: copy c:\\test_folder\\server\\f1.txt c:\\tralala\\tralali
    output
    illegal command
    """
    test_runner = Runner("copy folder does not exists",
                         ["copy " + SERVER_FOLDER + "\\f1.txt" +
                          " " + FOLDER_NO_EXIST],
                         "illegal command")

    test_runner.run()
    test_runner.check_results()


def check_execute_res():
    """
    tests if execution of bat file was done by testing its output file
    exe_test.bat generates a file named c:\\test_folder\\exe_out.txt
    that contains the string hello
    """
    content = ''
    if os.path.exists(EXECUTE_RES_FILE):
        with open(EXECUTE_RES_FILE, "r") as f:
            content = f.read()
    return "".join(content.split()) == "hello"


def test_execute():
    """
    tests execute without full path
    input: execute exe_test.bat
    output
       program executed
    output file should be generated
    """
    test_runner = Runner4Execute("execute",
                                 ["execute " + " " + EXECUTE_RUNNABLE],
                                 "program executed", check_execute_res)

    test_runner.run()
    test_runner.check_results()


def test_execute_full_path():
    """
    tests execute with full path
    input: execute exe_test.bat
    output
       program executed
    output file should be generated
    """
    test_runner = Runner4Execute("execute with full path",
                                 ["execute " + " " + WORK_DIR + "\\" +
                                  EXECUTE_RUNNABLE],
                                 "program executed", check_execute_res)

    test_runner.run()
    test_runner.check_results()


def test_execute_no_exist():
    """
    tests execute where exe does not exist
    input: execute c:\\tralala
    output
       illegal command
    """
    test_runner = Runner4Execute("execute no program",
                                 ["execute " + " " + FILE_NO_EXIST],
                                 "illegal command")

    test_runner.run()
    test_runner.check_results()


def test_send_file():
    """
    tests send_file command
    input: send_file c:\\test_folder\\server
    output
       file sent
    file should be generated in client's folder
    """
    test_runner = Runner("send file",
                         ["send_file " + " " + FILE_2_SEND],
                         "file sent", SENT_FILE, True)

    test_runner.run()
    test_runner.check_results()


def test_send_file_2_params():
    """
    tests send_file with two parameters command
    input: send_file c:\\test_folder\\server tralala
    output
       illegal request
    file should not be generated
    """
    test_runner = Runner("send file 2 parameters",
                         ["send_file " + " " + FILE_2_SEND + " tralala"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_send_file_no_params():
    """
    tests send_file with tno parameters command
    input: send_file
    output
       illegal request
    file should not be generated
    """
    test_runner = Runner("send file without parameters",
                         ["send_file"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_illegal_request():
    """
    tests an illegal command
    input: tralala
    output:
        illegal request
    """
    test_runner = Runner("illegal command",
                         ["tralala"],
                         "illegal request")

    test_runner.run()
    test_runner.check_results()


def test_few_command():
    """
    tests running few command
    input:
        take_screenshot
        send_file <screenshot file>
        copy <screenshot file> <dest folde>
        delete <screenshot file>
        delete <copied file>

    output
        screenshot take
        file sent
        file copied
        file deleted
        file deleted
    """
    test_runner = \
        Runner("consecutive commands",
               ["take_screenshot",
                "send_file " + SCREENSHOT_FILE,
                "copy " + SCREENSHOT_FILE + " " +
                SERVER_COPY_FOLDER,
                "delete " + SCREENSHOT_FILE,
                "delete " + SERVER_COPY_FOLDER + "\\screen.jpg"],
               "screenshot taken\nfile sent\nfile copied" +
               "\nfile deleted\nfile deleted")

    test_runner.run()
    test_runner.check_results()


def test_few_command_illegal_client():
    """
    tests few command with illegal one (client sod)
    input:
        take_screenshot
        send_file
        copy <screenshot file>  <dest folder>
        delete <screenshot file> tralala
        delete <destfolder>\\screen.jpg"
    output:
       screenshot taken
       illegal request
       file copied
       illegal request
       file deleted
    """
    test_runner = \
        Runner("consecutive commands client side errors",
               ["take_screenshot",
                "send_file ",
                "copy " + SCREENSHOT_FILE + " " +
                SERVER_COPY_FOLDER,
                "delete " + SCREENSHOT_FILE + " tralala",
                "delete " + SERVER_COPY_FOLDER + "\\screen.jpg"],
               "screenshot taken\nillegal request\nfile copied" +
               "\nillegal request\nfile deleted")

    test_runner.run()
    test_runner.check_results()


def test_few_command_illegal_server():
    """
    tests few command with illegal one (client sod)
    input:
        take_screenshot
        send_file c:\\tralala
        copy <screenshot file>  <dest folder>
        delete c:\\tralala
        delete <destfolder>\\screen.jpg"
    output:
       screenshot taken
       illegal command
       file copied
       illegal command
       illegal command
       file deleted
    """
    test_runner = \
        Runner("consecutive commands server side errors",
               ["take_screenshot",
                "send_file " + FILE_NO_EXIST,
                "copy " + SCREENSHOT_FILE + " " +
                SERVER_COPY_FOLDER,
                "delete " + FILE_NO_EXIST,
                "execute " + FILE_NO_EXIST,
                "delete " + SERVER_COPY_FOLDER + "\\screen.jpg"],
               "screenshot taken\nillegal command\nfile copied" +
               "\nillegal command\nillegal command\nfile deleted")

    test_runner.run()
    test_runner.check_results()


def test_quit():
    """
    tests quit command
    input:
        quit
    output:
        client should terminate server should stay on
    """
    test_runner = \
        RunnerIsAlive("test quit", ["quit"], '',
                      IsAliveStates.SERVER_ON + IsAliveStates.CLIENT_OFF)

    test_runner.run()
    test_runner.check_results()




def test_exit():
    """
    tests exit command
    input:
        exit
    output:
        both client and server should terminate
    """
    test_runner = \
        RunnerIsAlive("test exit", ["exit"], '',
                      IsAliveStates.SERVER_OFF + IsAliveStates.CLIENT_OFF)

    test_runner.run()
    test_runner.check_results()


def test_kill_client():
    """
    tests client's sudden terminatiom
    input:
        client terminates
    output:
        server should stay on
    """
    test_runner = \
        RunnerIsAlive("test kill client", [], '',
                      IsAliveStates.SERVER_ON + IsAliveStates.CLIENT_OFF)

    test_runner.run()
    test_runner.check_results()


def test_kill_server():
    """
    tests aerver's sudden termination
    input:
        server terminates
    output:
       client shoul exit properly but not crash
    """
    test_runner = \
        RunnerIsAlive("test kill server", ["take_screenshot"], '',
                      IsAliveStates.SERVER_OFF +
                      IsAliveStates.CLIENT_OFF)

    test_runner.run(True)
    test_runner.check_results()

def test_reload():
    """
    tests reload mechanism
    input:
        reload
        take_screenshot
    output:
        module reloaded
        screenshot taken
    """
    test_runner = \
        Runner("reload",
               ["reload",
                "take_screenshot"],
               "module reloaded\nscreenshot takenn")

    test_runner.run()
    test_runner.check_results()


def main():
    """
    running client server tests
    """
    # detailed output
    pytest.main(["technician_server - tst.py"])

    # summery
    # pytest.main(["technician_server - tst.py", "-v", "--tb=no"])
    # test_dir_no_params()
    print("clearing....")
    clear_test_data()

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
