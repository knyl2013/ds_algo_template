static double harmonicSmall(long n)
{
	double ans = 0;
	for (long i = 1; i <= n; i++) ans += 1.0 / i;
	return ans;
}
static double harmonic(long n)
{
	if (n <= (int)1e8) return harmonicSmall(n);
	final double EM = 0.57721566490153286060651209008240243104215933593992;
	return Math.log(n) + EM + (1.0 / (2*n)) - (1.0 / (12*(n*n)));
}
