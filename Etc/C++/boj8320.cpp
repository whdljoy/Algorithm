#include <iostream>
#include <cstring>
using namespace std;



int main(){

    int N,cent;
    cin >> N;
    if( N %2 == 0){
        cent = N/2;
    }else{
        cent =N/2 +1;
    }
    int *check = new int[N+1];
    int ans=1;
    if (N >1){
        for (int j=2; j<=N; j++){
            int tar; //체크해야되는 중간값 
            if( j %2 == 0){
                tar = j/2;
            }else{
                tar =j/2 +1;
            }
            memset(check,0,sizeof(int)*(N+1));
            for(int i=1; i<=tar; i++){
                if(j%i==0 && check[i] ==0 && check[j/i]==0){  // j는 현재 타켓값  // tar
                    ans +=1;
                    check[i] =1;
                    check[j/i]=1;
                }
            }
        }
    }
    cout <<ans;

}