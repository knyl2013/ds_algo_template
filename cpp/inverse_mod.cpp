int mod = 1e9+7;
int add(int a, int b) {
    return (a + b) % mod;
}
int subtract(int a, int b) {
    return (a - b) % mod;
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
