m = []
for i in range(9):
    i = int(input())
    m.append(i)

def slt(arr,n):
    result=[]
    if n== 0:
        return [[]]
    for i in range(0,len(arr)):
        elem =arr[i]
        rest_arr =arr[i+1:]
        for a in slt(rest_arr,n-1):
            result.append([elem]+a)
    return result
re = slt(m,7)
for i in range(len(re)):
    if sum(re[i])==100:
        re[i].sort()
        for a in range(len(re[i])):
            print(re[i][a])
        break