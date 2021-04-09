#define watch(x) cout << (#x) << " is " << (x) << endl
#include <bits/stdc++.h>
using namespace std;
using ll = long long;

void floyd(vector<vector<int>>& dp) {
    int n = dp.size();
    for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int new_dist = dp[i][k] + dp[k][j];
                if (new_dist < dp[i][j])
                    dp[i][j] = new_dist;
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    //int n;
    //cin >> n;
    int n = 4;
    int inf = 1e9+7;
    vector<vector<int>> dp(n, vector<int>(n, inf));
    vector<vector<int>> edges = {{0, 1}, {1, 2}, {1, 3}};

    for (int i = 0; i < n; ++i) {
        dp[i][i] = 0;
    }
    for (auto& e : edges) {
        dp[e[0]][e[1]] = 1;
        dp[e[1]][e[0]] = 1;
    }
    floyd(dp);
    for (auto& row : dp) {
        for (int& x : row) {
            cout << x << " ";
        }
        cout << "\n";
    }
}