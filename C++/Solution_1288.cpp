#include <iostream>
#include <stdio.h>
using namespace std;

#define fastio ios_base::sync_with_stdio(false); cin:tie(null) cout:tie(null)

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",w,stdout)


#define vec vector

#define NL "\n"

int idx = 1;
int num[10];
int N;

bool check_end(){
  for (int i = 0; i < 10;i++){
    if(num[i]==0){
      return false;
    }
  }
  return true;
}

void cal(){
  idx = 1;
  int cur_num = N * idx;
  while (1)
  {
    while(1){
      num[cur_num % 10] = 1;
      cur_num = cur_num / 10;
      if(cur_num ==0){
        break;
      }
    }
    if(check_end()){
      return;
    }
    idx++;
    cur_num = N * idx;
  }
}


int main()
{

  int tc;
  cin >> tc;
  for (int i = 1; i <= tc; i++)
  {
    cin >> N;
    for (int a = 0; a < 10;a++){
      num[a] = 0;
    }
    cal();
    cout << "#" << i << " " << N*idx << NL;
  }
}

