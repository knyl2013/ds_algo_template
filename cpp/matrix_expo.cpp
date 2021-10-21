#include <bits/stdc++.h>
using namespace std;
int n, mod = 1e9 + 7;
int add(int a, int b) { return (a + b >= mod) ? (a + b - mod) : (a + b); }
int mul(int a, int b) { return (long long)a * b % mod; }
typedef vector<int> vec;
typedef vector<vec> matrix;
matrix mul(const matrix& A, const matrix& B) {
  size_t n = A.size();
  matrix result(n, vec(n));
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 0; j < n; ++j) {
      for (size_t k = 0; k < n; ++k) {
        result[i][j] = add(result[i][j], mul(A[i][k], B[k][j]));
      }
    }
  }
  return result;
}
matrix expo(matrix const& M, unsigned n) {
  if (n < 1) throw;
  if (n == 1) return M;
  if (n % 2) return mul(M, expo(M, n - 1));
  matrix M2 = expo(M, n / 2);
  return mul(M2, M2);
}
int main() {
  int n;
  cin >> n;
  matrix mat = {{1, 1}, {1, 0}};
  mat = expo(mat, n);
  cout << mat[0][0];
}
