mod = int(1e9 + 7)


def add(a: int, b: int) -> int:
    c = a + b
    if c >= mod:
        c -= mod
    return c


def subtract(a: int, b: int) -> int:
    c = a - b
    if c < 0:
        c += mod
    return c


def mul(a: int, b: int) -> int:
    return (a * b) % mod


def pow(x: int, p: int) -> int:
    if p == 0:
        return 1
    if p % 2 == 1:
        return mul(x, pow(x, p - 1))
    tmp = pow(x, p // 2)
    return mul(tmp, tmp)


def inv(x: int) -> int:
    return pow(x, mod - 2)


def div(a: int, b: int) -> int:
    return mul(a, inv(b))

