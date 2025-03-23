#include <iostream>
#include <sstream>
#include <climits>
#include <algorithm>
#include <string>
using namespace std;

stringstream sb;

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
int N;
int M;
int **origin; // 0 빈칸 1 장애물 2 블루 3 레드 4 출구
int *op;
int ans = INT_MAX;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};
bool flag;
bool b_flag;
bool r_flag ;
bool in_range(int x, int y){
  return 0 <= x && x < M && 0 <= y && y < N;
}
int* move_total(int dir,int point[]){
  int *new_origin = new int [4];
  for (int i = 0; i < 4;i++){
    new_origin[i] = point[i];
  }
  int bx = new_origin[0];
  int by = new_origin[1];
  int rx = new_origin[2];
  int ry = new_origin[3];

  flag = false;
  r_flag = false;
  b_flag = false;
  int r_num = 0;
  int b_num = 0;
  while (1)
  {
    int tx = bx + dx[dir];
    int ty = by + dy[dir];
    if (origin[ty][tx] == 0 ){
      bx += dx[dir];
      by += dy[dir];
      b_num += 1;
    }else if (origin[ty][tx] ==4){
      b_flag = true;
      break;
    }else if(origin[ty][tx] ==1){
      break;
    }
  }
  while (1)
  {
    int tx = rx + dx[dir];
    int ty = ry + dy[dir];
    if (origin[ty][tx] == 0  ){
      rx += dx[dir];
      ry += dy[dir];
      r_num += 1;
    }else if (origin[ty][tx] == 4){
      r_flag = true;
      break;
    }else if(origin[ty][tx] ==1){
      break;
    }
  }
  if(rx ==bx && ry ==by){
    if(r_num > b_num){
      rx -= dx[dir];
      ry -= dy[dir];
    }
    else
    {
      bx -= dx[dir];
      by -= dy[dir];
    }
  }
  
  if(!b_flag){
    flag = true;
  }
  new_origin[0] = bx;
  new_origin[1] = by;
  new_origin[2] = rx;
  new_origin[3] = ry;
  return new_origin;
}
void dfs(int num, int *point,int before )
{
  if(num > 9){
    return;
  }
  for (int i = 0; i < 4;i++){
    if(i != before){
      int *moved = move_total(i,point);
      if (r_flag && !b_flag)
      {
        ans = min(ans, num+1);
        return;
      }
      if(flag){
        dfs(num + 1,moved,i);
      }
    }
  }
}
void input()
{
  cin >> N >> M;
  origin = new int *[N];
  op = new int[4];
  for (int y = 0; y < N; y++)
  {
    origin[y] = new int[M];
  }
  for (int y = 0; y < N; y++)
  {
    string ip;
    cin >> ip;
    for (int x = 0; x < M; x++)
    {
      if (ip[x] == '.')   origin[y][x] = 0;
      else if (ip[x] == '#')  origin[y][x] = 1;
      else if (ip[x] == 'B')  {
        origin[y][x] = 0;
        op[0] = x;
        op[1] = y;
      }
      else if (ip[x] == 'R') {
        origin[y][x] = 0;
        op[2] = x;
        op[3] = y;
      }
      else if (ip[x] == 'O')  origin[y][x] = 4;
    }
  }
}

void run()
{
  input();
  dfs(0, op,-1);
  if(ans == INT_MAX){
    ans = -1;
  }
  sb << ans << NL;
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();
   cout << sb.str();

   return 0;
}