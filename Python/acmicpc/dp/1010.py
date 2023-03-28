tc = int (input())


def bridge(n,m):
  if n == m:
    return 1
  elif m-n > n:
    return bridge(m-n,m)
  a =1
  b = 1
  for i in range(2,n+1):
    a*=i
  for j in range(m,m-n,-1):
    b*=j
  return b//a
for i in range(tc):
  n,m= map(int,input().split())
  print(bridge(n,m))