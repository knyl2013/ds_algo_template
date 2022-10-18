#include <bits/stdc++.h>
#define int long long
using namespace std;

int n;
vector<int> arr;

void solve() {
  cin >> n;
  arr = vector<int>(n);
  for (int& x : arr) cin >> x;
}

/* I/O Template */
string task = "";
bool multiple_testcases = false;

signed main() {
  int T = 1;
  cin.tie(0)->sync_with_stdio(0);
  if (!task.empty()) {
    freopen((task + ".in").c_str(), "r", stdin);
    freopen((task + ".out").c_str(), "w", stdout);
  }
  if (multiple_testcases) cin >> T;
  for (int t = 1; t <= T; t++) {
    solve();
  }
}