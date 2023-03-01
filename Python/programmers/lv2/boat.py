
def solution(people, limit):
    answer = 0
    people.sort(reverse=True)
    while people:
        target = people[0]
        mi = limit - target
        if len(people) ==1:
          answer +=1
          del people[0]
        else:
          if people[-1] > mi:
              answer +=1
              people.remove(target)
          else:
              answer +=1
              people.remove(target)
              del people[-1]
    return answer
  
print(solution([70, 50, 80],100))