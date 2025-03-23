t=int(input())
k = int(input())
coin = [list(map(int,input().split())) for _ in range(k)]
d = [0]*(t+1)
d[0]=1
for i in range(k):
  val,cnt=coin[i]
  for j in range (t,0,-1):
    for a in range (1,cnt+1):
      if(j - val * a >= 0):
        d[j]=d[j-val*a]+d[j] 
print(d[t])