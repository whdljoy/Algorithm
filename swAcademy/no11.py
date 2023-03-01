
from collections import deque
import sys
sys.stdin = open("input.txt", "r")

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
class Node:
  def __init__(self,val=None, left=None, right=None):
    self.val=val
    self.left=left
    self.right=right

def tree_insert(root,target):
  if root is None:
    return
  q= deque()
  q.append(root)
  while q:
    cur_node = q.popleft()
    if cur_node.val == target[0]:
      # print(cur_node.val)
      if cur_node.left:
        if cur_node.left.val > target[1]:
          cur_node.right=cur_node.left
          cur_node.left=Node(val=target[1])
        else:
          cur_node.right=Node(val=target[1])
        return
      else:
        cur_node.left=Node(val=target[1]) 
        return   
    if cur_node.left:
      q.append(cur_node.left)
    if cur_node.right:
      q.append(cur_node.right)
    
        
def dfs(root,target,visit):
  if root is None:
    return
  if target not in visit:
    visit.append(root.val)     
  dfs(root.left,target,visit)
  dfs(root.right,target,visit)


T = int(input())          
for test_case in range(1, T + 1):
  v,e,v1,v2 = map(int,input().split())
  total = list(map(int,input().split()))
  root = Node(val=1)
  for i in range(0,len(total),2):
    target = total[i:i+2]
    tree_insert(root,target)
  visited1=[]
  visited2=[]
  dfs(root,v1,visited1)
  dfs(root,v2,visited2)
  inter = list(set(visited1) & set(visited2))
  # print(visited1)
  # print(visited2)  
  print(inter)
  # print(visited1)
  # print(visited2)