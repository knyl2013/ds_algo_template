class UnionFind:
    def __init__(self, n: int):
        self.parents = [i for i in range(n)]
        self.counts = [1 for i in range(n)]
    def find(self, x: int) -> int:
        if self.parents[x] == x:
            return x
        p = self.find(self.parents[x])
        self.parents[x] = p
        return p
    def union(self, u: int, v: int):
        u_parent, v_parent = self.find(u), self.find(v)
        if u_parent == v_parent:
            return
        self.parents[u_parent] = v_parent
        self.counts[v_parent] += self.counts[u_parent]
        self.counts[u_parent] = 0
        
# Test code
uf = UnionFind(10)
uf.union(1, 2)
print(uf.find(1) == uf.find(2)) # Expected: True
print(uf.find(1) == uf.find(3)) # Expected: False
uf.union(1, 3)
print(uf.find(1) == uf.find(3)) # Expected: True