#1 나무성장 상하좌우 4칸의 나무 갯수 만큼 성장 동시에
#2 모든 나무들은 4개의 칸중 벽, 다른 나무,제초제 없는 모든 칸에 번식진행
# 각칸의 나무 그루수에서 총 번식이 가능한 칸의 갯수만큼 나누어진 그루수만큼 번식되며 나눌때 생기는 나머지는 버린다. 동시에 일어난다

#3 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다. 나무가 없는 칸에 제초제를 뿌리면 박멸되는 나무가 전혀 없는 상태로 끝이 나지만,
#나무가 있는 칸에 제초제를 뿌리게 되면 4개의 대각선 방향으로 k칸만큼 전파되게 됩니다
#단 전파되는 도중 벽이 있거나 나무가 아얘 없는 칸이 있는 경우, 그 칸 까지는 제초제가 뿌려지며 그 이후의 칸으로는 제초제가 전파되지 않습니다.
#제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년째가 될 때 사라지게 됩니다. 제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지됩니다. k만큼 대각선으로 버짐

#만약 박멸시키는 나무의 수가 동일한 칸이 있는 경우에는 행이 작은 순서대로, 만약 행이 같은 경우에는 열이 작은 칸에 제초제를 뿌리게 됩니다.

WALL = -1
EMPTY = 0
n,m ,k,c =map(int,input().split())

tree = list(list(map(int,input().split())) for _ in range(n))
med = [ [0 for _ in range(n)] for _ in range(n)]

dx = [-1,0,1,0]
dy = [0,-1,0,1]
ans =0
def in_range(x,y):
	return 0<= x <n and 0<=y<n
# 범위 내이고
def growth(): #그 자리에서 성장하는 거니까 갯수에 맞게 상관없다.
	global tree
	for i in range(n):
		for j in range(n):
			can =0
			if tree[i][j]>0:
				for a in range(4):
					px = j + dx[a]
					py = i +dy[a]
					if in_range(px,py) and tree[py][px] > 0 :
						can+=1
				tree[i][j] += can

def spread():
	global tree
	temp = [ [0 for _ in range(n)] for _ in range(n)]
	for y in range(n):
		for x in range(n):
			temp[y][x] = tree[y][x]

	for y in range(n):
		for x in range(n):
			if tree[y][x] > 0:
				can = 0
				cur_growth = 0
				for a in range(4):
					px = x + dx[a]
					py = y + dy[a]
					#벽이 아니고 다른 나무가 없다 #제초제도 없다
					if in_range(px, py) and tree[py][px] == 0 and med[py][px] == 0:
						can+=1
				if can > 0:
					cur_growth = tree[y][x] // can
					for a in range(4):
						px = x + dx[a]
						py = y + dy[a]
						if in_range(px, py) and tree[py][px] == 0 and med[py][px] == 0:
							temp[py][px] += cur_growth

	for y in range(n):
		for x in range(n):
			tree[y][x] = temp[y][x]
#좌상 우상 좌하 우하
cx = [-1,1,-1,1]
cy = [-1,-1,1,1]
def kill():
	global tree,med,k,c,ans
	best = (0,-n,-n)# 행이 작은 순서기 때문에 죽일 수 있는게 0이 나오더라도 해당 칸을 선택한다
	temp = [ [0 for _ in range(n)] for _ in range(n)]
	for y in range(n):
		for x in range(n):
			if tree[y][x] >0:
				can = tree[y][x]
				for a in range(4):
					for dist in range(1,k+1):
						px = x + dist * cx[a]
						py = y + dist * cy[a]
						if in_range(px,py):
							if tree[py][px] <1 :
								break
							else:
								can+= tree[py][px]
				if best < (can,-y,-x):
					best = (can,-y,-x)
	if best != (0,-n,-n):
		total=best[0]
		ans += total
		target_x = -best[2]
		target_y = -best[1]
		tree[target_y][target_x] = 0
		med[target_y][target_x] = c
		for a in range(4):
			for dist in range(1,k+1):
				px = target_x + dist * cx[a]
				py = target_y + dist * cy[a]
				if in_range(px,py):
					if tree[py][px] < 1: #비어있거냐 벽일때
						med [py][px] = c
						break
					else:
						med[py][px] = c
						tree[py][px] = 0
def destroy():
	for i in range(n):
		for j in range(n):
			if med[i][j] >0:
				med[i][j] -=1

for _ in range(m):
	growth()
	spread()
	destroy()
	kill()

print(ans)