#include <bits/stdc++.h>
using namespace std;
using ll = long long;
ll fun(ll smaller, ll bigger) { // using GCD as example, can be add, multiply, max, etc.
    if (smaller > bigger) return fun(bigger, smaller);
    if (smaller == 0L) return bigger;
    long remain = bigger % smaller;
    return fun(remain, smaller);
}
class SegTreeNode {
public:
    ll val;
    int start;
    int end;
    SegTreeNode* left;
    SegTreeNode* right;
    SegTreeNode(int s, int e, int v, SegTreeNode* l, SegTreeNode* r) {
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

class SegTree {
public:
    vector<ll> inputs;
    SegTreeNode* root;
    SegTree(vector<ll>& inp) {
        inputs = inp;
        int n = inputs.size();
        root = build_tree(0, n-1);
    }
    SegTreeNode* build_tree(int start, int end) {
        if (start > end) {
            return NULL;
        }
        if (start == end) {
            return new SegTreeNode(start, end, inputs[start], NULL, NULL);
        }
        int mid = (start + end) / 2;
        SegTreeNode* left = build_tree(start, mid);
        SegTreeNode* right = build_tree(mid+1, end);
        return new SegTreeNode(start, end, fun(left->val, right->val), left, right);
    }
    
    ll range_query(SegTreeNode* root, int start, int end) {
        if (root->start == start && root->end == end) {
            return root->val;
        }
        int mid = (root->start + root->end) / 2;
        bool on_left = end <= mid;
        bool on_right = start >= mid + 1;
        if (on_left) {
            return range_query(root->left, start, end);
        }
        else if (on_right) {
            return range_query(root->right, start, end);
        }
        else { // in between
            return fun(range_query(root->left, start, root->left->end), range_query(root->right, root->right->start, end));
        }
    }
    ll range_query(int start, int end) {
        return range_query(root, start, end);
    }
    void update(SegTreeNode* root, int index, ll val) {
        if (root->start == index && root->end == index) {
            root->val = val;
        }
        else {
            int mid = (root->start + root->end) / 2;
            if (index <= mid) {
                update(root->left, index, val);
            }
            else {
                update(root->right, index, val);
            }
            root->val = fun(root->left->val, root->right->val);
        }
    }
    void update(int index, ll val) {
        update(root, index, val);
    }
};

int main() {
    vector<ll> inputs = {6, 3, 3, 4, 5, 6};
    SegTree* tree = new SegTree(inputs);
    cout << tree->range_query(0, 2) << endl; // expect 3
    tree->update(0, 9);
    cout << tree->range_query(0, 2) << endl; // expect 3
    tree->update(0, 2);
    cout << tree->range_query(0, 2) << endl; // expect 1
    tree->update(0, 0);
    cout << tree->range_query(0, 2) << endl; // expect 3
}
