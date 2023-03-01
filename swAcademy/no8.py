
# import sys
# sys.stdin = open("input.txt", "r")
from collections import deque


for test_case in range(1,11):
    class Node:
      def __init__(self,value = 0, st="",  left=None, right =None ):
        self.st=st
        self.value=value
        self.left=left
        self.right=right
        
    def bfs_insert(root,val):
      if root is None:
        return 0
      q=deque()
      q.append(root)
      while q:
        cur_node = q.popleft()
        if cur_node.value == int(val[0]):
          for j in range(len(val)):
            if j == 1:
              cur_node.st = val[1]
            if j == 2:
              cur_node.left = Node(value=int(val[2])) 
            if j == 3:
              cur_node.right = Node(value=int(val[3]))               
          return
        if cur_node.left:
          q.append(cur_node.left)
        if cur_node.right:
          q.append(cur_node.right) 
  
    
    answer=[]
    def dfs(root):
      if root is None:
        return
      dfs(root.left)
      answer.append(root.st)
      dfs(root.right)
    T = int(input())              
    root =Node(value=1)

    for i in range(T):
      arr = list(input().split())
      bfs_insert(root,arr)
    
    dfs(root)
    print("#"+str(test_case),"".join(answer))

