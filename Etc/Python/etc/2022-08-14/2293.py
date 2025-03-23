n , k =map(int,input().split())
coin = [int(input()) for _ in range(n)]
anw = [0] *10001
anw[0]=1
for cs in range(0,len(coin)):
  for i in range(coin[cs],10001):
      anw[i]+=anw[i-coin[cs]]

print(anw[k])