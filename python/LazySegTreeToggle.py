# Python program to implement toggle and count
# queries on a binary array.


class LazySegTreeToggle:
    def __init__(self, n: int):
        self.tree = [0] * (n * 5)
        self.lazy = [False] * (n * 5)
        self.n = n

    def toggle(self, start: int, end: int):
        self._toggle(1, 0, self.n - 1, start, end)

    def query(self, start: int, end: int) -> int:
        return self._countQuery(1, 0, self.n - 1, start, end)

    def _toggle(self, node: int, st: int, en: int, us: int, ue: int):
        if self.lazy[node]:
            self.lazy[node] = False
            self.tree[node] = en - st + 1 - self.tree[node]
            if st < en:
                self.lazy[node << 1] = not self.lazy[node << 1]
                self.lazy[1 + (node << 1)] = not self.lazy[1 + (node << 1)]

        if st > en or us > en or ue < st:
            return

        if us <= st and en <= ue:
            self.tree[node] = en - st + 1 - self.tree[node]

            if st < en:
                self.lazy[node << 1] = not self.lazy[node << 1]
                self.lazy[1 + (node << 1)] = not self.lazy[1 + (node << 1)]
            return

        mid = (st + en) // 2
        self._toggle((node << 1), st, mid, us, ue)
        self._toggle((node << 1) + 1, mid + 1, en, us, ue)

        # And use the result of children calls to update this node
        if st < en:
            self.tree[node] = self.tree[node << 1] + self.tree[(node << 1) + 1]

    def _countQuery(self, node: int, st: int, en: int, qs: int, qe: int) -> int:
        if st > en or qs > en or qe < st:
            return 0

        if self.lazy[node]:
            self.lazy[node] = False
            self.tree[node] = en - st + 1 - self.tree[node]

            if st < en:
                self.lazy[node << 1] = not self.lazy[node << 1]
                self.lazy[(node << 1) + 1] = not self.lazy[(node << 1) + 1]

        if qs <= st and en <= qe:
            return self.tree[node]

        mid = (st + en) // 2
        return self._countQuery((node << 1), st, mid, qs, qe) + self._countQuery(
            (node << 1) + 1, mid + 1, en, qs, qe
        )


# Driver Code
if __name__ == "__main__":
    segTree = LazySegTreeToggle(5)
    segTree.toggle(2, 2)
    segTree.toggle(3, 3)
    print(segTree.query(2, 3))  # Expect 2
    segTree.toggle(2, 4)
    print(segTree.query(1, 4))  # Expect 1
    segTree.toggle(4, 4)
    print(segTree.query(1, 4))  # Expect 0

# This code is contributed by
# sanjeev2552
