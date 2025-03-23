#include <iostream>
#include <string.h>
#include <sstream>
#include <algorithm>
#include <climits>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 15
stringstream sb;
int number[MAX_N][2];
bool check[MAX_N];
int N;
int ans = INT_MIN;


void dfs(int money,int idx){
  if (idx> N)
  {
    return;
  }
  if(idx==N){
    ans = max(ans, money);
    return;
  }
  ans = max(ans, money);
  if(idx+ number[idx][0] <=N ){
      dfs( money + number[idx][1],idx+number[idx][0]);
  }
  dfs(  money, idx + 1);
  
}
void input(){
  cin >> N;
  for (int y = 0; y < N;y++){
    cin >> number[y][0] >> number[y][1];
  }
}


void run(){
  input();
  dfs(0,0);
  sb << ans<<NL;
}
int main()
{

  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  run();
  cout << sb.str();
  return 0;
}