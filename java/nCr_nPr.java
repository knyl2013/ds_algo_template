static int[] facts;
static int mod = (int) 1e9+7;
static int mul(int a, int b)
{
    return (int) ((long) a * b % mod);
}
static int pow(int a, int b)
{
    if (b == 0) return 1;
    if (b % 2 == 1) return mul(a, pow(a, b - 1));
    int c = pow(a, b / 2);
    return mul(c, c);
}
static int inv(int x)
{
    return pow(x, mod-2);
}
static void calc_fac(int m)
{
    facts = new int[m + 1];
    facts[0] = 1;
    for (int i = 1; i <= m; i++) {
        facts[i] = mul(facts[i-1], i);
    }
}
static int nPermuteK(int n, int k)
{
    return mul(facts[n], inv(facts[n-k]));
}
static int nChooseK(int n, int k) 
{
    return mul(nPermuteK(n, k), inv(facts[k]));
}