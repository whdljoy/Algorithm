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
#0 오른쪽 1왼쪽 2 위 3 아래
dx = [1,-1,0,0]
dy = [0,0,-1,1]
#
dice = [
	[1,1,1,1,1],
	[5,5,5,5,5],
	[1,4,6,3,1],
	[2,2,2,2,2],
  [1,1,1,1,1],
]
current_dice = [2,2] #(y,x)
current = [0,0] #(y,x)
direction = 0

def change_direction():
	global  current
	global  direction
	global current_dice
	dice_x =current_dice[1]+dx[direction]
	dice_y = current_dice[0] + dy[direction]
	if 0<=dice_x<5 and 0<=dice_y<5:
		current_dice=[dice_y,dice_x]
	elif dice>=5 and 0<=dice_y<5:
		current_dice=[2,1]
	elif dice_y >= 5 and 0 <= dice_x < 5:
		current_dice=[1,2]
	elif dice_x<0 and 0<=dice_y<5:
		current_dice=[2,3]
	elif dice_y < 0 and 0 <= dice_x < 5:
		current_dice = [3, 2]

	if grid[current[0]][current[1]]>dice[current_dice[0]][current_dice[1]]:
		if direction == 0 or direction ==1:
			direction+=2
		elif direction ==2:
			direction = 1
		else:
			direction= 0
	elif grid[current[0]][current[1]] < dice[current_dice[0]][current_dice[1]]:
		if direction == 0 or direction ==1:
			direction-=2
		elif direction ==0:
			direction = 3
		else:
			direction= 2


def bfs(start):
	visited = []
	q = deque()
	q.append(start)
	check = grid[start[0]][start[1]]
	while q:
		cur_v = q.popleft()
		for i in range(4):
			px = cur_v[0] + dx[i]
			py = cur_v[1] + dy[i]
			if 0<=px <n and 0<=py<n and grid[py][px] == check and [py,px] not in visited:
				visited.append([py,px])
				q.append([py,px])
	return  check * len(visited)
def move():
	global  current
	global  direction
	position_x= current[1]+dx[direction]
	position_y= current[0]+dy[direction]
	if 0<=position_x<n and 0<=position_y<n:
		current = [position_y,position_x]
	else:
		if direction == 0 or direction ==2:
			direction+=1
		else:
			direction-=1
		position_x = current[1] + dx[direction]
		position_y = current[0] + dy[direction]
		current = [position_y,position_x]


result =0
for i in range(m):
	move()
	result +=bfs(current)
	change_direction()
print(result)
