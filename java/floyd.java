static void floyd(long[][] dp) {
        int n = dp.length;
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    long new_dist = dp[i][k] + dp[k][j];
                    if (new_dist < dp[i][j])
                        dp[i][j] = new_dist;
                }
            }
        }
    }
