static class TarjanBridge {
    private int bridges;      // number of bridges
    private int cnt;          // counter
    private int[] pre;        // pre[v] = order in which dfs examines v
    private int[] low;        // low[v] = lowest preorder of any vertex connected to v

    public TarjanBridge(int[][] edges) {
        int n = edges.length;
        low = new int[n];
        pre = new int[n];
        for (int v = 0; v < n; v++) low[v] = -1;
        for (int v = 0; v < n; v++) pre[v] = -1;

        for (int v = 0; v < n; v++)
            if (pre[v] == -1)
                dfs(edges, v, v);
    }

    public int components() { return bridges + 1; }

    private void dfs(int[][] edges, int u, int v) {
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : edges[v]) {
            if (pre[w] == -1) {
                dfs(edges, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    System.out.println(v + "-" + w + " is a bridge");
                    bridges++;
                }
            }

            // update low number - ignore reverse of edge leading to v
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }
    }
} // https://stackoverflow.com/questions/28917290/how-can-i-find-bridges-in-an-undirected-graph