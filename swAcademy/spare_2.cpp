/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// float b, c;
// double d, e, f;
// char g;
// char var[256];
// long long AB;
// cin >> a;                            // int 변수 1개 입력받는 예제
// cin >> b >> c;                       // float 변수 2개 입력받는 예제 
// cin >> d >> e >> f;                  // double 변수 3개 입력받는 예제
// cin >> g;                            // char 변수 1개 입력받는 예제
// cin >> var;                          // 문자열 1개 입력받는 예제
// cin >> AB;                           // long long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// float b = 1.0, c = 2.0;               
// double d = 3.0, e = 0.0; f = 1.0;
// char g = 'b';
// char var[256] = "ABCDEFG";
// long long AB = 12345678901234567L;
// cout << a;                           // int 변수 1개 출력하는 예제
// cout << b << " " << c;               // float 변수 2개 출력하는 예제
// cout << d << " " << e << " " << f;   // double 변수 3개 출력하는 예제
// cout << g;                           // char 변수 1개 출력하는 예제
// cout << var;                         // 문자열 1개 출력하는 예제
// cout << AB;                          // long long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////



#include<iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
  int T;
  cin>>T;
  for(test_case = 1; test_case <= T; ++test_case){
    queue<int> num;
    vector<int> ans;
    string ip;
    int x, y=0;
    int first = 0;
    int abt = 1;
    cin >> ip >> x >> y;
    for (int i = 0; i < ip.size(); i++){
      num.push(ip[i] - '0');
    }
    if(x < 0 || x > 8){
      cout << "#"<< test_case<<" -1" << endl;
      continue;      
    }
    if(y< 1 || y > 9){
      cout << "#"<< test_case<<" -1" << endl;
      continue;        
    }
    if(x >= y){
      cout << "#"<< test_case<<" -1" << endl;
      continue;      
    }
    if (num.size() < 2)
    {
      int first = num.front();
      if (first >= y){
        cout << "#"<< test_case<<" "<<y<< endl;
        continue;
      }
      else if( first >=x ){
        if (x == 0){
          cout << "#"<< test_case<<" -1" << endl;
          continue;         
        }
        else{
          cout << "#"<< test_case<<" "<<x<< endl;
          continue;
        }
      }else{
        cout << "#"<< test_case<<" -1" << endl;
        continue;             
      }
    }
    if(x == 0){
      int first = num.front();
      if(first < y){
        for(int a=0; a<ip.size()-1; a++){
          ans.push_back(y);
        }
        cout << "#" << test_case << " ";
        for (auto loop : ans)
        {
          cout <<loop ;
        }
        cout << endl;
        continue;
      }
    }
    while (!num.empty())
    {
      int cur = num.front();
      if (cur > y)
      {
        ans.push_back(y);
        num.pop();
        while (!num.empty())
        {
          ans.push_back(y);
          num.pop();
        }
      }
      else if (cur == y)
      {
        ans.push_back(y);
        num.pop();
      }
      else if (cur > x)
      {
        ans.push_back(x);
        num.pop();
        while (!num.empty())
        {
          ans.push_back(y);
          num.pop();
        }
      }
      else if (cur == x)
      {
        ans.push_back(x);
        num.pop();
      }
      else
      {
        int idx = -1;
        for (int b = 0; b < ans.size(); b++)
        {
          if(ans[b] == y){
            idx = b;
          }
        }

        if( idx == -1 ){
          ans.clear();
          for(int a=0; a<ip.size()-1; a++){
            ans.push_back(y);
          }
          break;
        } 
        else{
          ans[idx] = x;
          if(idx < ans.size()-1 ){
            for (int c = idx+1; c < ans.size(); c++){
              ans[c] = y;
            }
          }
          while (!num.empty())
          {
            ans.push_back(y);
            num.pop();
          }
        }
      }
    }
    cout << "#" << test_case << " ";
    for (auto loop : ans)
    {
      cout <<loop ;
    }
    cout << endl;
  }
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}