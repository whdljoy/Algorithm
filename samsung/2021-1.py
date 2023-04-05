n = int(input())

grid = list( list(map(int,input().split())) for _ in range(n*n))

status = [[0]*n for i in range(n)]
dx = [-1,1,0,0]
dy = [0,0,1,-1]

best = (-1,-1,-1,-1)
def condition(favor):
	global best
	global status
	# 가능한 빈칸의 가능성 구하기
	for i in range(n):
		for j in range(n):
			if status[i][j]==0:
				each = getBest(i,j,favor)
				if best < each:
					best = each



def getBest(y,x,favor):
	global status
	friendNum =0
	emptyNum=0
	for a in range(4):
		px = x + dx[a]
		py = y + dy[a]
		if -1<px<n and -1<py<n and status[py][px] in favor:
			friendNum+=1
		if -1<px<n and -1<py<n and status[py][px] ==0:
			emptyNum+=1
	return (friendNum,emptyNum,-y,-x)

for i in range(n*n):
	best = (-1,-1,-1,-1)
	student = grid[i][0]
	favor = grid[i][1:]
	condition(favor)
	cur_x = -best[3]
	cur_y = -best[2]
	status[cur_y][cur_x] = student

total = 0
score = [0 , 1 , 10 , 100 , 1000]

for i in range(n):
	for j in range(n):
		favor2=[]
		cur = status[i][j]
		for c in range(n**2):
			if grid[c][0] == cur:
				favor2 = grid[c][1:]
				break
		num = 0
		for a in range(4):
			px = j+dx[a]
			py = i+dy[a]
			if 0 <= px< n and 0<= py < n and status[py][px] in favor2:
				num += 1
		total += score[num]
print(total)

