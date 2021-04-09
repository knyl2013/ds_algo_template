int rand(int min, int max) {
    int range = max - min + 1;
    int num = rand() % range + min;
    return num;
}

vector<int> random_vec(int n, int min, int max) {
    vector<int> ans(n);
    for (int i = 0; i < n; ++i) {
        ans[i] = rand(min, max);
    }
    return ans;
}

vector<vector<int>> random_mat(int h, int w, int min, int max) {
    vector<vector<int>> mat(h, vector<int>(w));
    for (int i = 0; i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            mat[i][j] = rand(min, max);
        }
    }
    return mat;
}

string random_bin_str(int n) {
    string ans = "";
    while (n--) {
        ans.push_back(rand() % 2 ? '1' : '0');
    }
    return ans;
}