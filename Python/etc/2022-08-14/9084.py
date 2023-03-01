def cal_coin(coin,m):
  anw = [0] *10001
  anw[0]=1
  for cs in range(0,len(coin)):
    for i in range(coin[cs],10001):
        anw[i]+=anw[i-coin[cs]]
  return anw[m]
t = int(input())
for tc in range(0,t):
  n = int(input())
  coin = list(map(int,input().split()))
  m = int(input())
  print(cal_coin(coin,m))