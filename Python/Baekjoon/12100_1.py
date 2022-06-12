from copy import deepcopy
n = int(input())
n_board = [list(map(int,input().split())) for _ in range (n)]
t_case=[]
def deep(arr):
    return [i[:] for i in arr]
def right(board):
    for a in range(n):
        for b in range(n - 1, -1, -1):
            if board[a][b] == 0:
                if (b > 0):
                    for c in range(b - 1, -1, -1):
                        if board[a][c] != 0:
                            board[a][b] = board[a][c]
                            board[a][c] = 0
                            break
def left(board):
    for a in range(n):
        for b in range(n - 1):
            if board[a][b] == 0:
                if (b < n - 1):
                    for c in range(b + 1, n):
                        if board[a][c] != 0:
                            board[a][b] = board[a][c]
                            board[a][c] = 0
                            break
def up(board):
    for a in range(n):
        for b in range(n - 1):
            if board[b][a] == 0:
                if (b < n - 1):
                    for c in range(b + 1, n):
                        if board[c][a] != 0:
                            board[b][a] = board[c][a]
                            board[c][a] = 0
                            break
def down(board):
    for a in range(n):
        for b in range(n - 1, -1, -1):
            if board[b][a] == 0:
                if (b > 0):
                    for c in range(b - 1, -1, -1):
                        if board[c][a] != 0:
                            board[b][a] = board[c][a]
                            board[c][a] = 0
                            break
def move(board,value,dir):
    if dir ==0:
        right(board)
        for a in range(n):
            for b in range(n-1,-1,-1):
                if board[a][b] != 0:
                    if board[a][b] == board[a][b-1]:
                        board[a][b] = 2 *board[a][b-1]
                        board[a][b-1]=0
        right(board)
    if dir ==1:
        left(board)
        for a in range(n):
            for b in range(n-1):
                if board[a][b] != 0:
                    if board[a][b] == board[a][b+1]:
                        board[a][b] = 2 *board[a][b+1]
                        board[a][b+1] = 0
        left(board)
    if dir ==2:
        up(board)
        for a in range(n):
            for b in range(n-1):
                if board[b][a] != 0:
                    if board[b][a] == board[b+1][a]:
                        board[b][a] = 2 *board[b+1][a]
                        board[b+1][a] = 0
        up(board)
    if dir ==3:
        down(board)
        for a in range(n):
            for b in range(n-1,-1,-1):
                if board[b][a] != 0:
                    if board[b][a] == board[b-1][a]:
                        board[b][a] = 2 *board[b-1][a]
                        board[b-1][a]=0
        down(board)
    value = value + 1
    if (value == 5):
        t_case.append(max(map(max,board)))
    else:
        move(deep(board),value,0)
        move(deep(board),value,1)
        move(deep(board),value,2)
        move(deep(board),value,3)
for i in range (4):
    move(deep(n_board),0,i)

print(max(t_case))
