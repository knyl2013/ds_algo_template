int mod = 1e9+7;
int add(int a, int b) {
    int c = a + b;
    if (c >= mod) c -= mod;
    return c;
}
int mul(int a, int b) {
    return (ll) a * b % mod;
}
int my_pow(int a, int b) {
    if (b == 0) return 1;
    if (b % 2 == 1) return mul(a, my_pow(a, b-1));
    else {
        int c = my_pow(a, b/2);
        return mul(c, c);
    }
}
int my_inv(int x) {
    return my_pow(x, mod-2);
}

vector<ll> fac;
void calc_fac(int x) {
    fac.resize(x + 1, 0);
    fac[0] = 1;
    for (int i = 1; i <= x; ++i) {
        fac[i] = mul(fac[i-1], i);
    }
}
ll nPr(int n, int r) {
    return mul(fac[n], my_inv(fac[n-r]));
}
ll nCr(int n, int r) {
    return mul(nPr(n, r), my_inv(fac[r]));
}