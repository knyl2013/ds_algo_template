mod = int(1e9 + 7)
nax = int(2e5 + 10)


def mul(a: int, b: int) -> int:
    return (a * b) % mod


def pow(a: int, b: int) -> int:
    if b == 0:
        return 1
    if b % 2 == 1:
        return mul(a, pow(a, b - 1))
    else:
        c = pow(a, b // 2)
        return mul(c, c)


def inv(x: int) -> int:
    return pow(x, mod - 2)


fac = [1] * (nax + 1)
for i in range(1, nax):
    fac[i] = mul(fac[i - 1], i)


def nPr(n: int, r: int) -> int:
    return mul(fac[n], inv(fac[n - r]))


def nCr(n: int, r: int) -> int:
    return mul(nPr(n, r), inv(fac[r]))