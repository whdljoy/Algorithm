n, m = map(int,input().split())

candy = list(list(input()) for _ in range(n))

blue = [0,0]
red = [0,0]
for i in range(n):
  for j in range(m):
    if candy[i][j] == "B":
      blue=[i,j]
    elif candy[i][j] == "R":
      red = [i,j]
# check =0
total =0
result =10
dx =[1,-1,0,0]
dy = [0,0,1,-1]

def back(check, total):
  global result
  bx = blue[1]
  by = blue[0]
  rx = red[1]
  ry = red[0]
  while(1):  
    if candy[by +dy[check]][bx + dx[check]] =="#":
      break
    elif candy[by+dy[check]][bx + dx[check]] =="O":
      bx += dx[check]
      by += dy[check]      
      total = -1 
      break
    bx += dx[check]
    by += dy[check]          
  while(1): 
    if candy[ry + dy[check]][rx +dx[check]] =="#":
      break
    elif candy[ry + dy[check]][rx +dx[check]] =="O":
      result = min(result,total+1)
      return 0 
    rx += dx[check]
    ry += dy[check]          
  blue[0] = by
  blue[1] = bx
  red[1] = rx
  red[0] = ry   
  total +=1
  if (total >= 10 or total == 0):
    return 0
  for i in range(4):
    back(i,total)
      
for j in range(4):
  back(j, total)
print(result)