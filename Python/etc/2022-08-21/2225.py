n,k = map(int,input().split())
memo =[[0] * (n+1) for _ in range((k+1))]


for i in range (1,k+1):
  for j in range(0,n+1):
    if i == 1 :
      memo[i][j] = 1
    else:
      for a in range(0,j+1):
        memo[i][j]=memo[i][j]+memo[i-1][a]
        memo[i][j]=memo[i][j] % 1e9
print(int( memo[k][n] % 1e9 ))
