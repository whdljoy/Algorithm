def solution(arr):
    answer = 0
    tmp = []
    arr.sort()
    mv = max(arr)
    i = 1
    while(1):
        tmp = []
        target = mv *i
        for j in arr:
            if (target % j) !=0:
                tmp.append(-1)
        if -1 in tmp:
            i+=1
        else:
            answer=target
            break
      
    return answer

print(solution([1,2,3]))