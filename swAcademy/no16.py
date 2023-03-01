

import sys
sys.stdin = open("input.txt", "r")
from collections import deque
import math
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

for test_case in range(1, T + 1):
  ans = []
  n = int(input())
  mp = [list(map(int,input().split())) for _ in range(2)]
  ev = float(input())
  for i in range(n-1):
    tx = mp[0][i]
    ty = mp[1][i]
    distance =[]
    for j in range(i+1,n):
      dx = mp[0][j]
      dy = mp[1][j]
      length=(dx-tx) **2 +(dy-ty)**2
      # length=math.pow(dx-tx,2) +math.pow(dy-ty,2)
      expense =ev *length
      distance.append((j,expense))
    ans.append(distance)
    
  sm=0
  # print(ans)
  for mi in range(len(ans)):
    ans[mi].sort(key=lambda x: x[1])
    # print(ans[mi][0][1])
    # sm+=ans[mi][0][1]
  chi =[]
  for j in range(1,n):
    chi.append(j)
    
  a =1
  # sm+=ans[a][0][1] 
  # chi.remove(a)  
  # a = ans[a][0][0] 
  while 1:
    if not chi:
      break
    sm+=ans[a-1][0][1]
    a = ans[a-1][0][0]
    chi.remove(a)
    
  # check=1
  # while(1):
  #   if check == n:
  #     break
  #   ee =[]
  #   for mi in range(n-1):
  #     for ma in ans[mi]:
  #       if ma[0] == check:
  #         ee.append(ma)
  #   # print(ee)
  #   for j in range(len(ee)):
  #     ee.sort(key=lambda x: x[1])
  #   print(ee)
  #   sm+=ee[0][1]    
  #   check+=1
  # print(sm)
  print("#"+str(test_case),sm)  
