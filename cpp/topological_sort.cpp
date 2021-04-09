vector<bool> visited;
vector<int> in_deg;

void dfs(int i) {
    if (visited[i]) return;
    if (in_deg[i] == 0) {
        visited[i] = true;
        for (auto& nei : adj_list[i]) {
            --in_deg[nei];
            dfs(nei);
        }
    }
}