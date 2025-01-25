class Solution {
public:
    int MOD = 1e9 + 7;
    vector<int> facts, inv_facts;
    int add(int a, int b) {
        int c = a + b;
        if (c >= MOD) c -= MOD;
        return c;
    }
    int mul(int a, int b) {
        return (long long) a * b % MOD;
    }
    int my_pow(int x, int p) {
        if (p == 0) return 1;
        if (p % 2 == 0) {
            int c = my_pow(x, p / 2);
            return mul(c, c);
        } else {
            return mul(x, my_pow(x, p - 1));
        }
    }
    int my_inv(int x) {
        return my_pow(x, MOD - 2);
    }
    int permute(int n, int k) {
        return mul(facts[n], inv_facts[n - k]);
    }
    int choose(int n, int k) {
        return mul(n_permute_k(n, k), inv_facts[k]);
    }
    int minMaxSums(vector<int>& nums, int k) {
        int ans = 0, n = nums.size();
        sort(nums.begin(), nums.end());
        facts = vector<int>(n + 1);
        inv_facts = vector<int>(n + 1);
        facts[0] = inv_facts[0] = 1;
        for (int i = 1; i <= n; i++) {
            facts[i] = mul(facts[i - 1], i);
        }
        inv_facts[n] = my_inv(facts[n]);
        for (int i = n - 1; i >= 1; i--) {
            inv_facts[i] = mul(inv_facts[i + 1], i + 1);
        }
        for (int i = 0; i < n; i++) {
            ans = add(ans, mul(2, nums[i]));
            for (int j = 1; j <= i && j < k; j++) {
                ans = add(ans, mul(nums[i], choose(i, j)));
            }
            for (int j = 1; n-i-1 >= j && j < k; j++) {
                ans = add(ans, mul(nums[i], choose(n-i-1, j)));
            }
        }
        return ans;
    }
};