def solution(s, skip, index):
    answer = ''
    for i in range(len(s)):
        tmp = ord(s[i])
        idx=0
        temp = tmp
        while idx<index:
            temp=temp+1
            if temp == 123:
                temp = 97
            if chr(temp) not in skip:
                idx=idx+1;
        answer= answer + chr(temp)                       
    return answer
