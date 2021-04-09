import java.util.*;

public class UnionFind {
    int[] parent;
    int[] size;
    
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = this.find(parent[x]);
    }

    void union(int u, int v) {
        int u_p = this.find(u);
        int v_p = this.find(v);
        if (u_p != v_p) {
            size[u_p] += size[v_p];
            size[v_p] = 0;
            parent[v_p] = u_p;
        }
    }

    boolean same(int u, int v) {
        return this.find(u) == this.find(v);
    }

    int size(int x) {
        return this.size[find(x)];
    }
}
