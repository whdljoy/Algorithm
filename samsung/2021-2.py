
from collections import deque
RED = 0
STONE = -1
EMPTY = -2
n , m  = map(int,input().split())
# n 격자 크기 m 폭탄 종류    -1 == 검은돌  0 빨간색 돌 1 이상 m 이하의 숫자는 각자의 돌
grid = list(list(map(int,input().split())) for _ in range(n))
dx = [-1,1,0,0]
dy = [0,0,-1,1]
firstBest =(-1,-1,-1,-1)
bestX = -1
bestY = -1
def bfs(cur):
	global grid
	visited = []
	q = deque()
	q.append(cur)
	visited.append(cur)
	color = grid[cur[0]][cur[1]]  #
	while q:
		cur_v = q.popleft()
		for i in range(4):
			py = cur_v[0] + dy[i]
			px = cur_v[1] + dx[i]
			if -1<px< n and -1<py< n and (py,px) not in visited and (grid[py][px] == color or grid[py][px] ==0):
				visited.append((py,px))
				q.append((py,px))
	return visited
#양수 , 음수, 양수(행) 음수(열)
def getBest(road,x,y):
	global firstBest
	global bestX
	global bestY
	numRed=0

	standard = (-1,-1)
	if len(road) >1:
		for item in road:
			if grid[item[0]][item[1]] == RED:
				numRed+=1
			if grid[item[0]][item[1]] != RED :
				if (item[0],-item[1]) >standard:
					standard = (item[0],-item[1])
		if firstBest < (len(road),-numRed,standard[0],standard[1]):
			firstBest =(len(road),-numRed,standard[0],standard[1])
			bestX =x
			bestY =y
def execute():
	global grid
	for i in range(n):
		for j in range(n):
			if grid[i][j] >= 1:
				getBest(bfs((i,j)),j,i)

def gravity():
	for i in range(n):
		for j in range(n-1,-1,-1):
			if grid[j][i] != -1 and grid[j][i] !=-2:
				plus = 1 # 총가야될수는 -1
				while(1):
					if (j+plus) < n and grid[j+plus][i] == -2:
						plus += 1
					else:
						break
				if plus-1 > 0:
					grid[j+plus-1][i] = grid[j][i]
					grid[j][i] = -2

def rotate(): #반시계 방향 90
	global grid
	r_grid =[[0]*n for _ in range(n)]
	for i in range(n):
		target = grid[i]
		for idx,item in enumerate(target):
			r_grid[n-1-idx][i] = item
	grid = r_grid

def change():
	global bomb_val
	# 터트림 터트리면 -2
	target = bfs((bestY,bestX))
	for item in target:
		x = item[1]
		y = item[0]
		grid[y][x] = EMPTY
	gravity()
	rotate()
	gravity()
	bomb_val += len(target)**2


bomb_val =0

while(1):
	firstBest =(-1,-1,-1,-1)
	bestX = -1
	bestY = -1
	execute()
	if bestY ==-1 and bestY ==-1 :
		print(bomb_val)
		break
	else:
		change()



