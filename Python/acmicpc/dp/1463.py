n = int(input())

ans =0;
dp = 1000002*[0]
dp[1]=0
dp[2]=1
dp[3]=1
dp[4]=2
dp[5]=2

# 재귀시 max depth 초과
def dynamic(n):
    if dp[n] !=0:
        return dp[n]
    else:
        if n%3 ==0:
            val1 = min(dynamic(n//3),dynamic(n-1)) +1
        if n%2 ==0:
            val2 = min(dynamic(n//2),dynamic(n-1)) +1
        dp[n] = min(val1,val2)
    return dp[n]


if n==1:
    print(0)
elif dp[n] !=0:
    print(dp[n])
else:
    print(dynamic(n))


