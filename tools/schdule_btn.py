import pyautogui
import time
import os
import threading

class setInterval :
    def __init__(self,interval,action) :
        self.interval=interval
        self.action=action
        self.stopEvent=threading.Event()
        thread=threading.Thread(target=self.__setInterval, daemon=True)
        thread.start()

    def __setInterval(self) :
        nextTime=time.time()+self.interval
        while not self.stopEvent.wait(nextTime-time.time()) :
            nextTime+=self.interval
            self.action()

    def cancel(self) :
        self.stopEvent.set()

def cls():
    os.system('cls' if os.name=='nt' else 'clear')

global keys, remained
seconds = int(input("Delay Second(s): "))
keys = input("Key(s) to press after {} seconds in one line (e.g. command ctrl esc) : ".format(seconds))
repeat = input("Repeat? (Y/N): ") == "Y"
remained = 0

def print_time():
	cls()
	global keys, remained
	print("Remaining second(s) to fire [{}] : {}".format(keys, remained))
	remained -= 1

first_run = True
while first_run or repeat:
	remained = seconds
	if first_run:
		t = setInterval(1, print_time)
		first_run = False
	time.sleep(seconds)
	print("Firing {}".format(keys))
	keys_arr = keys.split()
	for key in keys_arr:
		pyautogui.keyDown(key)
	for key in keys_arr:
		pyautogui.keyUp(key)

