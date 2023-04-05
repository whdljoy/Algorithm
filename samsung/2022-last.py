q = int (input())
from collections import deque

def execute(val,argument):
	#200 m_src m_dst
	if val == 200:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		# for i in range(len(status[m_src])-1,-1,-1):
		# 	status[m_dst].appendleft(status[m_src][i])
		status[m_dst] = status[m_src] +status[m_dst]
		status[m_src]= deque()
		return len(status[m_dst])
	#300 m_src m_dst
	elif val == 300:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		#갯수
		src_num = len(status[m_src])
		dst_num = len(status[m_dst])
		if src_num > 0 and dst_num > 0:
			target_src = status[m_src].popleft()
			target_dst = status[m_dst].popleft()
			status[m_src].appendleft(target_dst)
			status[m_dst].appendleft(target_src)
		elif src_num > 0 and dst_num == 0:
			target_src = status[m_src].popleft()
			status[m_dst].append(target_src)
		elif src_num == 0 and dst_num > 0:
			target_dst = status[m_dst].popleft()
			status[m_src].append(target_dst)
		return len(status[m_dst])
	# 400 m_src m_dst
	elif val == 400:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		if len(status[m_src]) > 1:
			target_num = len(status[m_src]) // 2
			for i in range(target_num-1,-1,-1):
				status[m_dst].appendleft(status[m_src].popleft())
		return len(status[m_dst])
	#500 p_num
	elif val == 500:
		target = 0
		p_num = argument[0]
		for i in range(n):
			if p_num in status[i]:
				target=i
				break
		a=-1
		b=-1
		idx = status[target].index(p_num)
		if idx > 0:
			a = status[target][idx-1]
		if idx < (len(status[target])-1):
			b = status[target][idx+1]
		return a+2*b
	# 600 b_num
	elif val ==600:
		target_belt = status[argument[0]-1]
		a=-1
		b=-1
		c = len(target_belt)
		if c > 0:
			a=target_belt[0]
			b=target_belt[len(target_belt)-1]
		return a+(2*b)+(3*c)


for i in range(q):
	mission = list(map(int,input().split()))
	if mission[0] == 100:
		n = mission[1] #belt num
		m = mission[2] #gift num
		status = [deque() for _ in range(n)]
		gift_num = 0
		for i in range(m):
			cur = mission[3+i] -1
			gift_num+=1
			status[cur].append(gift_num)
	elif mission[0] == 200:
		print(execute(200,mission[1:]))
	elif mission[0] == 300:
		print(execute(300,mission[1:]))
	elif mission[0] == 400:
		print(execute(400,mission[1:]))
	elif mission[0] == 500:
		print(execute(500,mission[1:]))
	elif mission[0] == 600:
		print(execute(600,mission[1:]))