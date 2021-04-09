vector<int> topological_sort(int n, vector<pair<int, int>>& edges) {
    vector<vector<int>> adj_list(n + 1);
    vector<int> incoming(n + 1, 0);
    vector<int> ans;
    queue<int> q;

    for (auto e : edges) {
        int u = e.first, v = e.second;
        adj_list[u].push_back(v);
        ++incoming[v];
    }

    for (int i = 1; i <= n; ++i) {
        if (incoming[i] == 0) {
            q.push(i);
        }
    }

    while (!q.empty()) {
        int len = q.size();
        while (len--) {
            int p = q.front(); q.pop();
            ans.push_back(p);
            for (int nei : adj_list[p]) {
                --incoming[nei];
                if (incoming[nei] == 0) {
                    q.push(nei);
                }
            }
        }
    }

    return ans;
}