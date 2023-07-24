#include <iostream>
using namespace std;


int main(){
	
	
	int L,N;
	cin >> L;
	cin >> N;
	int *arr = new int[L+1];
	int *arr2 = new int[N+1];
	int ex=0;
	int ex_idx=0;
	for(int i=0; i<(N+1); i++){
		arr2[i] = 0;
	}
	for(int i=0; i<(L+1); i++){
		arr[i] = -1;
	}
	for(int i=0; i<N;i++){
		int st,end;
		cin >> st >>end;
		if(end-st+1 >ex){
			ex = end-st+1;
			ex_idx=i+1;
		}
		for(int j=st; j<=end; j++){
			if(arr[j] == -1){
				arr[j] = i+1;
			}
		}
	}	
	for(int i=1; i<L+1;i++){
		if(arr[i]!= -1){
			arr2[arr[i]]+=1;
		}
	}
	int mx=0;
	int ans=0;
	for(int i=1; i<(N+1); i++){
		if(mx < arr2[i]){
			mx = arr2[i];
			ans = i; 
		}
	}
	cout<<ex_idx<<endl<<ans;
	
	
}
