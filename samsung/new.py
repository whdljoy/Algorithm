# 4 4   m 개의 몬스터 1 팩맨

# 몬스터는 상하좌우 대각선 턴단위

# 턴

# 1 몬스터 복제
# 2 몬스터 이동

# 3 팩맨이동
# 4 몬스터 시체 소멸
# 5 몬스터 복제 완성

# 1 현재의 위치에서 자신과 같은 방향을 가진 몬스터 복제
# 부화 x  부화할시 동일하게 나옴

# 2 가진 방향대로 이동 - 칸에 몬스터 시체가 있거나 팩맨이있는경ㅇ
# 반시계방향으로 45도  갈수있다면 가고 없으면 45 원래대로 돌아오면 고정


# 3 팩맨 3 칸이동  상하 좌우 선택 몬스터를 가장많이 먹을수 있는방향
# 방향이 여러거맨 상 좌 하 우  반시계 우선순위 격자 바깥을 나가는 경우 고려 x
# 먹은 뒤 시체 남김 , 알은 먹지 않음 이동하는 과정에 있는 몬스터만 먹음 그자리에 있는 몬스터도 x


# 4몬스터 시체는 2턴동안 유지

# 5 알이었던 몬스터 부화 방향을 지닌채로

# 구해야 할거 모든턴이 진행된두 살아남은 몬스터 수
# 몬스터의 초기 위치와 팩맨의 초기 위치 같을 수 있음
from collections import deque

m, t = map(int, input().split())
pack_y, pack_x = map(int, input().split())  # 팩맨 위치
pack_y -=1
pack_x -=1
info = list(list(map(int, input().split())) for _ in range(m))
# info  0  몬스터 y  info 1 몬스터 x  info 2 몬스터 방향
# 1 위 2 왼위 3 왼 4 왼아래 5 아래 6 아래오 7오 8 오위  -반시계
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
# EGG = -1 # 방향과 함께면 빼서 방향값을 구한다
EMPTY = 0
MONSTER = 1

egg = {}
# egg = [[EMPTY for _ in range(4)] for _ in range(4)]
# 0
grid = [[EMPTY for _ in range(4)] for _ in range(4)]  # 몬스터 갯수

# 몬스터 정보
m_info = {}
dead_monster = {}


# 초기 셋팅

def set_monster():
	for i in range(4):
		for j in range(4):
			m_info[(i, j)] = []
			dead_monster[(i, j)] = [0, 0, 0]
	for item in info:
		monster_y = item[0] - 1
		monster_x = item[1] - 1
		monster_d = item[2]
		# 몬스터갯수
		grid[monster_y][monster_x] += 1
		# 몬스터 정보 저장
		m_info[(monster_y, monster_x)].append(monster_d)


# 1 현재의 위치에서 자신과 같은 방향을 가진 몬스터 복제
# 복제시도
def clone():
	# 어차피 턴 지날때마다 에그 초기화 되니 초기화
	for i in range(4):
		for j in range(4):
			egg[(i, j)] = []

	for item in m_info:
		for val in m_info[item]:
			egg[item].append(val)


# 2 가진 방향대로 이동 - 칸에 몬스터 시체가 있거나 팩맨이있는경ㅇ
# 반시계방향으로 45도  갈수있다면 가고 없으면 45 원래대로 돌아오면 고정

# 몬스터 이동
def move():
	global dx
	global dy
	print("check",grid)
	tmp = {}
	for i in range(4):
		for j in range(4):
			tmp[(i, j)] = []

	for item in m_info:
		for target in m_info[item]:
			target_y = item[0]
			target_x = item[1]
			target_d = target -1
			print("target", target_y, target_x,target_d)
			while (1):
				change_y = target_y + dy[target_d]
				change_x = target_x + dx[target_d]
				# 가려는 위치 찾기 #몬스터 시체 #팩맨
				if 0 <= change_y < 4 and 0 <= change_x < 4 and  ((change_y,change_x) != (pack_y,pack_x)) \
					and dead_monster[(change_y, change_x)][0] == 0 and dead_monster[(change_y, change_x)][1] == 0:
					grid[target_y][target_x] -= 1
					grid[change_y][change_x] += 1
					tmp[(change_y, change_x)].append(target_d)  # 이전 위치에서 뽑아서 다음 위치에 저장
					# print("target",target_x,target_y)
					print("change", change_y,change_x)
					print(target_d)
					break
				else:
					# 0 위 1 왼위 2 왼 3 왼아래 4 아래 5 아래오 6오 7 오위  -반시계
					target_d = (target_d + 1 ) % 8
					if target_d == target - 1:
						tmp[(target_y, target_x)].append(target) #이전 위치 그대로 넣어줌
						break
	for change in tmp:
		m_info[change] = tmp[change]
	print("move",grid)
# 3 팩맨 3 칸이동  상하 좌우 선택 몬스터를 가장많이 먹을수 있는방향
# 방향이 여러거맨 상 좌 하 우  반시계 우선순위 격자 바깥을 나가는 경우 고려 x
# 먹은 뒤 시체 남김 , 알은 먹지 않음 이동하는 과정에 있는 몬스터만 먹음 그자리에 있는 몬스터도 x
def eat():
	global pack_x, pack_y
	mx = [0, -1, 0, 1]
	my = [-1, 0, 1, 0]
	can = []
	best = (0, -1, -1, -1)
	for i in range(4):
		x1 = pack_x + mx[i]
		y1 = pack_y + my[i]
		if 0 <= x1 < 4 and 0 <= y1 < 4:
			for j in range(4):
				x2 = x1 + mx[j]
				y2 = y1 + my[j]
				if 0 <= x2 < 4 and 0 <= y2 < 4 and (x1,y1) != (x2,y2):
					for a in range(4):
						x3 = x2 + mx[a]
						y3 = y2 + my[a]
						if 0 <= x3 < 4 and 0 <= y3 < 4 and (x2,y2) != (x3,y3) and (x1 ,y1) != (x3,y3):
							m_num = 0
							if grid[y1][x1] != 0:
								m_num += grid[y1][x1]
							if grid[y2][x2] != 0:
								m_num += grid[y2][x2]
							if grid[y3][x3] != 0:
								m_num += grid[y3][x3]
							if best[0] <m_num:
								best = (m_num,i,j,a)

	# print(best)
	# 먹은 거 값 처리 및 시체 남기기 턴추가
	dir0, dir1, dir2, dir3 = best
	for move_dir in [dir1, dir2, dir3]:
		nx = pack_x + mx[move_dir ]
		ny = pack_y + my[move_dir ]
		if grid[ny][nx]:
			dead_monster[(ny, nx)][2] += grid[ny][nx]
			grid[ny][nx] = 0
			m_info[(ny, nx)] = []
		pack_x, pack_y = nx, ny
	# print(grid)
def monster_remove():
	for key in dead_monster:
		for i in range(2):
			dead_monster[key][i] = dead_monster[key][i + 1]
		dead_monster[key][2] = 0


def birth():
	for key in egg:
		for item in egg[key]:
			# print(item)
			m_info[key].append(item)
		grid[key[0]][key[1]] += len(egg[key])

turn = 0
set_monster()
for _ in range(t):
	# print(grid)
	clone()
	move()
	# print(grid)
	eat()
	# print(grid)
	monster_remove()
	# print(grid)
	birth()
	# print(grid)
# print(grid)
print(sum(map(sum, grid)))
