def solution(s):
    answer = -1

    target = list(s)
    def check (val):
      print(val)
      if not val:
        return 1 
      for i in range(len(val)-1):
        if val[i]==val[i+1]:
          del val[i+1]
          del val[i]
          return check(val)
      return 0
    
    answer=check(target)
    return answer
  
print(solution("asddfkjnnjcanaslsacd"))