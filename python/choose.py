MOD = 10**9 + 7

MAXN = 10**6

fac = [0] * (MAXN + 1)
inv = [0] * (MAXN + 1)


def exp(x: int, n: int, m: int) -> int:
    """Computes x^n modulo m in O(log p) time."""
    x %= m  # note: m * m must be less than 2^63 to avoid ll overflow
    res = 1
    while n > 0:
        if n % 2 == 1:
            res = (res * x) % m
        x = (x * x) % m
        n //= 2
    return res


def factorial(p: int):
    """Precomputes n! from 0 to MAXN."""
    global fac
    fac[0] = 1
    for i in range(1, MAXN + 1):
        fac[i] = (fac[i - 1] * i) % p


def inverses(p: int):
    """
    Precomputes all modular inverse factorials from 0 to MAXN in O(n + log p) time
    """
    global inv
    inv[MAXN] = exp(fac[MAXN], p - 2, p)
    for i in range(MAXN, 0, -1):
        inv[i - 1] = (inv[i] * i) % p


def choose(n: int, r: int, p: int):
    """Computes nCr mod p"""
    return fac[n] * inv[r] % p * inv[n - r] % p


factorial(MOD)
inverses(MOD)
