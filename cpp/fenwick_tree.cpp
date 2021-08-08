#include <bits/stdc++.h>
using namespace std;

class FenwickTree {
public:
    vector<int> values;
    FenwickTree(int n) {
        values = vector<int>(n + 1, 0);
    }
    int lowbit(int x) {
        return x & (~x + 1);
    }
    void update(int i, int val) {
        int delta = val - (query(i) - query(i-1));
        while (i < values.size()) {
            values[i] += delta;
            i += lowbit(i);
        }
    }
    int query(int i) {
        int ans = 0;
        while (i > 0) {
            ans += values[i];
            i -= lowbit(i);
        }
        return ans;
    }
    int range_query(int i, int j) {
        return query(j) - query(i - 1);
    }
};
// Note: all index is 1-based
int main() {
    FenwickTree* tree = new FenwickTree(10);

    for (int i = 1; i < 10; ++i) {
        tree->update(i, i);
    }
    
    cout << tree->query(5) << endl;

    cout << tree->query(6) << endl;

    cout << tree->range_query(3, 4) << endl;
}