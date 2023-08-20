from typing import List

class BIT:
    def __init__(self, arr: List[int]):
        self.n = len(arr)
        self.bit = [0] * (self.n + 1)
        for i in range(self.n):
            self.add(i, arr[i])

    def sum(self, index: int) -> int:
        _sum = 0
        index += 1
        while index > 0:
            _sum += self.bit[index]
            index = index - (index & (-index))
        return _sum

    def range_sum(self, left: int, right: int) -> int:
        return self.sum(right) - self.sum(left - 1)

    def add(self, index: int, delta: int):
        index += 1
        while index <= self.n:
            self.bit[index] += delta
            index = index + (index & (-index))