struct SegmentTree {
    const int INF = 0x3f3f3f3f;
    int n;
    vector<int> st;
    SegmentTree(int _n): n(_n), st(4*n, INF) {}

    void update(int p, int val) { update(1, 0, n-1, p, val); }
    int query(int L, int R) { return query(1, 0, n-1, L, R); }

private:
    void update(int node, int l, int r, int p, int val){
        if(l==r){
            st[node] = val;
            return;
        }
        int mid = (l+r)>>1;
        if(p <= mid) update(node<<1, l, mid, p, val);
        else           update(node<<1|1, mid+1, r, p, val);
        st[node] = min(st[node<<1], st[node<<1|1]);
    }

    int query(int node, int l, int r, int L, int R){
        if(R < l || r < L) return INF;
        if(L <= l && r <= R) return st[node];
        int mid = (l+r)>>1;
        return min(
            query(node<<1, l, mid, L, R),
            query(node<<1|1, mid+1, r, L, R)
        );
    }
};
