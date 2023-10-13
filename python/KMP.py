def KMPSearch(txt: str, pat: str) -> List[int]:
    def computeLPSArray(pat: List[int], M: int, lps: List[int]):
        len = 0  # length of the previous longest prefix suffix
        lps[0]  # lps[0] is always 0
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

    M = len(pat)
    N = len(txt)
    lps = [0] * M
    j = 0  # index for pat[]
    ans = []
    computeLPSArray(pat, M, lps)
    i = 0  # index for txt[]
    while i < N:
        if pat[j] == txt[i]:
            i += 1
            j += 1

        if j == M:
            ans.append(i - j)
            j = lps[j - 1]
        elif i < N and pat[j] != txt[i]:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1
    return ans
