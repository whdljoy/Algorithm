m,n = map(int,input().split())
if ( 0<n<=pow (10,1000) and 0<m <= pow(10,1000) and n<=m):
    print(m//n)
    print(m%n)