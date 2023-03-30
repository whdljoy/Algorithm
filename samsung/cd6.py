n = int(input())

expense = list(list(map(int, input().split())) for _ in range(n))

memory = [[0] * (n+1)]
# n = day
def arr_copy(arr):
	b = []
	for i in range(len(arr)):
		b.append(arr[i])
	return b

for i in range(n):
	# item  num은 시작은 day
	day = i+1
	item = expense[i]
	for j in range(len(memory)-1, -1,-1):
		if memory[j][day] ==0:
			cur = arr_copy(memory[j])
			if day +item[0] <= n+1:
				for a in range(day,day+item[0]):
					cur[a] = max(cur[:day])+item[1]
				memory.append(cur)
# print(memory)
result = 0
for re in memory:
	result =max(max(re),result)
print (result)

