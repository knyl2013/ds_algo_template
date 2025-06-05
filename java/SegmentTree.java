import java.util.Arrays;
import java.util.function.BinaryOperator;
// min over range := SegmentTree<Integer> minSeg = new SegmentTree<>(n, Integer.MAX_VALUE, Integer::min);

// sum over range := SegmentTree<Integer> sumSeg = new SegmentTree<>(n, 0, Integer::sum);

// max over range := SegmentTree<Integer> maxSeg = new SegmentTree<>(n, Integer.MIN_VALUE, Integer::max);
public class SegmentTree<T> {
    private final int n;
    private final T id;
    private final BinaryOperator<T> op;
    private final T[] st;

    @SuppressWarnings("unchecked")
    public SegmentTree(int n, T id, BinaryOperator<T> op) {
        this.n = n;
        this.id = id;
        this.op = op;

        //noinspection unchecked
        this.st = (T[]) new Object[4 * n];
        // Fill the entire array with the identity element.
        Arrays.fill(this.st, id);
    }

    /**
     * Point‐update: set position p to value.
     */
    public void update(int p, T value) {
        update(1, 0, n - 1, p, value);
    }

    /**
     * Query the combined result over [L..R] (inclusive).
     */
    public T query(int L, int R) {
        if (L < 0 || R >= n || L > R) {
            return id;
        }
        return query(1, 0, n - 1, L, R);
    }

    // ─── private recursive methods ────────────────────────────────────────────

    private void update(int node, int l, int r, int p, T value) {
        if (l == r) {
            // Leaf node: directly place the value
            st[node] = value;
            return;
        }
        int mid = (l + r) >>> 1;
        if (p <= mid) {
            update(node << 1, l, mid, p, value);
        } else {
            update((node << 1) | 1, mid + 1, r, p, value);
        }
        // Recompute this node from its children
        st[node] = op.apply(st[node << 1], st[(node << 1) | 1]);
    }

    private T query(int node, int l, int r, int L, int R) {
        // Completely outside
        if (R < l || r < L) {
            return id;
        }
        // Fully inside
        if (L <= l && r <= R) {
            return st[node];
        }
        int mid = (l + r) >>> 1;
        T leftResult  = query(node << 1, l, mid, L, R);
        T rightResult = query((node << 1) | 1, mid + 1, r, L, R);
        return op.apply(leftResult, rightResult);
    }
}

