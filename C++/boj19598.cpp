#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int main(){
    vector<pair<int,int>> heap;
    priority_queue<int,vector<int>, greater<int>> q;
    int num,answer=1;
    scanf("%d",&num);
    for(int i=0;i<num;i++){
        int n1,n2;
        scanf("%d %d",&n1,&n2);
        heap.push_back(make_pair(n1,n2));
    }
    sort(heap.begin(),heap.end());
		q.push(heap[0].second);
    for(int j=1;j<num;j++){
				for(int a=0;a<q.size();a++){
						if(q.top()<=heap[j].first){
							q.pop();
						}
						else{
							break;
						}
					}
				q.push(heap[j].second);
				answer = max(answer,int(q.size()));
			}
		cout<<answer;

}