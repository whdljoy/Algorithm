n = int(input())
space = list(map(int,input().split()))
b,c = map(int,input().split())

total = 0
for item in space:
  total+=1
  another = item - b 
  if another > 0:
    total = total+ another //c
    if another %c >0 :
      total+=1
print(total)
    