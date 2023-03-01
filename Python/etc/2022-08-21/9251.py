lcs1=input()
lcs2=input()
idx=len(lcs1)
idx2=len(lcs2)
dp=[[0] * (idx2+1) for _ in range(idx+1)] 
for i in range(1,idx+1):
  for j in range(1,idx2+1):
    if lcs1[i-1] == lcs2[j-1]:
      dp[i][j] = dp[i-1][j-1]+1
    else:
      dp[i][j] = max(dp[i-1][j],dp[i][j-1])
print(dp[idx][idx2])  