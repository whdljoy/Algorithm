def solution(numbers):
    answer = [-1] * len(numbers)
    stack = []
    stack.append(0)
    for i in range(1,len(numbers)):
      while stack and numbers[stack[-1]] < numbers[i]:
        answer[stack.pop()] = numbers[i]
      stack.append(i)
    return answer