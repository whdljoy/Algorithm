#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
bool cmp(int n1,int n2){
	if(n1> n2){
		return true;
	}
	return false;
}
int main(){
	int sensor, k,answer=0;
	vector <int> distance,between;

	cin >> sensor >> k;
	for(int i=0;i< sensor;i++){
		int num;
		scanf("%d",&num);
		distance.push_back(num);
	}
	sort(distance.begin(),distance.end());
	distance.erase(unique(distance.begin(),distance.end()),distance.end());
	if(k>=sensor){
		printf("0\n");
	}
	else{
		for(int i=0;i<distance.size()-1;i++){
			between.push_back(abs(distance[i]-distance[i+1]));
		}
		sort(between.begin(),between.end(),cmp);
		for(int j=0+k-1;j<between.size();j++){
			answer+=between[j];
		}
		printf("%d\n",answer);
	}



}