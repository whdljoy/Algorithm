

#플레이어는 한칸 이동 격자를 벗어나는 경우 반대 방향 1 순차적 이동
# 해당칸에 총이있다면 총획득 총을 가지고있다면 가지고있는 총 중 더쎈거 획득 하고 놔둠

# 해당칸에 플레이어가 있다면 싸움 초기능력치과 가지고 있는 공격력의 합이 크면 이김 같은 경우 높은 플레이어가 승리
# 이긴경우 초기능력치와 가지고있는 총의 공격력의 합의 차이 만큼 획득
# 진사람의 경우 총을 내려놓고 해당플레이어가 가지고있던 방향대로 한칸이동 - 다른플레이어가 있거나 격자 밖인경우 시계 방향으로 90도 회전하여
# 빈칸이 보이는 순간이동 총이있다면 높은 총을 획득 내려놓음
# 이긴 플레이어는 높은 총을 획득 하고 내려놓음

# n 격자 크기 m 플레이어 수 k 라운의수
n , m , k  = map(int,input().split())

grid = list(list(map(int,input().split())) for _ in range(n))
EMPTY = 0
# 0보다 큰수는 총의 공격력
NO_PLAYER = -1
player = list(list(map(int,input().split())) for _ in range(m))

dx = [0, 1,0,-1]
dy = [-1,0,1,0]  #상 우 하 좌

player_d = [0 for _ in range(m)] #방향
gun = [0 for _ in range(m)]
player_p = [(0,0) for _ in range(m)] # 위치
player_s = [0 for _ in range(m)] #능력치
ans = [ 0 for _ in range(m)]
gun_info={}
def in_range(x,y):
	return 0<=x<n and 0<=y<n


def in_gun(x,y):
	return len(gun_info[(y,x)]) > 0

def in_player(x,y):
	return (y,x) in player_p
def setting():
	for idx in range(m):
		y,x,d,s = player[idx]
		player_d[idx] = d
		player_s[idx] = s
		player_p[idx] =(y-1,x-1) #플레이어 번호

	for y in range(n):
		for x in range(n):
			gun_info[(y,x)] = []
	for y in range(n):
		for x in range(n):
			if grid[y][x] != EMPTY:
				gun_info[(y,x)].append(grid[y][x])

def search_player(x,y):
	for i in range(m):
		if (y,x) == player_p[i]:
			return i

def search_gun(x,y):
	max_gun =0
	idx =0
	for id,item in enumerate(gun_info[(y,x)]):
		if item > max_gun:
			max_gun=item
			idx =id
	return (max_gun,idx)
def fight(p1,p2):
	a1 = player_s[p1] + gun[p1]
	a2 = player_s[p2] + gun[p2]
	if a1 > a2: #합이 클경구
		return (p1, a1-a2,p2)
	elif a1 == a2: #같ㅇ을 경우 초기 플레이어 능력치
		if player_s[p1] >player_s[p2]:
			return (p1,0,p2)
		else:
			return (p2,0,p1)
	else:
		return (p2,a2-a1,p1)
def move():
	for idx,position in enumerate(player_p):
		y,x = position
		direction = player_d[idx]
		px = x + dx[direction]
		py = y + dy[direction]
		if in_range(px, py):
			condition(idx,px,py)
		else:  # 방향 전환
			direction = (direction + 2) % 4
			px = x + dx[direction]
			py = y + dy[direction]
			player_d[idx] = direction
			condition(idx,px,py)


def condition(idx,px,py):
	if not in_player(px, py):  # 플레이어가없다면
		# print("not in")
		player_p[idx] = (py, px)
		if in_gun(px, py):  # 총이있다면
			max_gun, gun_idx = search_gun(px, py)
			if gun[idx] > 0:  # 기존에 총을 가지고있다면
				if gun[idx] < max_gun:  # 더쎈총 선택
					tmp = max_gun
					gun_info[(py, px)][gun_idx] = gun[idx]
					gun[idx] = tmp
			else:
				gun[idx] = max_gun
				del gun_info[(py, px)][gun_idx]
		player_p[idx] = (py,px)

	else:
		# print("in")
		idx2 = search_player(px, py)
		winner_idx, score, loser_idx = fight(idx, idx2)
		ans[winner_idx] += score
		# print("check",winner_idx, score, loser_idx )
		if gun[loser_idx] > 0:  # 해당 총을 격자에 내려두고
			gun_info[(py, px)].append(gun[loser_idx])
			gun[loser_idx] = EMPTY
		for a in range(4):
			loser_d = (player_d[loser_idx] + a) % 4
			loser_x = px + dx[loser_d]
			loser_y = py + dy[loser_d]
			# print(loser_x,loser_y)
			if not in_range(loser_x, loser_y) or in_player(loser_x, loser_y):
				# print("continue")
				continue  # 플레이어가 있거나 격자 범위 밖인경우 다시 90도 이동
			else:
				if not in_player(loser_x, loser_y):
					if in_gun(loser_x, loser_y):  # 움직이려는데총이있다면
						mx_gun, gun_id = search_gun(loser_x, loser_y)
						gun[loser_idx] = mx_gun
						del gun_info[(loser_y, loser_x)][gun_id]
					player_p[loser_idx] = (loser_y, loser_x)
					player_d[loser_idx] = loser_d
				break
		player_p[winner_idx] = (py, px)
		if in_gun(px, py):
			winner_gun, winner_g_idx = search_gun(px, py)
			if gun[winner_idx] > 0:
				if gun[winner_idx] < winner_gun:  # 더쎈총 선택
					tmp = winner_gun
					gun_info[(py, px)][winner_g_idx] = gun[winner_idx]
					gun[winner_idx] = tmp
			else:
				gun[winner_idx] = winner_gun
				del gun_info[(py, px)][winner_g_idx]

#상 우 하 좌

setting()
for i in range(k):
	move()
	# print(gun)
	# print(player_s)
	# print(player_d)
	# print(player_p)
	# print(gun_info)
print(*ans)


