#include <iostream>
#include <sstream>
#include <algorithm>
#include <climits>
#include <string>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"

stringstream sb;
string N;
int ans_min = INT_MAX;
int ans_max = INT_MIN;

int cal_odd(string val){
  int num = stoi(val);
  int check = 0;
  while (true)
  {
    if((num %10) %2 ==1){
      check += 1;
    }
    num = num / 10;
    if(num ==0){
      break;
    }
  }
  return check;
}

void cal(string val, int total){
  if(val.length() >= 3){ //3자리 이상일때
    for (int i = 0; i < val.length()-2;i++){
      for (int j = i + 1; j < val.length()-1;j++){
        string first =val.substr(0, i + 1);
        string second = val.substr(i + 1, j -i);
        string third = val.substr(j+1,val.length());
        string sum = to_string(stoi(first) + stoi(second) + stoi(third));
        cal(sum, total + cal_odd(first) + cal_odd(second) + cal_odd(third));
      }
    }
  }else if (val.length() == 2){
    int num = 0;
    for (int i = 0; i < 2; i++)
    {
      num += val[i] - '0';
      if ((val[i] - '0') % 2 == 1)
      {
        total += 1;
      }
    }
    cal(to_string(num), total);
  }
  else
  {
    if((val[0]-'0') %2 ==1){
      total += 1;
    }
    ans_min = min(ans_min,total);
    ans_max = max(ans_max, total);
  }
}

void input(){
  cin >> N;
}
void run(){
  input();
  cal(N,0);
  sb << ans_min << " "<<ans_max << NL;
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);

   run();
   cout << sb.str();
   return 0;
}