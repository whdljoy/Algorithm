import math
mi, ma = map(int,input().split())
memo = [False] * (ma-mi+1)
for target in range(2,int(math.sqrt(ma))+1):
  val = target **2
  i =int(mi/val)
  while 1:
    t2 =val*i
    if t2 >= mi and t2<= ma:
      memo[t2-mi]+=1
    if t2 > ma:
      break;
    i+=1
  if(val >= mi):
    memo[val-mi]+=1
print(memo.count(False))