#define watch(x) cout << (#x) << " is " << (x) << endl
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
void swap(vector<int>& arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
void reverse(vector<int>& arr, int start, int end) {
    while (start < end) {
        swap(arr, start++, end--);
    }
}
bool next_permute(vector<int>& arr) {
    int n = arr.size();
    int largest_idx = n-1;
    for (int i = n-2; i >= 0; --i) {
        if (arr[i] > arr[largest_idx]) {
            largest_idx = i;
        }
        else if (arr[i] < arr[largest_idx]) {
            int smallest_gt = largest_idx;
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] > arr[i] && arr[j] < arr[smallest_gt]) {
                    smallest_gt = j;
                }
            }
            swap(arr, i, smallest_gt);
            // sort(arr.begin() + i + 1, arr.end());
            reverse(arr, i+1, n-1);
            return true;
        }
    }
    return false;
}
void permute(vector<int>& arr) {
    sort(arr.begin(), arr.end());
    do {
        for (int x : arr) {
            cout << x << " ";
        }
        cout << "\n";
    } while (next_permute(arr));
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    vector<int> arr = {1,2,3,4,5};
    permute(arr);
}
