
#include<iostream>
#include <string>
#include <algorithm>
#include <malloc.h>
#include <cstring>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
  int **dp= new int *[1002];
  for (int i = 0; i < 1002; i++)
  {
    dp[i] = new int[1002];
    memset(dp[i], 0, sizeof(dp[i]));
  }
  freopen("sample_input.txt", "r", stdin);
  cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    string first, second;
    cin >> first >> second;
    int len1 = first.size();
    int len2 = second.size();
    for (int i = 1; i < len1 + 1; i++)
    {
      for (int j = 1; j<len2+1; j++){
        if( first[i-1] == second[j-1]){
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }
        else{
          dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    cout <<"#"<<test_case<<" "<< dp[len1][len2] << endl;
  }
  return 0;//정상종료시 반드시 0을 리턴해야합니다.
}