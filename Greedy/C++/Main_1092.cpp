#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int cmp(int num1,int num2){
	if(num1>num2){
		return true;
	}
	return false;
}
int main(){
	int num,weight,answer=0;
	vector <int> wt,box;
	priority_queue <int> q;
	scanf("%d",&weight);
	for(int i=0;i<weight;i++){
		int temp;
		scanf("%d",&temp);
		wt.push_back(temp);
	}
	scanf("%d",&num);
	for(int i=0;i<num;i++){
		int temp;
		scanf("%d",&temp);
		q.push(temp);
		box.push_back(temp);
	}
	sort(wt.begin(),wt.end(),cmp);
	sort(box.begin(),box.end(),cmp);
	if(wt[0]<box[0]){
		cout<<"-1"<<endl;
	}
	else{
		while(!box.empty()){
			for(int i=0;i<weight;i++){
				if(!box.empty()){
					for(int j=0;j<box.size();j++){
						if(box[j]<=wt[i]){
							box.erase(box.begin()+j);
							break;
						}
					}				
				}
			}
			answer++;	
		}
		printf("%d\n",answer);
	}	
}