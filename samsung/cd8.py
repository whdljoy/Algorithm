n, m = map(int,input().split())

y, x ,d = map(int,input().split())

#도로 0 인도 1
road = list(list(map(int,input().split())) for _ in range(n))
#d   0 :북 1:동 2:남 3:서

dx = [-1,0,1,0]
dy= [0,-1,0,1]
visited= []
visited.append((x,y))
cur = [y,x,d]
def move(road, cur):
  #cur_x = cur [1]
  #cur_y= cur [0]
  count = 0
  direction = cur[2] 
  while(1):  
    move_x = cur[1]+dx[direction] 
    move_y = cur[0]+dy[direction]  
    if count ==4:
      back_x = cur[1] 
      back_y = cur[0]   
      # 0 - 1  1 
      if direction == 0:
        back_y += dy[3]
      elif direction ==1:
        back_x += dx[0]       
      elif direction == 2:
        back_y += dy[1]            
      else:
        back_x += dx[2]   
      if road[back_y][back_x] == 0:
        cur = [back_y,back_x,direction]
        count =0
      else:
        break
    elif road[move_y][move_x] == 0 and (move_x,move_y) not in visited:
      visited.append((move_x,move_y))
      if direction == 0:
        direction=3
        cur = [move_y,move_x,direction]
        break
      elif direction ==1:
        direction = 0
        cur = [move_y,move_x,direction]
        break        
      elif direction == 2:
        direction= 1
        cur = [move_y,move_x,direction]
        break        
      else:
        direction=2
        cur = [move_y,move_x,direction]   
        break    
    else:
      count += 1
      if direction == 0:
        direction=3
      elif direction == 1:
        direction= 0
      elif direction == 2:
        direction=1        
      else:
        direction=2           
  if count ==4:
    return 0
  else:
    move(road,cur)          
move(road,cur)
print(len(visited))