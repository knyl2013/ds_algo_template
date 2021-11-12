#include <bits/stdc++.h>
using namespace std;
class SegTree {
 public:
  class Node {
   public:
    long long val;
    int start;
    int end;
    Node* left;
    Node* right;
    Node(int s, int e, int v, Node* l, Node* r) {
      start = s;
      end = e;
      val = v;
      left = l;
      right = r;
    }
    void deallocate() {
      if (left != NULL) left->deallocate();
      if (right != NULL) right->deallocate();
      free(this);
    }
  };
  vector<long long> vals;
  Node* root;
  SegTree(int n) {
    vals = vector<long long>(n);
    root = build_tree(0, n - 1);
  }
  Node* build_tree(int start, int end) {
    if (start > end) {
      return NULL;
    }
    if (start == end) {
      return new Node(start, end, vals[start], NULL, NULL);
    }
    int mid = (start + end) / 2;
    Node* left = build_tree(start, mid);
    Node* right = build_tree(mid + 1, end);
    return new Node(start, end, func(left->val, right->val), left, right);
  }
  long long range_query(Node* root, int start, int end) {
    if (root->start == start && root->end == end) {
      return root->val;
    }
    int mid = (root->start + root->end) / 2;
    bool on_left = end <= mid;
    bool on_right = start >= mid + 1;
    if (on_left) {
      return range_query(root->left, start, end);
    } else if (on_right) {
      return range_query(root->right, start, end);
    } else {  // in between
      return func(range_query(root->left, start, root->left->end),
                  range_query(root->right, root->right->start, end));
    }
  }
  long long range_query(int start, int end) {
    return range_query(root, start, end);
  }
  void update(Node* root, int index, long long val) {
    if (root->start == index && root->end == index) {
      root->val = val;
    } else {
      int mid = (root->start + root->end) / 2;
      if (index <= mid) {
        update(root->left, index, val);
      } else {
        update(root->right, index, val);
      }
      root->val = func(root->left->val, root->right->val);
    }
  }
  void update(int index, long long val) { update(root, index, val); }
  void deallocate() { root->deallocate(); }
  long long func(long long a, long long b) { return max(a, b); }
};

int main() {
  vector<long long> inputs = {6, 3, 3, 4, 5, 6};
  int n = inputs.size();
  SegTree tree(n);
  for (int i = 0; i < n; ++i) {
    tree.update(i, inputs[i]);
  }
  cout << tree.range_query(0, n - 1) << endl;  // expect 6
  cout << tree.range_query(1, 4) << endl;      // expect 5
  tree.update(4, 7);
  cout << tree.range_query(1, 4) << endl;  // expect 7
}
