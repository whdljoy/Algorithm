n = int(input())
people= list(map(int,input().split()))
cap, member = map(int,input().split())

total = []
for i in range(len(people)):
  people[i] = people[i] - cap
  if people[i] >0:
    if people[i] % member ==0:
      total.append(people[i]//member+1)
    else:
      total.append(people[i]//member+2)
  else:
    total.append(1)


print(sum(total))
