#include <iostream>
using namespace std;

int prime(int n, bool*isPrime){
    int answer=0;
    for(int i=n+1;i<2*n+1;i++){
        if(isPrime[i]==false){
            answer+=1;
        }
    }
    return answer;

}

int main(){
    int n=1;
    bool isPrime[123456*2+1]={false,};
    isPrime[0]=true;
    isPrime[1]=true;
    for(int i=2;i<123456*2+1;i++){
        if(isPrime[i]==false){
            for(int j=i*2;j<123456*2+1;j=j+i){
                isPrime[j]=true;
            }
        }
    }
    while(1){
        scanf("%d",&n);
        if(n==0){
            break;
        }
        cout<<prime(n,isPrime)<<endl;
    }
    return 0;
}