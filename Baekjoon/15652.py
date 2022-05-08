n,m=map(int,input().split())
sel=[]
for i in range(n):
    sel.append(i+1)

def gen_permutations(arr, n):
    result = []
    if n == 0:
        return [[]]
    for i,elem in enumerate(arr):
        for P in gen_permutations(arr[i:], n-1):
            result += [[elem]+P]
    return result

for a in gen_permutations(sel,m):
    for j in range(len(a)):
        print(a[j],end=" ")
    print("")
