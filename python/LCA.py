from functools import lru_cache

n = 10
adj = [[] for _ in range(n)]
parents = [-1 for _ in range(n)]
depths = [0 for _ in range(n)]
edges = [[0, 1], [0, 2], [3, 2], [2, 5]]
for u, v in edges:
    adj[u].append(v)
    adj[v].append(u)


def dfs(cur: int, parent: int):
    parents[cur] = parent
    if parent != -1:
        depths[cur] = depths[parent] + 1
    for nei in adj[cur]:
        if nei == parent:
            continue
        dfs(nei, cur)


dfs(0, -1)


@lru_cache(None)
def get_parent(cur: int, p: int) -> int:
    if p == 0:
        return parents[cur]
    return get_parent(get_parent(cur, p - 1), p - 1)


def get_kth_parent(cur: int, k: int) -> int:
    res, p = cur, 0
    while k > 0:
        bit = k % 2
        if bit:
            res = get_parent(res, p)
        p += 1
        k = k // 2
    return res


def lca(a: int, b: int) -> int:
    if depths[a] > depths[b]:
        return lca(b, a)
    diff = depths[b] - depths[a]
    b = get_kth_parent(b, diff)
    if a == b:
        return a
    lo, hi, res = 1, depths[a], -1
    while lo <= hi:
        mid = (lo + hi) // 2
        ap = get_kth_parent(a, mid)
        bp = get_kth_parent(b, mid)
        if ap == bp:
            res = ap
            hi = mid - 1
        else:
            lo = mid + 1
    return res


print(lca(1, 2))  # Expect 0
print(lca(5, 3))  # Expect 2
print(lca(5, 1))  # Expect 0
