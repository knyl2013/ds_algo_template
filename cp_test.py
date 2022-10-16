import subprocess
from random import randint

compile = not True
filename1 = "lane.cpp"
filename2 = "lane.cpp"

# Insert your code here: how to generate random test cases
def generate_testcase():
    N = randint(2, 10)
    R = randint(1, 100)
    cars = [[0, randint(1,R), randint(0,R)]]
    for i in range(1, N):
        rep = randint(1, 5)
        start = 0
        for _ in range(rep):
            car_start = randint(start, R)
            car_end = randint(car_start, R)
            cars.append([i, car_end-car_start, car_start])
            start = car_end
            if start == R:
                break
    ans = '{} {} {}\n'.format(N, len(cars), R)
    for car in cars:
        ans += '{} {} {}\n'.format(car[0], car[1], car[2])
    return ans

# Insert your code here: return true if two outputs(out1 and out2) are considered equal
def is_equal(out1, out2):
    if out1 != out2:
        if out1.isnumeric() and out2.isnumeric():
            return float(out1) == float(out2)
        return False
    return True

def compare(prog1, prog2):
    while True:
        tc = generate_testcase()
        out1, out2 = run(prog1, tc).decode("utf-8"), run(prog2, tc).decode("utf-8")
        print("testcase: ", tc)
        print(prog1+": ", out1)
        print(prog2+": ", out2)
        if not is_equal(out1, out2):
            break
    
def run(prog, tc):
    prog_name, _ = prog.split(".")
    if compile:
        subprocess.Popen("g++ -std=c++17 {} -o {}".format(prog, prog_name).split()).wait()
    p = subprocess.Popen(["./"+prog_name], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    output = p.communicate(input=tc.encode())[0]
    return output

compare(filename1, filename2)