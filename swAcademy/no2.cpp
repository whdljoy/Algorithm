
#include<iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
    vector<int> check;
    int N, M;
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        int temp = (M >> i) & 1;
        check.push_back(temp);
    }
    int ct = count(begin(check), end(check), 1);
    if(N == ct){
        cout << "#" << test_case << " ON"<<endl;
    }else{
        cout << "#" << test_case << " OFF"<<endl;
    }
  }
  return 0;//정상종료시 반드시 0을 리턴해야합니다.
}