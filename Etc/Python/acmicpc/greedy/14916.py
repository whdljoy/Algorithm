n = int(input())
i=0
target=0
while(1):
  target = n // 5 -i
  remain = n-(target*5)
  if(target< 0):
    target=-1
    break    
  if( remain % 2 == 0):
    target += remain //2
    break
  else:
    i+=1
    
print(target)