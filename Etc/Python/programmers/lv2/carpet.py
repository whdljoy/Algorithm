def solution(brown, yellow):
    answer = []
    target = brown//2 -2
    for y in range(1,yellow+1):
        x = yellow // y
        if x>=y and x*y == yellow and (x+y) ==target:
            answer.append(x+2)
            answer.append(y+2)
            break
    return answer

print(solution(8,1))