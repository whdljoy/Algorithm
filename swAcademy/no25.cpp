
#include<iostream>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	//freopen("input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		priority_queue<int, vector<int>, greater<int> > min_q;
		priority_queue<int> max_q;
		int n, first,sum=0;
		cin >> n >> first;
		min_q.push(first);
		for (int i = 0; i < n; i++)
		{
			int num1, num2;
			cin >> num1 >> num2;
			if(num1 < min_q.top()){
				max_q.push(num1);
			}
			else{
				min_q.push(num1);
			}
			if(num2 < min_q.top()){
				max_q.push(num2);
			}
			else{
				min_q.push(num2);
			}
			int max_len = max_q.size();
			int min_len = min_q.size();
			if ((max_len - min_len) > 1)
			{
				min_q.push(max_q.top());
				max_q.pop();
			}
			else if((min_len - max_len) > 1){
				max_q.push(min_q.top());
				min_q.pop();
			}			
			if(max_q.size() > min_q.size()){
				sum = sum + max_q.top()% 20171109;;
			}	
			else{
				sum = sum + min_q.top()% 20171109;;
			}
			sum = sum % 20171109;
		}
		cout << "#" << test_case << " " << sum<<endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}