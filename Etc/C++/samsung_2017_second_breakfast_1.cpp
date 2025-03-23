#include <iostream>
#include <sstream>
#include <algorithm>
#include <string.h>
#include <climits>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 20

stringstream sb;
int N;
int info[MAX_N][MAX_N];
bool check[MAX_N];
int ans = INT_MAX;



void cal_minus(){
  int f_ans=0,t_ans = 0;
  for (int y = 0; y < N; y++){
    for (int x = 0; x < N;x++){
      if(check[y] && check[x]){
        t_ans += info[y][x];
      }
      if(!check[y] && !check[x]){
        f_ans += info[y][x];
      }
    }
  }
  ans = min(ans, abs(t_ans - f_ans));
}

void select(int num,int idx){
  if(num == N/2){
    cal_minus();
    return;
  }
  for (int i = idx; i < N;i++){
    if(!check[i]){
      check[i] = true;
      select(num + 1, i + 1);
      check[i] = false;
    }
  }
}



void input(){
  cin >> N;
  for (int y = 0; y < N;y++){
    for (int x = 0; x < N; x++){
      cin >> info[y][x];
    }
  }

  memset(check, false, sizeof(check));
}

void run(){
  input();
  select(0, 0);
  sb << ans<<NL;
  cout << sb.str();
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();

   return 0;
}