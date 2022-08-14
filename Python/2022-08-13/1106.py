target, num = map(int,input().split())
test = [list(map(int,input().split())) for _ in range(num)]
cal = [0] * 100002
for i in test:
  for limit in range(1 ,100002):
    if(limit - i[0] >= 0):
        cal[limit] = max(cal[limit],cal[limit-i[0]]+i[1])
for j in range(1 ,100002):
  if cal[j] >= target:
    print(j);
    break;
