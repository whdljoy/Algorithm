#include <cstdio>
#include <iostream>

using namespace std;

int map[18][18];
int result;
int size_map;

int dfs(int typ,int x,int y){
	// 유효성 확인
	if(map[x][y]==1||x>size_map-1||y>size_map-1){
		return 0;
	}
	if(typ==2&&(map[x][y-1]==1||map[x-1][y]==1)){
		return 0;
	}
	// 도착 확인
	if(x==size_map-1&&y==size_map-1){
		result=result+1;
	}
	if(typ!=1) 		dfs(3,x,y+1);
	dfs(2,x+1,y+1);
	if(typ!=3)		dfs(1,x+1,y);
	
}



int main(int argc, char* argv[]) {
	
	scanf("%d",&size_map);
	for(int i=0; i<size_map;i++){
		for(int j=0; j<size_map; j++){
			scanf("%d",&map[j][i]);
		}
	}
	dfs(1,1,0);
	printf("%d\n",result);
	
}