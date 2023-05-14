from collections import deque
n = int(input()) # 무조건홀수
first = list(list(map(int,input().split())) for _ in range(n))
visited=[]
total = []
score=0
next_arr =[ [0 for _ in range(n)] for _ in range(n) ]
# bfs로 각각의 그룹을 찾는다
dx = [-1,0, 1,0]
dy = [0,-1,0,1]
def in_range(x,y):
	return 0<=x < n and 0<=y<n
def bfs(x,y, check):
	visited.append((y,x))
	group = []
	q = deque()
	q.append((y,x))
	group.append((y,x))

	while q:
		cur_y,cur_x = q.popleft()

		for a in range(4):
			px = cur_x + dx[a]
			py = cur_y + dy[a]
			if in_range(px,py) and first[py][px] == check and (py,px) not in group:
				q.append((py,px))
				visited.append((py,px))
				group.append((py,px))

	return group

# 각 그룹의 인접한 칸들을 구함
def neighbor():
	global total, total_neighbor
	for gr in total:
		tmp = []
		for each in gr:
			y,x = each
			for a in range(4):
				py = y + dy[a]
				px = x + dx[a]
				if in_range(py,px) and (py,px) not in gr and(py,px) not in tmp:
					tmp.append((py,px))
		total_neighbor.append(tmp)

def get_search():

	global total,total_neighbor,score
	for idx, gr in enumerate(total): #각 그룹의 옆에있는 것들
		another = total[idx+1:] #다른 그룹들
		for id,other in enumerate(another):
			cal = 0
			for item in gr:
				y, x = item
				for a in range(4):
					py = y + dy[a]
					px = x + dx[a]
					if in_range(py,px) and (py,px) in other:
						cal +=1
			if cal > 0:
				score = score + ((len(total[idx]) + len(other)) * get_val(total[idx]) * get_val(other) * cal)

def get_val(group):
	y,x=group[0]
	return first[y][x]


def get_group():
	global total
	for y in range(n):
		for x in range(n):
			if (y,x) not in visited:
				total.append(bfs(x,y,first[y][x]))

def rotate():
	global total,visited,first,next_arr
	total = []
	visited = []

	for i in range(n):
		for j in range(n):
			next_arr[i][j] = 0

	for j in range(n):
		next_arr[n-1-j][n//2] =first[n//2][j]
		next_arr[n//2][j] = first [j][n//2]


	sqaure_n = n // 2
	rotate_square(0, 0, sqaure_n)
	rotate_square(0, sqaure_n + 1, sqaure_n)
	rotate_square(sqaure_n + 1, 0, sqaure_n)
	rotate_square(sqaure_n + 1, sqaure_n + 1, sqaure_n)


	for i in range(n):
		for j in range(n):
			first[i][j] = next_arr[i][j]

def rotate_square(sx, sy, square_n):
	# 정사각형을 시계방향으로 90' 회전합니다.
	for x in range(sx, sx + square_n):
		for y in range(sy, sy + square_n):
			# Step 1. (sx, sy)를 (0, 0)으로 옮겨주는 변환을 진행합니다.
			ox, oy = x - sx, y - sy
			# Step 2. 변환된 상태에서는 회전 이후의 좌표가 (x, y) -> (y, square_n - x - 1)가 됩니다.
			rx, ry = oy, square_n - ox - 1
			# Step 3. 다시 (sx, sy)를 더해줍니다.
			next_arr[rx + sx][ry + sy] = first[x][y]


get_group()
get_search()
rotate()
get_group()
get_search()
rotate()
get_group()
get_search()
rotate()
get_group()
get_search()
rotate()
print(score)
