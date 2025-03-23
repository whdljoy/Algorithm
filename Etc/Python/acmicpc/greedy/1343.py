position = list(input())

def solution(answer):
  check = 0
  re = []
  for item in answer:  
    if item == "X":
      check+=1
    else:
      if check % 2==0:
        if check %4 == 0:
          for i in range(check//4):
            re.append("AAAA")
        else:
          for _ in range(check//4):
            re.append("AAAA")
          for _ in range((check%4)//2):
            re.append("BB")    
      else:
        re=[]
        re.append("-1")
        return re 
      check = 0   
      re.append(".")    
      
  if check !=0:
    if check % 2==0:
      if check %4 == 0:
        for i in range(check//4):
          re.append("AAAA")
      else:
        for _ in range(check//4):
          re.append("AAAA")
        for _ in range((check%4)//2):
          re.append("BB")  
    else:
        re=[]
        re.append("-1")
        return re          
  return re

print("".join(solution(position)))