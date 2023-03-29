n, m =map(int,input().split())

tetris = list(list(map(int,input().split())) for _ in range(n))

def search(start):
  x = start[0]
  y = start[1]
  #case 1 일자
  total = 0
  if -1<x+3<m:
    cur = 0
    for i in range(4):
      cur += tetris[y][x+i]
    total= max(cur,total)
  if -1<y+3< n:
    cur = 0
    for i in range(4):
      cur += tetris[y+i][x]
    total = max(cur,total)
  #case 2 사각
  if -1 <x+1 <m and-1<y+1< n:
    cur = 0
    cur+= tetris[y][x]
    cur+= tetris[y+1][x]
    cur+= tetris[y][x+1]
    cur+= tetris[y+1][x+1]
    total = max(cur,total)
    
  #case 3  - ㄴ
  if -1< y+2<n and -1<x+1< m:
    cur = 0
    for i in range(3):
      cur+=tetris[y+i][x]
    cur += tetris[y+2][x+1]
    total = max(cur,total)
    # 뒤집기
    cur = 0
    for i in range(3):
      cur+=tetris[y+i][x+1]
    cur += tetris[y+2][x]
    total = max(cur,total)   
    
    #180
    cur = 0
    for i in range(3):
      cur+=tetris[y+i][x+1]
    cur += tetris[y][x]
    total = max(cur,total)
    
    #뒤집기
    cur = 0
    for i in range(3):
      cur+=tetris[y+i][x]
    cur += tetris[y][x+1]
    total = max(cur,total)       
    
  # + 90  
  if -1< x+2<m and -1<y+1< n:
    
    cur = 0
    for i in range(3):
      cur+=tetris[y][x+i]
    cur += tetris[y+1][x]
    total = max(cur,total)  
    #뒤집기
    cur = 0
    for i in range(3):
      cur+=tetris[y][x+i]
    cur += tetris[y+1][x+2]
    total = max(cur,total)    
  
    # 270 
    cur = 0
    for i in range(3):
      cur+=tetris[y+1][x+i]
    cur += tetris[y][x+2]
    total = max(cur,total)
    # 뒤집기 
    cur = 0
    for i in range(3):
      cur+=tetris[y+1][x+i]
    cur += tetris[y][x]
    total = max(cur,total)    
  return total

def search2(start):
  x = start[0]
  y = start[1]
  #case 4
  total =0
  if -1<x+1<m and -1<y+2<n:
    # - 0
    cur =0
    cur += tetris[y][x]
    cur += tetris[y+1][x]    
    cur += tetris[y+1][x+1]
    cur += tetris[y+2][x+1]
    total = max(cur,total)
    # 뒤집기
    cur = 0
    cur += tetris[y][x+1]
    cur += tetris[y+1][x+1]
    cur += tetris[y+1][x]
    cur += tetris[y+2][x]
    total = max(cur,total)
  if -1<x+2<m and -1<y+1<n:  
    cur =0
    cur += tetris[y+1][x]
    cur += tetris[y+1][x+1]    
    cur += tetris[y][x+1]
    cur += tetris[y][x+2]
    total = max(cur,total)
    # 뒤집기
    cur = 0
    cur += tetris[y][x]
    cur += tetris[y][x+1]
    cur += tetris[y+1][x+1]
    cur += tetris[y+1][x+2]
    total = max(cur,total) 
    
  #case 5
  if -1 <x+1<m and -1 < y+2 <n:
    cur = 0
    for i in range(3):
      cur += tetris[y+i][x]
    cur += tetris[y+1][x+1]
    
    total = max(cur,total)
    
    cur = 0
    for i in range(3):
      cur += tetris[y+i][x+1]
    cur += tetris[y+1][x]
  
    total = max(cur, total)
    
    #뒤집기
  if -1 <x+2<m and -1 < y+1<n:
    cur = 0
    for i in range(3):
      cur += tetris[y][x+i]
    cur += tetris[y+1][x+1]
    
    total = max(cur,total)
    
    cur = 0
    for i in range(3):
      cur += tetris[y+1][x+i]
    cur += tetris[y][x+1]
  
    total = max(cur, total)
  return total
result =0          
for i in range(n):
  for j in range(m):
    c1 = search([j,i])
    c2 = search2([j,i])
    result = max(result,c1,c2)
print(result)