class KMP {
public:
    static vector<int> build(string p) {
        int n = p.size();
        vector<int> nxt = {0, 0};
        for (int i = 1, j = 0; i < n; ++i) {
            while (j > 0 && p[i] != p[j]) {
                j = nxt[j];
            }
            if (p[i] == p[j]) j++;
            nxt.push_back(j);
        }
        return nxt;
    }
    static vector<int> match(string s, string p) {
        int n = s.size(), m = p.size();
        vector<int> nxt = build(p);
        vector<int> ans;
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && p[j] != s[i]) {
                j = nxt[j];
            }
            if (s[i] == p[j]) j++;
            if (j == m) {
                ans.push_back(i - m + 1);
                j = nxt[j];
            }
        }
        return ans;
    }
};
