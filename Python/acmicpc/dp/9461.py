
tc = int(input())


dp = [0] *102;
dp[0] = 1
dp[1] = 1
dp[2] = 1
dp[3] = 1
dp[4] = 2
dp[5] = 2
dp[6] = 3
dp[7] = 4
dp[8] = 5
dp[9] = 7
dp[10] = 9
def dinamic(N):
    if dp[N] != 0:
        return dp[N]
    else:
        dp[N]= dinamic(N-5)+dinamic(N-1)
    return dp[N]
for i in range(tc):
    N = int(input())

    if dp[N] != 0:
        print(dp[N])
    else:
        print(dinamic(N))