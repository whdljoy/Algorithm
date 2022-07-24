#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
	int village;
	long long int p_sum=0,check=0;
	vector <pair<long long int,long long int>> val;
	scanf("%d",&village);
	for(int i=0;i<village;i++){
		long long int num1,num2;
		cin >> num1>>num2;
		p_sum+=num2;
		val.push_back(make_pair(num1,num2));
	}
	sort(val.begin(),val.end());
	for(int i=0;i<val.size();i++){
		check+=val[i].second;
		if((p_sum+1)/2<=check){
			cout<<val[i].first<<endl;
			break;
		}
	}

}
