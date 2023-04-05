q = int (input())

class Node(object):
	def __init__(self,val=0,next=None, prev = None):
		self.val =val
		self.next=next
		self.prev = prev
class LL(object):
	def __init__(self):
		self.count = 0
		self.head = self.tail = Node()
	def append(self,gift):
		if self.head.next == None:
			self.head.next = self.tail.prev = Node(val=gift, prev = self.head, next=self.tail)
		else:
			new_node =Node(val=gift, prev=self.tail.prev,next = self.tail )
			self.tail.prev.next = new_node
			self.tail.prev =new_node
		self.count += 1
	def insert(self,gift):
		if self.head.next == None:
			self.head.next = self.tail.prev =Node(val=gift,prev=self.head,next = self.tail)
		else:
			new_node = Node(val=gift, prev=self.head, next=self.head.next)
			self.head.next.prev = new_node
			self.head.next = new_node
		self.count += 1
	def clear(self):
		self.head = self.tail = Node()
		self.count = 0
	def pop(self):
		gift_num = self.tail.prev.val
		self.tail.prev = self.tail.prev.prev
		self.tail.prev.next = self.tail
		self.count -= 1
		return gift_num
	def popfront(self):
		gift_num = self.head.next.val
		self.head.next = self.head.next.next
		self.head.next.prev = self.head
		self.count -= 1
		return gift_num
	def searchGift(self,search_num):
		cur = self.head.next
		a =-1
		b=-1
		while(1):
			if cur == self.tail or cur == None:
				return None
			else:
				if cur.val == search_num:
					if cur.prev.val != 0:
						a = cur.prev.val
					if cur.next.val != 0:
						b = cur.next.val
					return a,b
				else:
					cur = cur.next

def execute(val,argument):
	#200 m_src m_dst
	if val == 200:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		if status[m_src].count != 0:
			status[m_src].tail.prev.next = status[m_dst].head.next
			status[m_dst].head.next = status[m_src].head.next
			status[m_dst].head.next.prev = status[m_src].tail.prev
			status[m_src].head.next.prev = status[m_dst].head
			status[m_dst].count += status[m_src].count

		# for i in range(status[m_src].count):
		# 	status[m_dst].insert(status[m_src].pop())
		status[m_src].clear()
		return status[m_dst].count
	#300 m_src m_dst
	elif val == 300:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		#갯수
		src_num = status[m_src].count
		dst_num = status[m_dst].count
		if src_num > 0 and dst_num > 0:
			target_src = status[m_src].popfront()
			target_dst = status[m_dst].popfront()
			status[m_src].insert(target_dst)
			status[m_dst].insert(target_src)
		elif src_num > 0 and dst_num == 0:
			target_src = status[m_src].popfront()
			status[m_dst].append(target_src)
		elif src_num == 0 and dst_num > 0:
			target_dst = status[m_dst].popfront()
			status[m_src].append(target_dst)
		return status[m_dst].count
	# 400 m_src m_dst
	elif val == 400:
		m_src = argument[0] - 1 #배열 -1 해줘야됨
		m_dst = argument[1] - 1
		if status[m_src].count > 1:
			target_num = status[m_src].count // 2
			for i in range(target_num):
				status[m_dst].insert(status[m_src].popfront())
		return status[m_dst].count
	#500 p_num
	elif val == 500:
		p_num = argument[0]
		a=-1
		b=-1
		for i in range(n):
			key = status[i].searchGift(p_num)
			if key != None:
				a,b = key
				break
		return a+2*b

	# 600 b_num
	elif val ==600:
		target_belt = status[argument[0]-1]
		a=-1
		b=-1
		c = target_belt.count
		if c > 0:
			a=target_belt.head.next.val
			b=target_belt.tail.prev.val
		return a+(2*b)+(3*c)

for i in range(q):
	mission = list(map(int,input().split()))
	if mission[0] == 100:
		n = mission[1] #belt num
		m = mission[2] #gift num
		status = [ LL() for i in range(m)]
		gift_num = 0
		for i in range(m):
			cur = mission[3+i] -1
			gift_num+=1
			status[cur].append(gift = gift_num)
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