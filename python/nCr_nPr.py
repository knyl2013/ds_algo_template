mod = int(1e9+7)
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
facts = [1] * (n + 1)
for i in range(1, n+1):
		facts[i] = mul(facts[i - 1], i)
@lru_cache(None)
def nPr(n: int, r: int) -> int:
		return mul(facts[n], inv(facts[n - r]))
def nCr(n: int, c: int) -> int:
		return mul(nPr(n, r), facts[r])
