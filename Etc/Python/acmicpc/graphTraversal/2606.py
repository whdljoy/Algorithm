from collections import deque

computer = int(input())
graph = [ [] for _ in range(computer+1)]
dual = int(input())

for _ in range(dual):
  n, m = map(int,input().split())
  graph[n].append(m)
  graph[m].append(n)
visited=[False] * (computer+1)
def bfs(start):
  answer =0
  search = deque()
  visited[start] = True
  search.append(start)
  while search:
    target = search.popleft()
    for item in graph[target]:
      if not visited[item]:
        answer+=1
        visited[item] =True
        search.append(item)
  return answer
      
print(bfs(1))