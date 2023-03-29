n = map( int, input())

expense = list( list(map(int,input().split())) for _ in range(n))
dp=[]
dp[0] =0
cur = 1
for i in range(n):
  item = expense[i]
  if cur == i+1:
    for j in range(cur,cur+item[0]):
      dp[j] = item[1]
    cur = cur+item[0]
  else:
    before = expense[i-1]
    dp[i+1 -before[0]] +expense
    dp[i+1]
    