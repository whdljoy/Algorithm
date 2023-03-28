from collections import deque
n,m = map(int,input().split())

total = list( list(map(int,input())) for _ in range(n))

check = [ [0]*m for _ in range(n)]


def bfs(x,y):
  check[y][x] = 1
  q = deque()
  q.append([x,y])
  dx =[-1,1,0,0]
  dy = [0,0,-1,1] 
  while q:
    cur_x,cur_y = q.popleft()
    for i in range(4):
      tx =cur_x + dx[i]
      ty =cur_y + dy[i]
      if tx>=0 and  ty>=0 and tx < m and ty<n and total[ty][tx] != 0 and check[ty][tx] == 0 :
        q.append([tx,ty])
        check[ty][tx]=check[cur_y][cur_x]+1
bfs(0,0)
print (check[n-1][m-1])
