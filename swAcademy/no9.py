
import sys
from collections import deque
sys.stdin = open("input.txt", "r")

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1,11):
  
  T =  int(input())
  visited =[]  
  class Node:
    def __init__ (self,num = None, val = None, left =None, right=None):
      self.num = num
      self.val = val
      self.right=right
      self.left=left
  
  def Tree_insert(cur,target):
    if cur is None:
      return  
    q = deque()
    q.append(cur)
    while q:
      cur_node = q.popleft()
      if cur_node.num == int(target[0]):
        
        for j in range(1,len(target)):
          if j == 1:
            cur_node.val = target[1]
          if j == 2:
            cur_node.left = Node(num=int(target[2])) 
          if j == 3:
            cur_node.right = Node(num=int(target[3]))   
        return
      if cur_node.left:
        q.append(cur_node.left)
      if cur_node.right:
        q.append(cur_node.right)  
        
  def dfs(root):
    if root is None:
      return
    
    dfs(root.left)
    visited.append(root.val)
    dfs(root.right)  
    
  root = Node(num=1)
  for i in range(T):
    cal = input().split()
    target=[]
    target=list(cal)
    Tree_insert(root,target)
      
  dfs(root)
  answer=1
  fix = ["+","-","*","/"]
  for idx, task in enumerate(visited):
    if idx % 2 ==1:
      if task not in fix:
        answer=0
  print("#"+str(test_case),answer)

    