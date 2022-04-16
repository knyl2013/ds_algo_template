// ====================== Code Template: Begin ======================
// g++ -std=c++17 -Wshadow -Wall Solution.cpp && ./a.out < Solution.in
#define watch(x) cout << (#x) << " is " << (x) << endl
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
int ni() {
    int x;
    cin >> x;
    return x;
}
ll nl() {
    ll x;
    cin >> x;
    return x;
}
string ns() {
    string s;
    cin >> s;
    return s;
}
char nc() {
    char c;
    cin >> c;
    return c;
}
template <typename T>
std::ostream & operator << (std::ostream & os, const std::vector<T>& vec) {
    for(auto elem : vec)
        os << elem << " ";
    return os;
}
template <typename T>
std::ostream & operator << (std::ostream & os, const std::vector<vector<T>>& mat) {
    cout << "\n";
    for(auto elem : mat)
        os<<elem<<"\n";
    return os;
}
template <typename A, typename B>
std::ostream & operator << (std::ostream & os, const std::map<A, B>& mp) {
    os << "{\n";
    for (auto& t : mp)
        os << t.first << ": "  << t.second << "\n";
    os << "}";
    return os;
}
// ====================== Code Template: End ======================


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int T = ni();
    for (int t = 1; t <= T; t++) {
        int ans = 1;
        cout << "Case #" << t << ": " << ans << "\n";
    }
}
