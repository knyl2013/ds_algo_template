from functools import lru_cache
from typing import List

nums = [1,5,1,2,4,5,6,8,1,2]

@lru_cache(None)
def sparse_table(i: int, j: int) -> int:
    if j == 0:
        return nums[i]
    return max(sparse_table(i, j - 1), sparse_table(i + (1<<(j-1)), j - 1))
@lru_cache(None)
def max_query(l: int, r: int) -> int:
    x = 0
    while 1 << (x+1) <= r-l+1:
        x += 1
    two_x = (1 << x)
    return max(sparse_table(l, x), sparse_table(r-two_x+1,x))

print(max_query(0, 3)) # expect 5
print(max_query(5, 7)) # expect 8