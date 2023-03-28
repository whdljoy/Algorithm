from collections import deque
n, m = map(int,input().split())

num = list( list(map(int,input().split())) for _ in range(n))
dx = [-1,1,0,0]
dy = [0, 0, -1,1]
visited =[[False]*m for _ in range(n)]
answer = [ [0]*m for _ in range(n)]
def bfs(x,y):
  q = deque()
  q.append([x,y])
  while q:
    cur_x,cur_y = q.popleft()
    for i in range(4):
      tx = cur_x +dx[i]
      ty = cur_y +dy[i]
      if 0<=tx<m and 0<=ty <n and num[ty][tx] != 0 and not visited[ty][tx]:
        answer[ty][tx] = answer[cur_y][cur_x]+1
        visited[ty][tx] = True
        q.append([tx,ty])


for i in range(n):
  for j in range(m):
    if num[i][j] ==2:
      bfs(j,i)
      answer[i][j]=0
      
for i in range(n):
  for j in range(m):
    if not visited[i][j] and num[i][j] != 0:
      answer[i][j] = -1
      
for i in range(n):
  print(*answer[i])
