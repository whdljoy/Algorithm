n,m,h,k = map(int,input().split())
position = list(list(map(int,input().split())) for _ in range(m))
#x,y d d =1 좌우(항상오른쪽) 2, 상하(항상아래쪽)
# 0  열 1 행
tree = list(list(map(int,input().split())) for _ in range(h))
turn =1
grid = [[EMPTY for _ in range(n)] for _ in range(n)]
EMPTY =-1
player_x,player_y= n//2,n//2
#술래 항상 중앙
def setting():
  for item in position:
    y = item[0] -1
    x = item[1] -1
    grid[y][x] = item[2]-1
# 0 오 1아래쪽 2왼쪽 3 위쪽 
dx = [1, 0, -1, 0]
dy = [0, 1 ,0, -1]
#턴제
#도망자 존재
def move():
  for i in range(n):
    for j in range(n):
      if grid[i][j] > EMPTY:
        direction = grid[i][j]
        px = j + dx[direction]
        py = i + dy[direction] 
        #거리
        road = abs(player_y - i)+abs(player_x-i)
        if road <= 3:
          if 0<= px < n and 0<=py<n and (player_y,player_x) != (py,px):
            grid [py][px] = direction
            grid[i][j] = EMPTY
          else:
            direction = (direction +2) %4
            tmp_x = j + dx[direction]
            tmp_y = i + dy[direction]
            if (player_y,player_x) != (tmp_y,tmp_x):
              grid[tmp_y][tmp_x] = direction
              grid[i][j] = EMPTY
            else:
              grid[i][j] = direction
          
#도망자 겹치기 x
# 술래와의 거리가 3이하인 도망자만 움직임 
#거리 x1-x2  + y1-y2
# 1칸 움직일때 격자를안 벗어남 술래있으면 x
# 술래 없으면 해당칸으로 이동 나무 상관 x

# 격자 벗어나면 방향 반대로  1칸움직일때 술래없으면 가기

#술래 나선형으로 1칸씩 움직임 
#도망자 먼저
#1,1에 가는 순간 방향 바꿈 모서리에서도
#시야 내에 3칸 도망자 잡음 나무 랑 같으있으면 막음
#t x 도망자 잡힌수 
setting()
# while (turn <= t):
  # if turn % 2==1:
  #   # 도망자
  # else:
    #술래
#   turn +=1