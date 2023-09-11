#include <iostream>
#include <sstream>
#include <algorithm>
#include <string.h>
#include <queue>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 20
stringstream sb;
int N;
int grid[MAX_N][MAX_N];
int  check[5];
int ans = 0;

void move(int dir,int moving[][MAX_N]){
   // 0 위 1 아래 2 왼쪽 3 오른쪽
    deque<int> cur;
   if(dir == 0){
     for (int x = 0; x < N; x++)
     {
       for (int y = 0; y < N ; y++)
       {
         if (moving[y][x] !=0 ){   // 0이 아닌 것들 넣기
           cur.push_back(moving[y][x]);
            moving[y][x] = 0;           
         }    
       }
       int position = 0;
       int first = 0;
       while(!cur.empty()){
        if(first ==0){
          first = cur.front();
          cur.pop_front();
        }else{
          if(first == cur.front()){
            cur.pop_front();
            moving[position++][x] = first *2 ;
            first = 0;
          }
          else
          {
            moving[position++][x] = first;
            first = 0;
          }
        }
       }
      if(first !=0){
        moving[position][x] = first;
      }       
     }
   }else if(dir==1){
     for (int x = 0; x < N;x++){
       for (int y = N-1; y >-1; y--)
       {
         if (moving[y][x] !=0 ){   // 0이 아닌 것들 넣기
           cur.push_back(moving[y][x]);
            moving[y][x] = 0;
         }

       }
       int position = N-1;
       int first = 0;
       while(!cur.empty()){
        if(first ==0){
          first = cur.front();
          cur.pop_front();
        }else{
          if(first == cur.front()){
            cur.pop_front();
            moving[position--][x] = first *2;
            first = 0;
          }
          else
          {
            moving[position--][x] = first;
            first = 0;
          }
        }       
      }
      if(first !=0){
        moving[position][x] = first;
      }            
     }
   }else if(dir==2){
     for (int y = 0; y < N;y++){
       for (int x = 0; x < N ; x++)
       {
         if (moving[y][x] !=0 ){   // 0이 아닌 것들 넣기
           cur.push_back(moving[y][x]);
           moving[y][x] = 0;
         }

       }
       int position = 0;
       int first = 0;
       while(!cur.empty()){
        if(first ==0){
          first = cur.front();
          cur.pop_front();
        }else{
          if(first == cur.front()){
            cur.pop_front();
            moving[y][position++] = first * 2;
            first = 0;
          }
          else
          {
            moving[y][position++] = first;
            first = 0;
          }
        }
       }
      if(first !=0){
        moving[y][position] = first;
      }                
     }
   }else if(dir ==3){
     for (int y = 0; y < N;y++){
       for (int x = N-1; x >-1; x--)
       {
         if (moving[y][x] !=0 ){   // 0이 아닌 것들 넣기
           cur.push_back(moving[y][x]);
           moving[y][x] = 0;
         }
       }
       int position = N-1;
       int first = 0;
       while(!cur.empty()){
        if(first ==0){
          first = cur.front();
          cur.pop_front();
        }else{
          if(first == cur.front()){
            cur.pop_front();
            moving[y][position--] = first *2;
            first = 0;
          }
          else
          {
            moving[y][position--] = first;
            first = 0;
          }
        }
      }  
      if(first !=0){
        moving[y][position] = first;
      }       
     }
   }

}

void each_dir(){
  int moving[MAX_N][MAX_N];
  memset(moving, 0, sizeof(moving));
  for (int y = 0; y < N; y++){
    for (int x = 0; x < N;x++){
      moving[y][x] = grid[y][x];
    }
  }
  for (int i = 0; i < 5; i++)
  {
    move(check[i], moving);
  }
  int cur_max = 0;
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < N;x++){
      if(moving[y][x] > cur_max){
        cur_max = moving[y][x];
      }
    }
  }
  ans = max(ans, cur_max);
}

void dfs(int num){
  if(num ==5){
    each_dir();
    return;
  }
  for (int i = 0; i < 5;i++){
    check[num] = i;
    dfs(num + 1);
  }
}

void input()
{
  cin >> N;
  memset(grid, 0, sizeof(grid));
  memset(check, 0, sizeof(check));
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < N;x++){
      cin >> grid[y][x];
    }
  }
}

void run(){
  input();
  dfs(0);
  sb << ans << NL;
}

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  run();
  cout << sb.str();

  return 0;
}