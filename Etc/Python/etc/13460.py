import queue
n,m= map(int,input().split())
escape = [list(input()) for _ in range(n)]
visited = [[[[False] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
q = queue.Queue()
def start():
    _rx, _ry, _bx, _by = [0] * 4
    for i in range(n):
        for j in range(m):
            if escape[i][j] == 'R':
                _rx, _ry = j, i
            elif escape[i][j] == 'B':
                _bx, _by = j, i
    q.put([_ry, _rx])
    q.put([_by,_bx])
    q.put(0)
    visited[_ry][_rx][_by][_bx] = True

def move(x,y,nx,ny):
    cnt=0
    while escape[y+ny][x+nx] != "#" and escape[y][x] != "O":
        cnt=cnt+1
        x=x+nx
        y=y+ny
    return x,y,cnt


def change():
    while not q.empty():
        y, x = q.get()
        y1, x1 = q.get()
        total = q.get()
        if total>=10:
            break
        for i in range(4):
            rx,ry,rcnt = move(x,y,dx[i],dy[i])
            bx,by,bcnt = move(x1,y1,dx[i],dy[i])
            if escape[by][bx] == "O":
                continue
            if escape[ry][rx] =="O":
                print(total+1)
                return
            if rx==bx and ry==by:
                if(rcnt>bcnt):
                    rx=rx-dx[i]
                    ry=ry-dy[i]
                else:
                    bx=bx-dx[i]
                    by=by-dy[i]
            if not visited[ry][rx][by][bx]:
                visited[ry][rx][by][bx] = True
                q.put([ry,rx])
                q.put([by,bx])
                q.put(total+1)
    print(-1)
start()
change()

