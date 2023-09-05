#include <iostream>
#include <sstream>
#include <climits>
#include <algorithm>
using namespace std;
stringstream sb;

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
int N;
int check[3];  // 0 - ,1 + ,2 *
int *num;

int ans_max = INT_MIN;
int ans_min = INT_MAX;

void dfs(int val,int cal_num){
  if(val == N-1){
    //연산값
    ans_max = max(ans_max, cal_num);
    ans_min = min(ans_min, cal_num);
    return;
  }
  if(check[0] != 0){
    check[0] -= 1;
    dfs(val + 1, cal_num + num[val + 1]);
    check[0] += 1;
  }
  if (check[1] != 0)
  {
    check[1] -= 1;
    dfs(val + 1, cal_num - num[val + 1]);
    check[1] += 1;
  }
  if(check[2] != 0){
    check[2] -= 1;
    dfs(val + 1, cal_num * num[val + 1]);
    check[2] += 1;
  }    
}

void input()
{
  cin >> N;
  num = new int[N];
  for (int i = 0; i < N; i++){
    cin >> num[i];   //숫자
  }
  for (int i = 0; i < 3;i++){
    cin >> check[i];  //각 연산자별 갯수
  }
}

void run()
{
  input();
  dfs(0, num[0]);
  sb << ans_min << " " << ans_max << NL;
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();
   cout << sb.str();

   return 0;
}