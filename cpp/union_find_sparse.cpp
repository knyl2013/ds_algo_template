#include <bits/stdc++.h>
using namespace std;
class UnionFind {
 public:
  map<int, int> parent;
  map<int, int> size;

  UnionFind() {
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
      size.erase(v_p);
      parent[v_p] = u_p;
    }
  }

  bool Same(int u, int v) { return this->Find(u) == this->Find(v); }

  int Size(int x) { return this->size[Find(x)]; }
};
