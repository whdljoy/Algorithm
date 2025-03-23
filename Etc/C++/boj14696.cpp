#include <iostream>
#include <algorithm>
using namespace std;


int main(){
	int N;
	cin >> N;
	char *ans = new char[N];
	//라운드 1에서 A가내는 딱지에 나온 그림의 총 개수 a  뒤따라 나오는 a 개의 그림 갯수
	// 라운드 1에서 b b의 그림 갯수  
//	만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
//	별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
//	별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모가 많은 쪽의 딱지가 이긴다.
//	별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모가 많은 쪽의 딱지가 이긴다.
//	별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.

// 4 별 3 동그라미 2 네모 1 세모 
	for (int i=0; i<N;i++){
		int Na,Nb;
		int *first = new int[5];
		int *second = new int [5];
		fill(first,first+5,0); 
		fill(second,second+5,0);
		cin >>Na; 
		for(int a=0; a<Na;a++ ){
			int cur;
			cin >> cur;
			first[cur]++;
		}
		cin >>Nb; 
		for(int b=0; b<Nb;b++ ){
			int cur;
			cin >> cur;
			second[cur]++;
		}
		//	만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
		if(second[4] > first[4]){
			ans[i] ='B';
		}else if(second[4] < first[4] ){
			ans[i] ='A';
		}else{ //별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
			if(second[3] > first[3]){
				ans[i] ='B';
			}else if(second[3] < first[3] ){
				ans[i] ='A';
			}else{
				if(second[2] > first[2]){
					ans[i] ='B';
				}else if(second[2] < first[2] ){
					ans[i] ='A';
				}else{
					if(second[1] > first[1]){
						ans[i] ='B';
					}else if(second[1] < first[1] ){
						ans[i] ='A';
					}else{
						ans[i] ='D';
					}
				}				
			}		
		}			
	}
	for(int i=0; i<N;i++){
		cout<<ans[i]<<endl;
	}
}