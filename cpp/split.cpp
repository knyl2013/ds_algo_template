#include <bits/stdc++.h>
using namespace std;


vector<string> split(const string& s, char delim) {
    string tmp; 
    stringstream ss(s);
    vector<string> words;

    while(getline(ss, tmp, delim)){
        words.push_back(tmp);
    }
    return words;
}

vector<string> split(const string& str, const string& delim)
{
    vector<string> tokens;
    size_t prev = 0, pos = 0;
    do
    {
        pos = str.find(delim, prev);
        if (pos == string::npos) pos = str.length();
        string token = str.substr(prev, pos-prev);
        if (!token.empty()) tokens.push_back(token);
        prev = pos + delim.length();
    }
    while (pos < str.length() && prev < str.length());
    return tokens;
}

    

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    vector<string> test = split("ab ab ab", " ");
    cout << test[0] << endl;
}