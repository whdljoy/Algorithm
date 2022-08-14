e,s,m = map(int,input().split())
e_1,s_1,m_1 =1,1,1
answer =1
while True:
    if e_1== e and s_1 ==s and m_1 == m:
        break
    e_1+=1
    s_1+=1
    m_1+=1
    if e_1>15:
        e_1=1
    if s_1>28:
        s_1=1
    if m_1>19:
        m_1=1
    answer+=1
print (answer)
