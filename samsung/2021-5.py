from collections import deque
#정육면체는 항상 (0,0) 처음 항상 오른쪽
#인접의 경우

#1칸씩 움직임
#default 오른쪽

#판에있는 숫자보다 주사위 맞다는 면이 클경우 90도 회전
#격자 판을 벗어나게 된다면 반대로 돌아서 한칸움직임
#총 점수 구하기
n, m  = map(int,input().split())
grid = list(list(map(int,input().split())) for _ in range(n))
#0 오른쪽 1아래 2 왼 3 위
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
#
up , front, right = 1,2,3

current_x,current_y = 0,0 #(y,x)
direction = 0
def change_direction():
	global  current_x,current_y
	global  direction
	global up,front,right

	if direction == 0 : #오른쪽
		up, front ,right = 7-right, front ,up
	elif direction == 1: #아래쪽
		up, front, right = 7 - front, up, right
	elif direction ==2: #왼쪽
		up, front, right = right, front, 7 - up
	else: #위쪽
		up, front, right = front, 7 - up, right

	bottom = 7 - up
	if grid[current_y][current_x] > bottom: # 반시계
		direction = (direction -1 + 4 ) % 4
	elif grid[current_y][current_x] < bottom: #시게
		direction = (direction +1 ) %4


def bfs(target_num):
	global current_x, current_y
	visited = []
	q = deque()
	total = 0
	visited.append((current_y,current_x))
	q.append((current_y,current_x))
	while q:
		y,x = q.popleft()
		total += target_num
		for i in range(4):
			px = x + dx[i]
			py = y + dy[i]
			if 0 <= px < n and 0 <= py < n and grid[py][px] == target_num and (py,px) not in visited:
				visited.append((py,px))
				q.append((py,px))
	# total = target_num*  len(visited)
	return total

def move():
	global current_x, current_y
	global  direction
	position_x= current_x+dx[direction]
	position_y= current_y+dy[direction]

	if 0 <= position_x < n and 0 <= position_y < n:
		current_y, current_x = position_y, position_x
	else:
		if direction == 0 or direction == 1:
			direction += 2
		else:
			direction -= 2
		position_x = current_x + dx[direction]
		position_y = current_y + dy[direction]
		current_y, current_x = position_y, position_x


result = 0
for i in range(m):
	move()
	result += bfs(grid[current_y][current_x])
	change_direction()


print(result)
