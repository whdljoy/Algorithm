n,m,y,x,k = map(int,input().split())

pan = list(list(map(int,input().split()))for _ in range(n))

execute = list(map(int,input().split()))

dice =  [[0]*5 for _ in range(5)]

dx= [0,1,-1,0,0]
dy=[0,0,0,-1,1]
for i in range(k):
  x+= dx[execute[i]]
  y+= dy[execute[i]]
  
  
  if -1< x < m and -1 < y<n:
    if execute[i] ==1:
      for j in range(1,5):
        dice [2][j-1]= dice[2][j]
      dice[2][4]=dice[2][0]
      dice[0][2] = dice [2][0]
      dice[4][2] =dice [2][0] 
    elif execute[i]==2:
      for j in range(3,-1,-1):
        dice [2][j+1]= dice[2][j]
      dice[2][0]=dice[2][4]
      dice[4][2]=dice[2][4]
      dice[0][2]=dice[2][4]
    elif execute[i]==3:
      for j in range(3,-1,-1):
        dice [j+1][2]= dice[j][2]
      dice[0][2]=dice[4][2]    
      dice[2][0]=dice[4][2]  
      dice[2][4]=dice[4][2]  
    else:
      for j in range(1,5):
        dice [j-1][2]= dice[j][2]
      dice[4][2]=dice[0][2]  
      dice[2][0]=dice[0][2]  
      dice[2][4]=dice[0][2]         
    
    if pan[y][x] == 0:
      pan[y][x]=dice[2][2]
    else:
      dice[2][2] =pan[y][x] 
      pan[y][x]=0
    # print(execute[i])
    # print("")
    # print(dice)   
    # print("")
    print(dice[0][2])
  else:
    x -=dx[execute[i]]
    y-= dy[execute[i]]