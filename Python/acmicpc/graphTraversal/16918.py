
r, c, n = map(int,input().split())
de = [ "O"* c for _ in range(r)]
bomb = [ list(input()) for _ in range(r)]
dx = [-1,1,0,0]
dy = [0,0,-1,1]


def searchBomb():
  total =[]
  for y in range(r):
    for x in range(c):
      if bomb[y][x] == "O":
        total.append((x,y))
  return total

def changeBomb(cur):
  default = [ ["O"]* c for _ in range(r)]
  for cur_x,cur_y in cur:
    default[cur_y][cur_x]="."
    for i in range(4):
      tx = cur_x +dx[i]
      ty = cur_y+dy[i]
      if ty >=0 and tx >=0 and ty <r and tx <c:
        default[ty][tx]="."
  return default

      
if n ==1:
  for j in range(r):
    print("".join(bomb[j]))
elif n % 2 ==0:
  for i in range(r):
    print(de[i])
else:
  for i in range(n//2):
    cur =searchBomb()
    bomb = changeBomb(cur)
  for j in range(r):
    print("".join(bomb[j]))