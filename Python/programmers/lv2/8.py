def solution(n):
    answer = 0
    check = list(format(n,"b")).count("1")
    while(1):
      n+=1
      tmp =list(format(n,"b")).count("1")
      if(tmp == check):
        return n
print(solution(4))