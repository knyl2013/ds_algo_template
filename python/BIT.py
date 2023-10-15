class BIT: # This code is contributed by sanjeev2552
	def __init__(self, n: int):
		self.tree1 = [0] * (n + 1)
		self.tree2 = [0] * (n + 1)
		self.n = n
	def _getSum(self, BITree: List[int], index: int) -> int:
		summ = 0 # Initialize result

		# index in BITree[] is 1 more than the index in arr[]
		index = index + 1

		# Traverse ancestors of BITree[index]
		while index > 0:

			# Add current element of BITree to sum
			summ += BITree[index]

			# Move index to parent node in getSum View
			index -= index & (-index)
		return summ
	def _updateBit(self, BITTree: List[int], index: int, val: int) -> None:
		# index in BITree[] is 1 more than the index in arr[]
		index = index + 1

		# Traverse all ancestors and add 'val'
		while index <= self.n:

			# Add 'val' to current node of BI Tree
			BITTree[index] += val

			# Update index to that of parent in update View
			index += index & (-index)
	# Returns the sum of array from [0, x]
	def _summation(self, x: int) -> int:
		return (self._getSum(self.tree1, x) * x) - self._getSum(self.tree2, x)
	def updateRange(self, val: int, l: int, r: int) -> None:
		# Update BIT1
		self._updateBit(self.tree1, l, val)
		self._updateBit(self.tree1, r + 1, -val)
		# Update BIT2
		self._updateBit(self.tree2, l, val * (l - 1))
		self._updateBit(self.tree2, r + 1, -val * r)
	def rangeSum(self, l: int, r: int) -> int:
		return self._summation(r) - self._summation(l - 1)

# Driver Code
if __name__ == "__main__":
	bit = BIT(5)
	n = 5

	# Add 5 to all the elements from [0,4]
	l = 0
	r = 4
	val = 5
	bit.updateRange(val, l, r)

	# Add 10 to all the elements from [2,4]
	l = 2
	r = 4
	val = 10
	bit.updateRange(val, l, r)

	# Find sum of all the elements from
	# [1,4]
	l = 1
	r = 4
	print("Sum of elements from [%d,%d] is %d" % (l, r, bit.rangeSum(l, r))) # Expect 50
