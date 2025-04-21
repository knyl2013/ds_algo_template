template<typename T>
class SegmentTree {
public:
    // Usages:
    // min over range => SegmentTree<int> segtree(n, INT_MAX, [](int a, int b) { return min(a, b); });
    // sum over range => SegmentTree<int> segtree(n, 0, [](int a, int b) { return a + b; });
    // max over range => SegmentTree<int> segtree(n, 0, [](int a, int b) { return max(a, b); });
    using F = std::function<T(const T&, const T&)>;
    SegmentTree(int _n, T _id, F _op)
      : n(_n), id(_id), op(_op), st(4*_n, _id)
    {}
    void update(int p, const T &value) {
        update(1, 0, n-1, p, value);
    }
    T query(int L, int R) const {
        return query(1, 0, n-1, L, R);
    }
private:
    int n;
    T id;
    F op;
    std::vector<T> st;

    void update(int node, int l, int r, int p, const T &value) {
        if (l == r) {
            st[node] = value;
            return;
        }
        int mid = (l + r) >> 1;
        if (p <= mid)       update(node<<1,   l,    mid, p, value);
        else                update(node<<1|1, mid+1, r,   p, value);
        st[node] = op(st[node<<1], st[node<<1|1]);
    }

    T query(int node, int l, int r, int L, int R) const {
        if (R < l || r < L) // completely outside
            return id;
        if (L <= l && r <= R) // fully inside
            return st[node];
        int mid = (l + r) >> 1;
        T left  = query(node<<1,   l,    mid, L, R);
        T right = query(node<<1|1, mid+1, r,   L, R);
        return op(left, right);
    }
};