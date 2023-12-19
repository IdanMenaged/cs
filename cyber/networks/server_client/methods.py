import random

def TIME(client_socket):
    from datetime import datetime
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")
    return "Current Time =", current_time


def NAME():
    return "server name is Shachars Project"

def RAND(client_socket):
    random_number = random.randint(1, 10)
    return random_number

def QUIT():
    response = "quit"
    done = True
    return response, done

def EXIT():
    response = "exit"
    done = True
    return response, done