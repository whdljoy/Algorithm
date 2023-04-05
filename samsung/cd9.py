n, L = map(int,input().split())

grid = list(list(map(int,input().split())) for _ in range(n))
result =0
total =[]
def check(cur):
	visited=[]
	global result
	for j in range(n-1):
		if cur[j] - cur[j+1] == 1:
			fulfill = True
			#양의 방향
			for a in range(1,L+1):
				if not ( -1<a+j< n and cur[a+j] == cur[j+1] and a+j not in visited):
					fulfill = False
					break
			if fulfill == True:
				for a in range(1, L + 1):
					visited.append(a+j)
				result +=1
		elif cur[j] - cur[j+1] == -1:
			fulfill = True
			#양의 방향
			for a in range(1,L+1):
				if not ( -1<j-a< n and cur[j-a] == cur[j] and j-a not in visited):
					fulfill = False
					break
			if fulfill == True:
				for a in range(1, L + 1):
					visited.append(j-a)
				result +=1
	print(visited)
#열 체크
for i in range(n):
	#확인 열
	target = []
	for j in range(n):
		target.append(grid[j][i])
	check(target)

# 행체크
for i in range(n):
	target = []
	for j in range(n):
		target.append(grid[i][j])
	check(target)

print(result)


