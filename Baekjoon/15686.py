import queue
n, m = map(int, input().split())
street = [list (map(int,input().split())) for _ in range(n)]

chicken = [[i,j] for i in range(n) for j in range(n) if street[i][j] == 2]
home = [[i,j] for i in range(n) for j in range(n) if street[i][j] == 1]


def gen_combinations(arr, n): 
    result =[] 
    if n == 0: 
        return [[]] 
    for i in range(0, len(arr)): 
        elem = arr[i] 
        rest_arr = arr[i + 1:] 
        for C in gen_combinations(rest_arr, n-1): 
            result.append([elem]+C) 
    return result
close=gen_combinations(chicken,m)

def dist(ho,c_list):
    min_dist=1e9
    for chi in c_list:
        dist=abs(chi[1]-ho[1])+abs(chi[0]-ho[0])
        min_dist = min(min_dist, dist)
    return min_dist
ans=[]
for chi in close:
    total = 0
    for h in home:
        total += dist(h, chi)
    ans.append(total)                   
print(min(ans))
