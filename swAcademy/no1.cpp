#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	//freopen("input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
    int check[10] = {0,};
		bool exist = true;
		int val;
		cin >> val;
		string num;
		num = to_string(val);
		while(1){
			for (int i = 0; i < num.length();i++){
				check[num[i] - '0'] = 1;
			}

			int ct = count(begin(check), end(check), 1);
			if(ct == 10){
				cout << "#" << test_case << " " << num << endl;
				break;				
			}
			else{
				int tmp = stoi(num)+val;
				num = to_string(tmp);
			}
		}



	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}