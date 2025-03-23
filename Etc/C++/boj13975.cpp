#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;



int main(){
	int n,k;
	cin >> n;
	for(int i = 0; i < n; i++){
		priority_queue <long long int,vector<long long int>, greater<long long int>> chapter;
		scanf("%d",&k);
		long long int c_sum=0;
		for (int j = 0; j < k; j++ ){
			int num;
			scanf("%d",&num);
			chapter.push(num);
			}
		for(int a=0; a < k-1 ; a++){
			long long int tmp=0;
			tmp += chapter.top();
			chapter.pop();
			tmp += chapter.top();
			chapter.pop();
			chapter.push(tmp);
			c_sum+=tmp;			
		}
		cout<<c_sum<<endl;
	}
	return 0;
}