from collections import deque

n = int(input())
graph = [ [] for _ in range(n+1)]
answer = [0] * (n+1)  
visited=[ False ] *(n+1)

for i in range(n-1):
  a,b = map(int,input().split())
  graph[a].append(b)
  graph[b].append(a)


def bfs(start):
  visited[start]=True
  queue = deque()
  queue.append(start)
  while queue:
    cur_v=queue.popleft()
    for item in graph[cur_v]:
      if not visited[item]:
        answer[item] = cur_v
        queue.append(item)
        visited[item]=True 
bfs(1)

for i in range(2,n+1):
  print(answer[i])
