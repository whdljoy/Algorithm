n,m=map(int,input().split())
sel=list(map(int,input().split()))

def permutation(arr,n):
    result=[]
    if n==0:
        return [[]]
    for i,ele in enumerate(arr):
        for P in permutation(arr[i:],n-1):
            result+=[[ele]+P]
    return result
rvi=set(sel)
rv2=list(rvi)
rv2.sort()
for i in permutation(rv2,m):
    for a in range (len(i)):
        print(i[a],end=" ")
    print("")