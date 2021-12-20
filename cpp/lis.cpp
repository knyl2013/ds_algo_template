#include <bits/stdc++.h>
using namespace std;
int lengthOfLIS(vector<int> &a) {
  int n = a.size();
  vector<int> mono;
  for (int x : a) {
    if (mono.empty() || x > mono.back()) {
      mono.push_back(x);
    } else {
      int lo = 0, hi = mono.size() - 1, gt_idx = -1;
      while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (mono[mid] <= x) {
          lo = mid + 1;
        } else {
          if (mid == 0 || mono[mid - 1] < x) gt_idx = mid;
          hi = mid - 1;
        }
      }
      if (gt_idx != -1) mono[gt_idx] = x;
    }
  }
  return mono.size();
}