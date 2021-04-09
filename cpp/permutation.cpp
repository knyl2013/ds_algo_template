#define watch(x) cout << (#x) << " is " << (x) << endl
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
void backtrack(vector<int>& arr, vector<bool>& visited, vector<int>& cur, vector<vector<int>>& ans) {
    if (cur.size() == arr.size()) {
        ans.push_back(vector<int>(cur.begin(), cur.end()));
        return;
    }
    int n = arr.size();
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            visited[i] = true;
            cur.push_back(arr[i]);
            backtrack(arr, visited, cur, ans);
            cur.pop_back();
            visited[i] = false;
        }
    }
}
vector<vector<int>> permute(vector<int>& arr) {
    vector<vector<int>> ans;
    int n = arr.size();
    vector<bool> visited(n);
    vector<int> cur;
    backtrack(arr, visited, cur, ans);
    return ans;
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    //int n;
    //cin >> n;
    vector<int> arr = {1,2,3};
    permute(arr);
}


