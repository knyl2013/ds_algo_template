#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

bool is_prime(int n) {
    if (n <= 1) return false;
    if (n == 2) return true;

    for (int i = 2; i*i <= n; ++i) {
        if (n % i == 0) return false;
    }

    return true;
}

bool brute(int n) {
    if (n <= 1) return false;
    if (n == 2) return true;

    for (int i = 2; i <= n/2; ++i) {
        if (n % i == 0) return false;
    }

    return true;
}


int rand(int min, int max) {
    int range = max - min + 1;
    int num = rand() % range + min;
    return num;
}

void test_case() {
    while (true) {
        // int n = rand(1e7, 1e8);
        // cout << n << "\n";
        // is_prime(n);
        int n = rand(1, 1e5);
        cout << n << "\n";

        if (brute(n) != is_prime(n)) {
            break;
        }
    }
}



int main() {
    bool debug = true;
    if (debug) {test_case(); return 0;}
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int n;
    cin >> n;
    if (is_prime(n)) {
        cout << "yes";
    }
    else {
        cout << "no";
    }
    return 0;
}
