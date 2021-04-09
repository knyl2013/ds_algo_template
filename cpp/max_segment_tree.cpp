#include <bits/stdc++.h>
using namespace std;

class SegTreeNode {
public:
    int val; // could be max/min/sum
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
};

class MaxSegTree {
public:
    vector<int> inputs;
    SegTreeNode* root;
    SegTree(vector<int>& inp) {
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
        return new SegTreeNode(start, end, max(left->val, right->val), left, right);
    }
    
    int range_query(SegTreeNode* root, int start, int end) {
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
            return max(range_query(root->left, start, root->left->end), range_query(root->right, root->right->start, end));
        }
    }
    int range_query(int start, int end) {
        return range_query(root, start, end);
    }
    void update(SegTreeNode* root, int index, int val) {
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
            root->val = max(root->left->val, root->right->val);
        }
    }
    void update(int index, int val) {
        update(root, index, val);
    }
};

int main() {
    vector<int> inputs = {6, 5, 3, 4, 5, 6};
    MaxSegTree* segTree = new MaxSegTree(inputs);
    cout << segTree->range_query(0, 4) << endl;
}