n,m = map(int,input().split())

wall = list(list(map(int,input().split())) for _ in range(n))


def comb(arr,r):
  result =[]
  if r == 0:
    return [[]]
  
  for i in range(len(arr)):
    cur = arr[i]
    rest = arr[i+1:]
    for C in comb(rest,r-1):
      result.append([cur]+C)
  return result


    