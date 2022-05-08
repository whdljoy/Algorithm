n,m = map(int, input().split())
sel=[]
for i in range(n):
    sel.append(i+1)

def gen_permutations(arr, n):
    result = []
    if n == 0:
        return [[]]
    for i, elem in enumerate(arr):
        for P in gen_permutations(arr[:i] + arr[i+1:], n-1):
            result += [[elem]+P]
    return result

for i in gen_permutations(sel,m):
    for a in range(len(i)):
        print(i[a],end=" ")
    print("")