n = map( int, input())

expense = list( list(map(int,input().split())) for _ in range(n))
dp=[]
dp[0] =0
cur = 1
for i in range(n):
  item = expense[i]

      
          