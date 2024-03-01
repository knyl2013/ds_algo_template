from typing import List
def computePrefixAlsoSuffix(s: str) -> List[bool]:
    def computeLPSArray(pat: str, M: int, lps: List[int]):
        len = 0  # length of the previous longest prefix suffix
        lps[0]
        i = 1
        while i < M:
            if pat[i] == pat[len]:
                len += 1
                lps[i] = len
                i += 1
            else:
                if len != 0:
                    len = lps[len - 1]
                else:
                    lps[i] = 0
                    i += 1
    M = len(s) 
    lps = [0] * M
    computeLPSArray(s, M, lps)
    res = [False] * M
    cur = M - 1
    while cur >= 0:
        res[cur] = True
        cur = lps[cur] - 1
    return res
res = computePrefixAlsoSuffix("abacaba")
print(res) # Expect [True, False, True, False, False, False, True]
