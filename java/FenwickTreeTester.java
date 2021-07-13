public class FenwickTreeTester {
	static class FenwickTree {
		int[] values;
		public FenwickTree(int n) 
		{
			values = new int[n + 1];
		}
		int lowbit(int x) 
		{
			return x & (~x + 1);
		}
		void update(int i, int val) 
		{
			int delta = val - (query(i) - query(i-1));
			while (i < values.length) {
				values[i] += delta;
				i += lowbit(i);
			}
		}
		int query(int i) 
		{
			int ans = 0;
			while (i > 0) {
				ans += values[i];
				i -= lowbit(i);
			}
			return ans;
		}
		int range_query(int i, int j) {
			return query(j) - query(i - 1);
		}
	}
	// Note: all index is 1-based
	public static void main(String[] args) 
	{
		FenwickTree tree = new FenwickTree(10);

		for (int i = 1; i <= 10; i++) {
			tree.update(i, i);
		}
		// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		System.out.println(tree.query(5)); // 15
		System.out.println(tree.query(6)); // 21
		System.out.println(tree.range_query(3, 4)); // 7
		
		tree.update(1, 10);
		// [10, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		System.out.println(tree.query(5)); // 24
		System.out.println(tree.query(1)); // 10
		System.out.println(tree.range_query(1, 10)); // 64
	}
}
