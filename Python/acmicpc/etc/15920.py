
m = int(input())
maneking= input()
arr = list(maneking)
cur ="A"
la = False
memory =False
whe =  -1
answer = 0
for i in range(len(arr)):
  if arr[i] == "P":
    if la == False:
      la = True
      if cur == "B":
        memory = True      
    elif la == True:
      la = False
      if cur == "B":
        memory = True
      
  if arr[i] == "W":
    if cur == "A":
      if la == False:
        whe =0
      elif la == True:
        whe =1
      cur = "B"
      
    elif cur == "B":    
      cur ="C"
      
      if whe == 0:
        answer = 5
      elif whe == 1:
        answer = 1
      if memory == True:
        answer = 6  
      break        
    
print(answer)