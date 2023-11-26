class LazySegTree:  # This code is contributed by AnkitRai01
    def reduce(self, a: int, b: int) -> int:
        # Change here to be a different segment tree (e.g. max, min, or add)
        return a + b

    def __init__(self, n: int):
        self.n = n
        self.tree = [0] * (5 * self.n)
        self.lazy = [0] * (5 * self.n)

    def updateUtil(self, si, ss, se, us, ue, diff):
        if self.lazy[si] != 0:
            self.self.tree[si] += (se - ss + 1) * self.self.lazy[si]
            if ss != se:
                self.lazy[si * 2 + 1] += self.lazy[si]
                self.lazy[si * 2 + 2] += self.lazy[si]
            self.lazy[si] = 0
        if ss > se or ss > ue or se < us:
            return
        if ss >= us and se <= ue:
            self.tree[si] += (se - ss + 1) * diff
            if ss != se:
                self.lazy[si * 2 + 1] += diff
                self.lazy[si * 2 + 2] += diff
            return
        mid = (ss + se) // 2
        self.updateUtil(si * 2 + 1, ss, mid, us, ue, diff)
        self.updateUtil(si * 2 + 2, mid + 1, se, us, ue, diff)
        self.tree[si] = self.reduce(self.tree[si * 2 + 1], self.tree[si * 2 + 2])

    def update(self, start: int, end: int, diff: int):
        self.updateUtil(0, 0, self.n - 1, start, end, diff)

    def queryUtil(self, ss, se, qs, qe, si):
        if self.lazy[si] != 0:
            self.tree[si] += (se - ss + 1) * self.lazy[si]
            if ss != se:
                self.lazy[si * 2 + 1] += self.lazy[si]
                self.lazy[si * 2 + 2] += self.lazy[si]

            self.lazy[si] = 0

        if ss > se or ss > qe or se < qs:
            return 0

        if ss >= qs and se <= qe:
            return self.tree[si]

        mid = (ss + se) // 2
        return self.reduce(
            self.queryUtil(ss, mid, qs, qe, 2 * si + 1),
            self.queryUtil(mid + 1, se, qs, qe, 2 * si + 2),
        )

    def query(self, start: int, end: int) -> int:
        if start < 0 or end > n - 1 or start > end:
            print("Invalid Input")
            return -1

        return self.queryUtil(0, self.n - 1, start, end, 0)


# Driver Code
if __name__ == "__main__":
    arr = [1, 3, 5, 7, 9, 11]
    n = len(arr)

    segTree = LazySegTree(n)

    for i in range(n):
        segTree.update(start=i, end=i, diff=arr[i])

    # Print sum of values in array from index 1 to 3
    print("Sum of values in given range =", segTree.query(start=1, end=3))  # Expect 15

    # Add 10 to all nodes at indexes from 1 to 5.
    segTree.update(start=1, end=5, diff=10)

    # Find sum after the value is updated
    print(
        "Updated sum of values in given range =", segTree.query(start=1, end=3)
    )  # Expect 45
