n,k = map(int,input().split())
bag = [list(map(int,input().split())) for _ in range(n)]
m_bag = [[0]*(k+1) for _ in range(n+1)]

for i in range(1,n+1):
  for limit in range(1 ,k+1):
    if(limit - bag[i-1][0] >= 0):
      m_bag[i][limit] = max(m_bag[i-1][limit],m_bag[i-1][limit-bag[i-1][0]]+bag[i-1][1])
    else:
      m_bag[i][limit] = m_bag[i-1][limit]    
print(m_bag[n][k])  
