class UnionFindSparse {
public:
    map<int, int> parent;
    map<int, int> size;

    UnionFindSparse() {
        parent = {};
        size = {};
    }

    int Find(int x) {
        if (!parent.count(x)) return x;
        return parent[x] = this->Find(parent[x]);
    }

    void Union(int u, int v) {
        int u_p = this->Find(u);
        int v_p = this->Find(v);
        if (u_p != v_p) {
            size[u_p] += size[v_p];
            size[v_p] = 0;
            parent[v_p] = u_p;
        }
    }

    bool Same(int u, int v) {
        return this->Find(u) == this->Find(v);
    }

    int Size(int x) {
        return this->size[Find(x)];
    }
};
