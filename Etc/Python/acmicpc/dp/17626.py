# #########내답
# m = int(input())
# j=1
# memo = [0] * (m+1)
# smemo={}
# while(1):
#   if j**2 > m:
#     break
#   smemo[j**2]=1
#   j+=1

# for idx in smemo:
#   for index in range (len(memo)):
#     if(idx+index) <len(memo) :
#       if memo[idx+index] == 0:
#         memo[idx+index]=smemo[idx]+memo[index]
#       else:
#         memo[idx +index] = min(memo[idx+index],smemo[idx]+memo[index])
        
# print(memo)
# print(memo[m])