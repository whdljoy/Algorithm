#include <iostream>
#include <algorithm>
using namespace std;


int main(){
	int N, K;
	cin >> N >> K;
	int*man = new int[7];
	int *woman = new int[7];
	fill(man,man+7,0);
	fill(woman,woman+7,0);
	
	for(int i=0; i<N; i++){
		int S,Y;
		cin >> S >> Y; // 성별 ,학년
		if (S ==0) {// 여자 
			woman[Y]+=1;
		}else{
			man[Y] +=1;
		}
	}
	int ans=0;
	for(int i=1; i<7;i++){
		if(woman[i]!=0){
			ans +=(woman[i]/K);
			if(woman[i]%K !=0){
				ans+=1;
			}
		}
		if(man[i]!=0){
			ans +=(man[i]/K);
			if(man[i]%K !=0){
				ans+=1;
			}
		}		
	}
	cout<<ans;

	
}