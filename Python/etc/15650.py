n,m =map(int,input().split())

sel=[]
for i in range(n):
    sel.append(i+1)

def permutation(arr,n):
    result=[]
    if n==0:
        return [[]]
    for i,ele in enumerate(arr):
        for P in permutation(arr[i+1:],n-1):
            result+=[[ele]+P]
    return result
for i in permutation(sel,m):
    for a in range(len(i)):
        print(i[a],end=" ")
    print("")