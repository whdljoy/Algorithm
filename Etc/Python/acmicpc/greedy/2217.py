m = int(input())
rope = list(int(input()) for _ in range(m))

answer = []
check = 0
rope.sort(reverse=True)
for item in rope:
  answer.append(item)
  check=max(len(answer) *answer[-1],check)
  
print(check)