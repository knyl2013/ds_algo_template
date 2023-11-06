class SegTree: # This code is modified version from: https://www.geeksforgeeks.org/segment-tree-efficient-implementation/
	def func(self, a: int, b: int) -> int:
		return max(a, b) # Change here to be a different seg tree (e.g. max, min, or add)
	def __init__(self, n: int):
		self.n = n
		self.tree = [0] * (2 * self.n)
	def update(self, index: int, value: int):
		self.tree[index + self.n] = value; 
		index = index + self.n; 
		i = index; 
		while i > 1:
			self.tree[i >> 1] = self.func(self.tree[i], self.tree[i ^ 1]); 
			i >>= 1; 
	def query(self, l: int, r: int):
		res = 0;
		l += self.n;
		r += self.n + 1;
		while l < r : 
			if (l & 1) : 
				res = self.func(res, self.tree[l]); 
				l += 1
			if (r & 1) : 
				r -= 1; 
				res = self.func(res, self.tree[r]); 
			l >>= 1; 
			r >>= 1
		return res; 

# Test
arr = [1,2,3,4,5]
segTree = SegTree(len(arr))
for i in range(len(arr)):
	segTree.update(index = i, value = arr[i])
print(segTree.query(l = 0, r = 2)) # Expect 3
print(segTree.query(l = 1, r = 3)) # Expect 4
print(segTree.query(l = 0, r = 4)) # Expect 5