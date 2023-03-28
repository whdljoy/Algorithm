
m = int(input())
ppap = input()
arr = list(ppap)

val =0
i = 0
while(1):
  if i == len(arr)-1:
    break
  if arr[i] == "p" and arr[i+1] == "P" and arr[i+2]=="A" and arr[i+3]=="p":
    val+=1
    if i == len(arr)-4:
      break    
    i=i+3
  i+=1
    
print(val)
    