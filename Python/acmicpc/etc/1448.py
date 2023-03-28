m = int(input())
straw = []
for _ in range(m):
  val = int(input())
  straw.append(val)

straw.sort(reverse=True)
result = -1

for i in range(len(straw)-2):
  c = straw[i]
  b = straw[i+1]
  a = straw[i+2]
  if c >= (a+b): 
    continue
  else:
    result = a+b+c
    break
  
print(result) 