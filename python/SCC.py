from collections import defaultdict

class Graph:
	def __init__(self, vertices):
		# No. of vertices
		self.V = vertices
		# default dictionary to store graph
		self.graph = defaultdict(list)
		self.Time = 0

	# function to add an edge to graph
	def addEdge(self, u, v):
		self.graph[u].append(v)

	def SCCUtil(self, u, low, disc, stackMember, st, ans):
		disc[u] = self.Time
		low[u] = self.Time
		self.Time += 1
		stackMember[u] = True
		st.append(u)

		for v in self.graph[u]:

			# If v is not visited yet, then recur for it
			if disc[v] == -1:
				self.SCCUtil(v, low, disc, stackMember, st, ans)
				low[u] = min(low[u], low[v])

			elif stackMember[v] == True:
				low[u] = min(low[u], disc[v])

		# head node found, pop the stack and print an SCC
		w = -1 # To store stack extracted vertices
		if low[u] == disc[u]:
			ans.append([])
			while w != u:
				w = st.pop()
				ans[-1].append(w)
				stackMember[w] = False
			ans[-1].reverse()

	# The function to do DFS traversal.
	# It uses recursive SCCUtil()

	def SCC(self):
		disc = [-1] * (self.V)
		low = [-1] * (self.V)
		stackMember = [False] * (self.V)
		st = []
		ans = []
		for i in range(self.V):
			if disc[i] == -1:
				self.SCCUtil(i, low, disc, stackMember, st, ans)
		return ans
