from collections import deque
n,m,v = map(int,input().split())

graph =[[] for _ in range(n+1)]

for _ in range(m):
  a,b = map(int,input().split())
  graph[a].append(b)
  graph[b].append(a)

for i in range(n+1):
  graph[i].sort()

def bfs(start):
  visited =[start]
  queue = deque()
  queue.append(start)
  while queue:
    cur_v = queue.popleft()
    for item in graph[cur_v]:
      if item not in visited:
        visited.append(item)
        queue.append(item)
  return visited
  


d_visited =[ ]
def dfs(cur_v):
  d_visited.append(cur_v)
  for item in graph[cur_v]:
    if item not in d_visited:
      # d_visited.append(item)
      dfs(item)

  
dfs(v)
print(*d_visited)
print(*bfs(v))