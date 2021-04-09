void test_case(int actual, int expected) {
    if (actual == expected) {
        cout << "Correct\n";
    }
    else {
        cout << "Wrong. Expected: " << expected << ", Got: " << actual << "\n";
    }
}