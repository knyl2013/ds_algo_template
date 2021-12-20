#include <bits/stdc++.h>
using namespace std;
// Find all number palindrome
int main() {
  for (int len = 1;; len++) {
    int x = (int)pow(10, (len - 1) / 2);
    int y = (int)pow(10, (len + 1) / 2);
    for (int i = x; i < y; i++) {
      long long b = i;
      for (int j = len % 2 == 1 ? i / 10 : i; j > 0; j /= 10) {
        b = b * 10 + j % 10;
      }
      cout << b << endl;
    }
  }
}
