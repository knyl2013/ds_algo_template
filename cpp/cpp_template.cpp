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

signed main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  int T = 1;
  bool multiple_testcases = false;
  if (multiple_testcases) cin >> T;
  for (int t = 1; t <= T; t++) {
    solve();
  }
}