static class FenwickTree {
        long[] values;
        public FenwickTree(int n) 
        {
            values = new long[n + 1];
        }
        int lowbit(int x) 
        {
            return x & (~x + 1);
        }
        void update(int i, long val) 
        {
            long delta = val - (query(i) - query(i-1));
            while (i < values.length) {
                values[i] += delta;
                i += lowbit(i);
            }
        }
        long query(int i) 
        {
            long ans = 0;
            while (i > 0) {
                ans += values[i];
                i -= lowbit(i);
            }
            return ans;
        }
        long range_query(int i, int j) {
            return query(j) - query(i - 1);
        }
    }

