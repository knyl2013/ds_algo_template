static long nextHash(long pow, long hash, int left, int right)
{
	return (hash - left*pow)*p + right;
}  

static long hash(int start, int end) 
{
	long hash = 0;
	long mul=1;
	for (int i = end; i >= start; i--) {
		hash += arr[i]*mul;
		mul*=p;
	}
	return hash;
}   


/*usuage example*/
static boolean ok(int len)
{
	Map<Long, Integer> mp = new HashMap<>();
	int n = arr.length;
	long h=hash(0, len-1);
	long pow = 1;
	mp.put(h, 0);
	for(int l=1;l<len;l++) pow*=p;
	for(int i=1;i<=n-len;i++){
		h=nextHash(pow,h,arr[i-1],arr[i+len-1]);
		if (mp.containsKey(h)) {
			int start = mp.get(h);
			if (start + len < i) return true;
		}
		else {
			mp.put(h, i);
		}
	}
	return false;
}

