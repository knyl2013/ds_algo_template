import matplotlib.pyplot as plt
from matplotlib.patches import Rectangle


#define Matplotlib figure and axis
fig, ax = plt.subplots()

def add_rect(x1, y1, x2, y2):
	width, height = x2 - x1, y2 - y1
	ax.add_patch(Rectangle((x1, y1), width, height, alpha=1, fill=None))

#create simple line plot
ax.plot([0, 0],[0, 10], alpha=0)

inputs = """
0 0 10 10 
5 -5 15 15 
"""

lines = inputs.split('\n')
mini, maxi = float('inf'), float('-inf')
for line in lines:
	if not len(line):
		continue
	nums = [int(x) for x in line.split()]
	mini, maxi = min(mini, min(nums)), max(maxi, max(nums))
	add_rect(nums[0], nums[1], nums[2], nums[3])
ax.plot(mini, mini, maxi, maxi)
plt.show()