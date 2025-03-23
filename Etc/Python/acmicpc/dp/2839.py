m = int(input())

memo = [0]* (m+1)
i=1
while(1):
  if i * 5 >m:
    break
  memo[i*5]=i
  i+=1
  
for idx, item in enumerate(memo):
  if item != 0:
    a=1
    while(1):
      if (idx+(a * 3)) >m:
        break
      if memo[idx+a*3] ==0 or item+a < memo[idx+a*3]:
        memo[idx+a*3] =item+ a
      a+=1
j=1
while(1):
  if j * 3 >m:
    break
  if memo[j*3] ==0 or j < memo[j*3]:
    memo[j*3] = j
  j+=1

if memo[m] == 0:
  print(-1)
else:
  print(memo[m])  
