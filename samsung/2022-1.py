n,m,h,k = map(int,input().split())
position = list(list(map(int,input().split())) for _ in range(m))
#x,y d d =1 좌우(항상오른쪽) 2, 상하(항상아래쪽)
# 0  열 1 행
tree = list(list(map(int,input().split())) for _ in range(h))
turn =1
EMPTY =-1
md = 3
ps= True
operate =1
ans =0
grid = [[EMPTY for _ in range(n)] for _ in range(n)]
player_x,player_y= n//2,n//2
info=[]
d_info=[ [0] * n for _ in range(n)]
back_d_info=[[0] * n for _ in range(n)]

# 0 오 1아래쪽 2왼쪽 3 위쪽
dx = [1, 0, -1, 0]
dy = [0, 1 ,0, -1]
def setting():
  for item in position:
    y = item[0] - 1
    x = item[1] - 1
    grid[y][x] = item[2] - 1

  curr_x, curr_y = n // 2, n // 2
  move_dir, move_num = 3, 1
  while curr_x or curr_y:
    for _ in range(move_num):
      d_info[curr_y][curr_x] = move_dir
      curr_x += dx[move_dir]
      curr_y += dy[move_dir]
      back_d_info[curr_y][curr_x] =move_dir + 2 if move_dir < 2 else move_dir - 2
      if not curr_x and not curr_y:
        break
    move_dir =(move_dir+1)%4
    if move_dir == 1 or move_dir == 3:
      move_num += 1

  for idx, item in enumerate(tree):
    tree[idx] = [item[0] - 1, item[1] - 1]


#턴제
#도망자 존재#도망자 겹치기 x
# 술래와의 거리가 3이하인 도망자만 움직임
#거리 x1-x2  + y1-y2
# 1칸 움직일때 격자를안 벗어남 술래있으면 x
# 술래 없으면 해당칸으로 이동 나무 상관 x
# 격자 벗어나면 방향 반대로  1칸움직일때 술래없으면 가기
def move():
  tmp = [ [EMPTY for _ in range(n)] for _ in range(n)]
  for i in range(n):
    for j in range(n):
      if grid[i][j] > EMPTY:
        direction = grid[i][j]
        px = j + dx[direction]
        py = i + dy[direction] 
        #거리
        road = abs(i-player_y)+abs(j-player_x)
        if road <= 3:
          if 0<= px < n and 0<=py<n:
            if (player_y,player_x) != (py,px):
              tmp [py][px] = direction
            else:
              tmp[i][j] = direction
          else:
            direction = (direction +2) %4
            tmp_x = j + dx[direction]
            tmp_y = i + dy[direction]
            if (player_y,player_x) != (tmp_y,tmp_x):
              tmp[tmp_y][tmp_x] = direction
            else:
              tmp[i][j] = direction
  for i in range(n):
    for j in range(n):
      grid[i][j] = tmp[i][j]

def get_seeker_dir():
  # 현재 술래의 위치를 불러옵니다.
  global player_y,player_x
  x, y = player_x,player_y
  global ps

  # 어느 방향으로 움직여야 하는지에 대한 정보를 가져옵니다.
  move = 0
  if ps:
    move = d_info[y][x]
  else:
    move = back_d_info[y][x]

  return move

def seeker_move():
  global ans, player_y, player_x, ps, turn,dx,ydy

  move_dir = get_seeker_dir()

  player_x, player_y = player_x + dx[move_dir], player_y + dy[move_dir]

  check_facing()


def check_facing():
  global ps,player_x,player_y,ans

  if (player_y,player_x) == (0, 0) and ps:
    ps = False
  if (player_y,player_x) == (n // 2, n // 2) and not ps:
    ps = True

def grab():
  global ans,turn
  seeker_move()
  run = 0
  move_dir = get_seeker_dir()
  for i in range(3):
    sight_x = player_x + i * dx[move_dir]
    sight_y = player_y + i * dy[move_dir]
    if [sight_y,sight_x] not in tree and 0 <= sight_y < n and 0 <= sight_x < n and grid[sight_y][sight_x] > EMPTY:
      grid[sight_y][sight_x]=EMPTY
      run +=1
  ans += (turn * run)

#술래 나선형으로 1칸씩 움직임 
#도망자 먼저
#1,1에 가는 순간 방향 바꿈 모서리에서도
#시야 내에 3칸 도망자 잡음 나무 랑 같으있으면 막음
#t x 도망자 잡힌수 
setting()
while (turn <= k):
  move()
  grab()
  turn +=1

print(ans)