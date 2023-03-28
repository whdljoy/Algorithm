n = int(input())

grid =list (list(map(int,input().split())) for _ in range(n))

dx = [1,-1,0,0]
dy = [0,0,-1,1]
def copy(first):
  total =[]
  for i in range (n):
    b = []
    for j in first[i]:
      b.append(j)
    total.append(b)
  return total

def move(arr,dir):
  if dir ==0: #동쪽
    for i in range (n):
      for j in range(n-2,-1,-1):
        if arr[i][j] != 0:
          bx = j
          while(1):
            if -1< i + dy[dir] < n and -1 < bx+dx[dir] < n and arr[ i + dy[dir] ][ bx + dx[dir] ] == 0 :
              bx +=1                 
            else:
              if bx != j:
                arr[ i][ bx ] = arr[i][j]
                arr[i][j]=0              
              break        
  elif dir == 1: #서쪽
    for i in range (n):
      for j in range(n):
        if arr[i][j] != 0:
          bx = j
          while(1):
            if -1< i + dy[dir] < n and -1 < bx+dx[dir] < n and arr[ i + dy[dir] ][ bx + dx[dir] ] == 0 :
              bx -=1
            else:
              if bx != j:
                arr[ i][ bx ] = arr[i][j]
                arr[i][j]=0              
              break        
  elif dir == 2: #위로 밀기
    for i in range (n):
      for j in range(n):    
        if arr[i][j] != 0:
          by = i
          while(1):
            if -1< by + dy[dir] < n and -1 < j + dx[dir] < n and arr[ by + dy[dir] ][ j + dx[dir] ] == 0 :
              by -=1
            else:
              if by != i:
                arr[ by][ j ] = arr[i][j]
                arr[i][j]=0              
              break            
  elif dir == 3: #아래로 밀기
    for i in range (n-2,-1,-1):
      for j in range(n):
        if arr[i][j] != 0:
          by = i
          while(1):
            if -1< by + dy[dir] < n and -1 < j + dx[dir] < n and arr[ by + dy[dir] ][ j + dx[dir] ] == 0 :
              by +=1
            else:
              if by != i:
                arr[ by][ j ] = arr[i][j]
                arr[i][j]=0              
              break         
  return arr;

def check(arr,dir):
  if dir == 0: #동쪽
    for i in range (n):
      for j in range(n-1,0,-1):
        if arr[i][j] != 0 and arr[i][j] == arr[i][j-1]:
            arr[i][j] *=2
            arr[i][j-1]=0
  elif dir == 1: #서쪽
    for i in range (n):
      for j in range(n-1):
        if arr[i][j] != 0 and arr[i][j] == arr[i][j+1]:
            arr[i][j] *=2
            arr[i][j+1]=0
  elif dir == 2: #위로 밀기
    for i in range (n-1):
      for j in range(n):
        if arr[i][j] != 0 and arr[i][j] == arr[i+1][j]:
            arr[i][j] *=2
            arr[i+1][j]=0
  elif dir == 3: #아래로 밀기
    for i in range (n-1,0,-1):
      for j in range(n):        
        if arr[i][j] != 0 and arr[i][j] == arr[i-1][j]:
            arr[i][j] *=2
            arr[i-1][j]=0                                    
  return arr

re = 0
def bt(arr, dir, count):
  global re
  count+=1
  if count == 6:
    re= max(max(map(max,arr)), re)
    return 0
  for i in range(4):
    result = move(check(move(copy(arr),dir),dir),dir)
    bt(copy(result),i,count)
  return arr

for i in range(4):
  bt(copy(grid),i,0)
print(re)


