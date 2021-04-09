const int MOD = 1e9+7;
struct Number {
    int val;
    int mod_cnt;
};
void add_self(Number& a, int b) {
        int c = a.val + b;
        while (c >= MOD) {
            c -= MOD;
            ++a.mod_cnt;
        }
        a.val = c;
}
Number max(Number& a, Number& b) {
    if (a.mod_cnt > b.mod_cnt) {
        return a;
    }
    else if (b.mod_cnt > a.mod_cnt) {
        return b;
    }
    else if (a.val > b.val) {
        return a;
    }
    else {
        return b;
    }
}