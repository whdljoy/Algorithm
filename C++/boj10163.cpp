#include <iostream>
#include <cstring>
using namespace std;
int info[1001][1001];

int main(){
	
	int N;
	cin >> N;

	memset(info,0,sizeof(info));
	for(int i=0;i<N;i++){   // 가장 왼쪾 아래칸의 번호와 너비 높이  
		int sx,sy,w,h;
		cin >> sx >> sy >> w>>h;
		for(int a =0; a<h;a++){
			for(int b=0; b<w;b++ ){
				info[sy+a][sx+b]=i+1;
			}
		}
	}
	int *ans=new int[N+1];
	for(int i=0 ;i<=N;i++){
		ans[i]=0;
	}
	for(int i=0;i<1001;i++){
		for(int j=0; j<1001;j++){
			if(info[i][j]!=0){
				ans[info[i][j]]+=1;
			}
		}
	}
	for(int i=0;i<N;i++){
		cout<<ans[i+1]<<endl;
	}
	return 0;
}