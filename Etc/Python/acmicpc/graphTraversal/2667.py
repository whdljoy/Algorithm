from collections import deque
n = int(input())

miro = list( list(map(int,input())) for _ in range(n))

visited = [ [False]*n for _ in range(n)]

dx = [ -1, 1, 0, 0]
dy = [0 , 0, -1, 1]
answer=[]
def bfs(x,y):
    q = deque()
    num =0
    q.append([x,y])
    visited[y][x]=True
    while q:
      num+=1
      cur_x,cur_y= q.popleft()
      for i in range(4):
        tx = cur_x + dx[i]
        ty= cur_y +dy[i]
        if tx >=0 and ty >=0 and tx<n and ty <n and miro[ty][tx] == 1 and not visited[ty][tx]:
          q.append([tx,ty])
          visited[ty][tx]=True
    return num
      
for i in range(n):
  for j in range(n):
    if miro [i][j] != 0 and not visited[i][j]:
      answer.append(bfs(j,i))

print(len(answer))
answer.sort()
for i in range(len(answer)):
  print(answer[i])