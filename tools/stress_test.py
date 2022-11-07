import subprocess
from random import randint

filename1 = "lane.java"
filename2 = "lane_sol.cpp"

# Insert your code here: how to generate random test cases
def generate_testcase():
    N = randint(2, 100)
    R = randint(1, 1_000_000)
    cars = []
    for i in range(0, N):
        rep = randint(1 if i == 0 else 0,5)
        start = 0
        for _ in range(rep):
            car_start = randint(start, R-1)
            car_end = randint(car_start+1, R)
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
    def is_float(element) -> bool:
        try:
            float(element)
            return True
        except ValueError:
            return False
    if out1 != out2:
        if is_float(out1) and is_float(out2):
            return float(out1) == float(out2)
        return False
    return True

compile = True
def compare(prog1, prog2):
    while True:
        tc = generate_testcase()
        out1, out2 = run(prog1, tc).decode("utf-8"), run(prog2, tc).decode("utf-8")
        print("testcase: ", tc)
        print(prog1+": ", out1)
        print(prog2+": ", out2)
        if not is_equal(out1, out2):
            break
        global compile
        compile = False
    
def run(prog, tc):
    prog_name, ext = prog.split(".")
    if ext == "cpp":
        compile_command = "g++ -std=c++17 {} -o {}".format(prog, prog_name).split()
        run_command = ["./"+prog_name]
    elif ext == "java":
        compile_command = "javac {}".format(prog).split()
        run_command = ["java",prog_name]

    if compile:
        subprocess.Popen(compile_command).wait()
    p = subprocess.Popen(run_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    output = p.communicate(input=tc.encode())[0]
    return output

compare(filename1, filename2)