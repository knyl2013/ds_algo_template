
static class MaxFlow {
    static final long INF = (long) 1e16;
    private long[][] copy(long[][] mat)
    {
        long[][] ans = new long[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                ans[i][j] = mat[i][j];
        return ans;
    }
    public long maxFeasibleFlow(int source, int sink, long[][] c, long[][] d)
    {
        int n = c.length;
        long[][] nc = new long[n+2][n+2];
        long[] a = new long[n];
        int source2 = n, sink2 = n + 1;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                nc[u][v] = c[u][v] - d[u][v];
                a[u] -= d[u][v];
                a[v] += d[u][v];
            }
        }
        nc[sink][source] = INF;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                sum += a[i];
                nc[source2][i] = a[i];
            }
            else {
                nc[i][sink2] = -a[i];
            }
        }
        long f1 = maxFlow(source2, sink2, nc);
        if (f1 < sum) return -1; // no feasible flow
        return maxFlow(source, sink, nc);
    }
    public boolean feasible(long[][] c, long[][] d)
    {
        int n = c.length;
        long[][] nc = new long[n+2][n+2];
        long[] a = new long[n];
        int source2 = n, sink2 = n + 1;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                nc[u][v] = c[u][v] - d[u][v];
                a[u] -= d[u][v];
                a[v] += d[u][v];
            }
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                sum += a[i];
                nc[source2][i] = a[i];
            }
            else {
                nc[i][sink2] = -a[i];
            }
        }
        return maxFlow(source2, sink2, nc) >= sum;
    }
    public long maxFlow(int source, int sink, long[][] capMat)
    {
        if (source == sink) return INF;
        int n = capMat.length;
        long total = 0;
        while (true) {
            int[] prevNodes = new int[n];
            Arrays.fill(prevNodes, -1);
            long[] flows = new long[n];
            boolean[] visited = new boolean[n];
            flows[source] = INF;
            long maxFlow = 0;
            int maxLoc = -1;
            while (true) {
                maxFlow = 0;
                maxLoc = -1;
                // find the unvisited node with highest capacity
                for (int i = 0; i < n; i++) {
                    if (flows[i] > maxFlow && !visited[i]) {
                        maxFlow = flows[i];
                        maxLoc = i;
                    }
                }
                if (maxLoc == -1) break;
                if (maxLoc == sink) break;
                visited[maxLoc] = true;
                for (int i = 0; i < n; i++) {
                    if (capMat[maxLoc][i] == 0) continue;
                    if (flows[i] < Math.min(maxFlow, capMat[maxLoc][i])) {
                        prevNodes[i] = maxLoc;
                        flows[i] = Math.min(maxFlow, capMat[maxLoc][i]);
                    }
                }
            }
            // no path
            if (maxLoc == -1) break;
            long pathCap = flows[sink];
            total += pathCap;
            int cur = sink;
            while (cur != source) {
                int next = prevNodes[cur];
                capMat[next][cur] -= pathCap;
                capMat[cur][next] += pathCap;
                cur = next;
            }
        }
        
        return total;
    }
}

