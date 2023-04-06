# 1 플레이어는 상하좌우 중 칸수 만큼 몬스터 제거
# 2 비어 있는 공간칸큼 이동하여 채움 - 나선형 고려
# 3 연속된 몬스터의 종류가 4번 이상 나오면 제거  - 4번이상 나오는거 삭제할 때 점수구하기  갯수 곱하기 점수
# 4 비어 있는 공간만큼 채우기 4번이상 나오는거 없을때 까지 삭제
# 5 (총개수, 숫자크기) 로 바꾸어서 다시 미로속에 넣기  - 격자 범위 넣으면 나머지 배열 무시

# 플레이어 위치 n//2 , n//2고정
EMPTY = 0
ans =0
# n 격자 크기 m 총라운드 수
n,m = map(int,input().split())
player_x,player_y = n//2, n//2
grid = list(list(map(int,input().split())) for _ in range(n))
tmp = [ [0 for _ in range(n)] for _ in range(n)]
rule = list(list(map(int,input().split())) for _ in range(m))
# d - 방향  0: 오른쪽,1:아래 ,2: 왼쪽 ,3: 위쪽  p: 공격칸수
dx = [1,0,-1,0]
dy = [0,1,0,-1]
spatial = []
def copy_spatial():
	px = [-1,0,1,0]
	py = [0,1,0,-1]
	cur_x = n//2
	cur_y = n //2
	move_num =1
	move_dir = 0
	while(1):
		if cur_x or cur_y:
			break
		for _ in range(move_num):
			if grid[cur_y+py[move_dir]][cur_x+px[move_dir]]:
				spatial.append((cur_y+py[move_dir],cur_x+px[move_dir]))

			if cur_x or cur_y:
				break
		move_dir = (move_dir + 1) % 4
		if move_dir == 0 or move_dir == 2:
			move_num += 1
def clear(d,p): #1번 상하좌우중 칸수만큼 몬스터제거
	global grid,ans
	for i in range(1,p+1):
		tx = player_x +dx[i]*i
		ty = player_y +dy[i]*i
		ans += grid[ty][tx]
		grid[ty][tx] = EMPTY

	pull()
def pull(): # 비어있는 공간 채우기 n//2 보다  y크면
	for i in range(n):
		for j in range(n):
			tmp[i][j] = 0

	for sy,sx in spatial:
		idx=0
		if grid[sy][sx]:
			ty, tx = spatial[idx]
			tmp[ty][tx] = grid[sy][sx]
			idx +=1

	for i in range(n):
		for j in range(n):
			grid[i][j] = tmp[i][j]


def search(start_x,start_y):
	x = start_x
	y = start_y


copy_spatial()
for d,p in rule:
	clear(d,p)
