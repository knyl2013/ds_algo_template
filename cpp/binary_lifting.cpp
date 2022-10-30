class TreeAncestor {
public:
    vector<int> parent;
    vector<vector<int>> dp;
    int LOG = 20; // ~ log n
    TreeAncestor(int n, vector<int>& p) {
        parent = p;
        dp = vector<vector<int>>(n, vector<int>(LOG, -1));
    }
    
    // Return 2^(x)-th ancestor of node
    int up(int node, int x) {
        if (node == -1) return -1;
        if (x == 0) return parent[node];
        if (dp[node][x] != -1) return dp[node][x];
        int p = up(node, x - 1);
        return dp[node][x] = up(p, x - 1);
    }
    
    int getKthAncestor(int node, int k) {
        int ans = node;
        for (int j = 0; j < LOG; j++) {
            if ((k >> j) & 1) {
                ans = up(ans, j);
            }
        }
        return ans;
    }
};
