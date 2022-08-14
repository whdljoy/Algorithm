shark=[ list(map(int,input().split())) for _ in range(4) ]
print(shark)

def direction(arr):
    for a in range(0,16):
        for b in range(0,8,2):
            for c in range(0,4):
                if arr[c][b]==a:


def change(arr,x,y):
    if arr[y][x+1] ==1:
        if arr[y-1][x] ==-1 or y-1<0:
            arr[y][x+1]=2
            change(arr,x,y)
        else:
            temp1=arr[y-1][x]
            temp2=arr[y-1][x+1]
            arr[y - 1][x]=arr[y][x]
            arr[y - 1][x +1]=arr[y][x+1]
            arr[y][x]=temp1
            arr[y][x + 1]=temp2
    if arr[y][x + 1] == 2:
        if arr[y-1][x-2] ==-1 or y-1 <0 or x-1<0:
            arr[y][x+1]=3
            change(arr, x, y)
        else:
            temp1=arr[y-1][x-2]
            temp2=arr[y-1][x-1]
            arr[y - 1][x - 2]=arr[y][x]
            arr[y - 1][x - 1]=arr[y][x+1]
            arr[y][x]=temp1
            arr[y][x + 1]=temp2
    if arr[y][x + 1] == 3:
        if arr[y][x-2] ==-1 or x-1<0:
            arr[y][x + 1] = 4
            change(arr, x, y)
        else:
            temp1=arr[y][x-2]
            temp2=arr[y][x-1]
            arr[y][x-2]=arr[y][x]
            arr[y][x-1]=arr[y][x+1]
            arr[y][x]=temp1
            arr[y][x + 1]=temp2
    if arr[y][x + 1] == 4:
        if arr[y+1][x-2]== -1 or y+1>3 or x-1<0:
            arr[y][x + 1] = 5
            change(arr, x, y)
        else:
            temp1=arr[y+1][x-2]
            temp2=arr[y+1][x-1]
            arr[y+1][x-2]=arr[y][x]
            arr[y+1][x -1]=arr[y][x+1]
            arr[y][x]=temp1
            arr[y][x + 1]=temp2
    if arr[y][x + 1] == 5:
        if arr[y+1][x]== -1 or y+1>3:
            arr[y][x + 1] = 6
            change(arr, x, y)
    if arr[y][x + 1] == 6:
        if arr[y+1][x+2]== -1 or y+1>3 or x+2>7:
            arr[y][x + 1] = 7
            change(arr, x, y)
    if arr[y][x + 1] == 7:
        if arr[y][x+2]== -1 or x+2>7:
            arr[y][x + 1] = 8
            change(arr, x, y)
    if arr[y][x + 1] == 8:
        if arr[y-1][x+2] == -1 or y-1<0 or x+2>7:
            arr[y][x+1]= 1
            change(arr, x, y)