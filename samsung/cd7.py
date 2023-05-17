from collections import deque
n,m = map(int,input().split())

wall = list(list(map(int,input().split())) for _ in range(n))


def arr_copy(arr):
  total =[]
  for i in range(n):
    b=[]
    for j in range(len(arr[i])):
      b.append(arr[i][j])
    total.append(b)
  return total

def comb(arr,r):
  result =[]
  if r == 0:
    return [[]]
  
  for i in range(len(arr)):
    cur = arr[i]
    rest = arr[i+1:]
    for C in comb(rest,r-1):
      result.append([cur]+C)
  return result

fire = []
exist = []
#[y,x]
for i in range(n):
  for j in range(m):
    if wall[i][j]==0:
      cur = [i,j]
      exist.append(cur)
    elif wall[i][j]==2:
      fire.append([i,j])
ex = comb(exist,3)
result = []


dx = [-1,1,0,0]
dy = [0,0,-1,1]
def bfs(wall,visited):
  q = deque()
  for item in visited:
    q.append(item)
  while q:
    cur_fire  = q.popleft()
    for j in range(4):
      x = cur_fire[1] +dx[j]
      y = cur_fire[0]+dy[j]
      if -1<x<m and -1<y<n and wall[y][x] ==0: 
        q.append([y,x])
        wall[y][x] =2
  return wall

def search(wall):
  count =0
  for i in range(n):
    for j in range(m):
      if wall[i][j] ==0:
        count+=1
  return count


for item in ex:
  n_wall=arr_copy(wall)
  for posi in item:
    n_wall[posi[0]][posi[1]]=1
  result.append(search(bfs(n_wall,fire)))
print(max(result))
