def solution(n,a,b):
    answer = 1
    ag =0
    bg=0
    while(1):
      if a %2 == 1:
        ag =(a+1)/2
      else:
        ag = a/2
        
      if b %2 == 1:
        bg =(b+1)/2
      else:
        bg = b/2   
      
      if ag ==bg:
        break
      else:
        answer+=1
        a = ag
        b = bg
    return answer

    return answer