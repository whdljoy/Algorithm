#include <iostream>
#include<vector>
#include <algorithm>
using namespace std;
bool cmp(int n1,int n2){
	if( n1>n2){
		return true;
	}
	return false;
}

int main(){
	int num,group,answer=0;
	vector<int> height,minus;
	scanf("%d %d",&num,&group);
	for(int i=0;i<num;i++){
		int value;
		scanf("%d",&value);
		height.push_back(value);
	}
	for(int i=num-1;i>0;i--){
		minus.push_back(height[i]-height[i-1]);
	}
	sort(minus.begin(),minus.end());
	for(int i=0;i<minus.size()-(group-1);i++){
		answer+=minus[i];
	}
	printf("%d",answer);
}