n , m = map(int,input().split())

grid = list(list(map(int,input().split())) for _ in range(n)) # 리브로수의 높이

rule = list(list(map(int,input().split())) for _ in range(m)) #각 년도의 이동규칙
special = [(n-1,0),(n-1,1),(n-2,0),(n-2,1)]  #(y,x)
RIGHT =1
UPPER_RIGHT =2
UPPER =3
UPPER_LEFT =4
LEFT =5
UNDER_LEFT =6
UNDER =7
UNDER_RIGHT = 8
# 1 오 2 오위 3 위 4 왼위 5 왼 6 왼아래 7아래 8오아래 이동방향 규칙별
#초기 특수영양제는 하단 4칸 고정
#d 이동방향  p 칸수
dx = [0,1,1,0,-1,-1,-1,0,1]
dy = [0,0,-1,-1,-1,0,1,1,1]

plus_x =[-1,1, -1, 1]
plus_y =[-1,-1, 1, 1]
def move(direction,position):
	global grid
	global special
	#이동
	for idx,item in enumerate(special):
		y = item[0] + dy[direction]*position
		x = item[1] + dx[direction]*position

		if x > n -1:
			x = x - n
		elif x < 0:
			x = n + x

		if y > n-1:
			y = y-n
		elif y< 0:
			y = n+y
		special[idx] = (y, x)
		# if direction == RIGHT:
		# 	x +=position
		# 	if x> n-1:
		# 		x= x - n
		# elif direction == UNDER_RIGHT:
		# 	x+=position
		# 	y-=position
		# elif direction == UPPER:
		# 	y -=position
		# 	if y< 0:
		# 		y= n+y
		# elif direction == UPPER_LEFT:
		# 	x-=position
		# 	y-=position
		# elif direction == LEFT:
		# 	x-=position
		# 	if x < 0:
		# 		x = n + x
		# elif direction == UNDER_LEFT:
		# 	x-=position
		# 	y+=position
		# elif direction == UNDER
		# elif direction == UNDER_RIGHT
		special[idx] = (y, x)
	#증가
	for item in special:
		grid[item[0]][item[1]] += 1
	for item in special:
		num_cross=0
		for a in range(4):
			px = item[1] + plus_x[a]
			py = item[0] + plus_y[a]
			if 0<= px <n and 0<= py <n and grid[py][px] >=1:
				num_cross+=1
		grid[item[0]][item[1]] += num_cross

def change():
	global special
	tmp =[]
	for i in range(n):
		for j in range(n):
			if grid[i][j] >=2 and (i,j) not in special:
				grid[i][j] -=2
				tmp.append((i,j))

	special = tmp

for d,p in rule:
	move(d,p)
	change()

print(sum(map(sum,grid)))