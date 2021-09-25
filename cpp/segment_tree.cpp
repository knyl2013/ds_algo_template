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
  vector<long long> inputs;
  Node* root;
  SegTree(vector<long long>& inp) {
    inputs = inp;
    int n = inputs.size();
    root = build_tree(0, n - 1);
  }
  Node* build_tree(int start, int end) {
    if (start > end) {
      return NULL;
    }
    if (start == end) {
      return new Node(start, end, inputs[start], NULL, NULL);
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
  // using GCD as example function
  long long func(long long smaller, long long bigger) {
    if (smaller > bigger) return func(bigger, smaller);
    if (smaller == 0L) return bigger;
    long remain = bigger % smaller;
    return func(remain, smaller);
  }
};

int main() {
  vector<long long> inputs = {6, 3, 3, 4, 5, 6};
  SegTree* tree = new SegTree(inputs);
  cout << tree->range_query(0, 2) << endl;  // expect 3
  tree->update(0, 9);
  cout << tree->range_query(0, 2) << endl;  // expect 3
  tree->update(0, 2);
  cout << tree->range_query(0, 2) << endl;  // expect 1
  tree->update(0, 0);
  cout << tree->range_query(0, 2) << endl;  // expect 3
  tree->deallocate();
}
