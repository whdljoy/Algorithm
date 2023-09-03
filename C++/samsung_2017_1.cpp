#include <iostream>
#include <string.h>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",w,stdout)
#define NL "\n"

int N;
int M;
#define MAX_N 201
#define MAX_M 201
int **origin;
int ans = 0;


void input()
{
  cin >> N >> M;
  origin = new int*[N];
  for (int y = 0; y < N; y++){
    origin[y] = new int[M];
  }
  for (int y = 0; y < N; y++){
    for (int x = 0; x < M;x++){
      cin >> origin[y][x];
    }
  }

}
bool in_range(int x,int y){
  return 0 <= x && x < M && 0 <= y && y < N;
}
void cal_type1(){  // -  type


    //가로
    for (int y = 0; y < N; y++){
      for (int x = 0; x < M; x++)
      {
        int cur_sum = 0;        
        for (int rn = x; rn < (x + 4); rn++){
          if(in_range(rn,y)){
            cur_sum += origin[y][rn];
          }else{
            cur_sum = 0;
            break;
          }
        }
        ans = max(ans, cur_sum);
      }
    }

    //세로
    for (int x = 0; x < M; x++){
      for (int y = 0; y < N; y++)
      {
        int cur_sum = 0;
        for (int rn = y; rn < y + 4;rn++){
          if(in_range(x,rn)){
            cur_sum += origin[rn][x];
          }else{
            cur_sum = 0;
            break;
          }
        }
        ans = max(ans, cur_sum);
      }
    }

}

void cal_type2(){  // L type  //미ㅇ완
  int dx[32] = {0,0,0,1,  0,1,1,1,    0,1,0,0, 0,1,1,1, 0,1,2,2 , 0,0,1,2, 0,1,2,2, 0,0,1,2  };
  int dy[32] = {0,1,2,2,  0,0,-1,-2,  0,0,1,2, 0,0,1,2, 0,0,0,-1, 0,1,1,1, 0,0,0,1, 0,1,0,0};
  for (int y = 0; y < N;y++){
    for (int x = 0; x < M; x++)
    {
      int cur_sum = 0;
      for (int dir = 0; dir < 4; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 4; dir < 8; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 8; dir < 12; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 12; dir < 16; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 16; dir < 20; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 20; dir < 24; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;                              

      for (int dir = 24; dir < 28; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }

      ans = max(ans, cur_sum);
      cur_sum = 0;                              

      for (int dir = 28; dir < 32; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);       
    }
  }
}
void cal_type3(){ // ㅁ type
  int dx[4] = {0, 1, 0, 1};
  int dy[4] = {0, 0, 1, 1};
  for (int y = 0; y < N;y++){
    for (int x = 0; x < M; x++)
    {
      int cur_sum = 0;
      for (int dir = 0; dir < 4; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
       ans = max(ans, cur_sum);
    }
  }
}
void cal_type4(){ // 
  int dx[16] = {0, 0, 1, 1, 0,0,-1,-1,  0,1,1,2,  0,1,1,2 } ;
  int dy[16] = {0, 1, 1, 2, 0,1,1,2,    0,0,-1,-1, 0,0,1,1};
  for (int y = 0; y < N;y++){
    for (int x = 0; x < M; x++)
    {
      int cur_sum = 0;
      for (int dir = 0; dir < 4; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 4; dir < 8; dir++)
      {
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 8; dir < 12; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 12; dir < 16; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }                        
      }
      ans = max(ans, cur_sum);
    }
  
  }
}
void cal_type5(){ // ㅏ type
    
  int dx[16] = {0, 0, 0, 1,  0, 1,  1,  1,  0,1,2,1,  0,1,2,1 } ;
  int dy[16] = {0, 1, 2, 1,  0, 0, -1,  1,  0,0,0,-1, 0,0,0,1};
  for (int y = 0; y < N;y++){
    for (int x = 0; x < M; x++)
    {
      int cur_sum = 0;
      for (int dir = 0; dir < 4; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 4; dir < 8; dir++)
      {
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 8; dir < 12; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }
      }
      ans = max(ans, cur_sum);
      cur_sum = 0;
      for (int dir = 12; dir < 16; dir++){
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(in_range(cx,cy)){
          cur_sum += origin[cy][cx];
        }else{
          cur_sum = 0;
          break;
        }                        
      }
      ans = max(ans, cur_sum);
    }
  }
}
void cal(){
  cal_type1();
  cal_type2();
  cal_type3();
  cal_type4();
  cal_type5();
}

void run(){
  input();
  cal();
  cout <<   ans<<NL;
}

int main(){


   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();

   return 0;
}