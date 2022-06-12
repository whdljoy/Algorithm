n = int(input())
answer=[]
total=0
def plus(pre,num,twin):
    global total
    for i in range(1,4):
        if pre+i<twin:
            plus(pre+i,num-i,twin)
        if pre+i==twin:
            total=total+1
for i in range(n):
    answer.append(int(input()))

for j in range(n):
    plus(0,answer[j],answer[j])
    print(total)
    total=0
