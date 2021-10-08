#include <bits/stdc++.h>
using namespace std;

class Tarjan {
 public:
  int V;                    // No. of vertices
  vector<vector<int>> adj;  // A dynamic array of adjacency lists
  Tarjan(int V) {
    this->V = V;
    adj = vector<vector<int>>(V);
  }
  void connect(int v, int w) { adj[v].push_back(w); }
  vector<vector<int>> SCC() {
    int* disc = new int[V];
    int* low = new int[V];
    bool* stackMember = new bool[V];
    stack<int>* st = new stack<int>();
    vector<vector<int>> ans;
    for (int i = 0; i < V; i++) {
      disc[i] = -1;
      low[i] = -1;
      stackMember[i] = false;
    }
    for (int i = 0; i < V; i++) {
      if (disc[i] == -1) {
        SCCUtil(i, disc, low, st, stackMember, ans);
      }
    }
    return ans;
  }
  void SCCUtil(int u, int disc[], int low[], stack<int>* st, bool stackMember[],
               vector<vector<int>>& ans) {
    static int time = 0;
    disc[u] = low[u] = ++time;
    st->push(u);
    stackMember[u] = true;
    for (int v : adj[u]) {
      if (disc[v] == -1) {
        SCCUtil(v, disc, low, st, stackMember, ans);
        low[u] = min(low[u], low[v]);
      } else if (stackMember[v] == true)
        low[u] = min(low[u], disc[v]);
    }
    int w = 0;  // To store stack extracted vertices
    if (low[u] == disc[u]) {
      vector<int> cur;
      while (st->top() != u) {
        w = (int)st->top();
        cur.push_back(w);
        stackMember[w] = false;
        st->pop();
      }
      w = (int)st->top();
      cur.push_back(w);
      stackMember[w] = false;
      st->pop();
      ans.push_back(cur);
    }
  }
};

// Driver program to test above function
int main() {
  vector<vector<int>> ans;
  cout << "\nSCCs in first graph \n";
  Tarjan g1(5);
  g1.connect(1, 0);
  g1.connect(0, 2);
  g1.connect(2, 1);
  g1.connect(0, 3);
  g1.connect(3, 4);
  ans = g1.SCC();
  for (auto grp : ans) {
    for (auto x : grp) {
      cout << x << ' ';
    }
    cout << '\n';
  }

  cout << "\nSCCs in second graph \n";
  Tarjan g2(4);
  g2.connect(0, 1);
  g2.connect(1, 2);
  g2.connect(2, 3);
  ans = g2.SCC();
  for (auto grp : ans) {
    for (auto x : grp) {
      cout << x << ' ';
    }
    cout << '\n';
  }

  cout << "\nSCCs in third graph \n";
  Tarjan g3(7);
  g3.connect(0, 1);
  g3.connect(1, 2);
  g3.connect(2, 0);
  g3.connect(1, 3);
  g3.connect(1, 4);
  g3.connect(1, 6);
  g3.connect(3, 5);
  g3.connect(4, 5);
  ans = g3.SCC();
  for (auto grp : ans) {
    for (auto x : grp) {
      cout << x << ' ';
    }
    cout << '\n';
  }

  cout << "\nSCCs in fourth graph \n";
  Tarjan g4(11);
  g4.connect(0, 1);
  g4.connect(0, 3);
  g4.connect(1, 2);
  g4.connect(1, 4);
  g4.connect(2, 0);
  g4.connect(2, 6);
  g4.connect(3, 2);
  g4.connect(4, 5);
  g4.connect(4, 6);
  g4.connect(5, 6);
  g4.connect(5, 7);
  g4.connect(5, 8);
  g4.connect(5, 9);
  g4.connect(6, 4);
  g4.connect(7, 9);
  g4.connect(8, 9);
  g4.connect(9, 8);
  ans = g4.SCC();
  for (auto grp : ans) {
    for (auto x : grp) {
      cout << x << ' ';
    }
    cout << '\n';
  }

  cout << "\nSCCs in fifth graph \n";
  Tarjan g5(5);
  g5.connect(0, 1);
  g5.connect(1, 2);
  g5.connect(2, 3);
  g5.connect(2, 4);
  g5.connect(3, 0);
  g5.connect(4, 2);
  ans = g5.SCC();
  for (auto grp : ans) {
    for (auto x : grp) {
      cout << x << ' ';
    }
    cout << '\n';
  }

  return 0;
}
