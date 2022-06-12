n =int(input())
memo=[list(0 for i in range(0,41)) for _ in range(2)]
def fibo(n):
    global zero
    global one
    if n==0:
        zero+=1
        return 0
    elif n==1:
        one+=1
        return 1
    else:
        if memo[0][n]==0 and memo[1][n]==0:
            return fibo(n-1)+fibo(n-2)
        else:
            zero+=memo[0][n]
            one+=memo[1][n]
            return 0     
for i in range(n):
    zero=0
    one=0
    m = int(input())
    if(m>40):
        continue
    fibo(m)
    memo[0][m]=zero
    memo[1][m]=one
    print(zero,one)
