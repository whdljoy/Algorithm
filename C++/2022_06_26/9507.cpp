#include <iostream>
#include <vector>
using namespace std;

long long int koong(long long int *memo,int n){
    if(memo[n]==0){
        memo[n]=koong(memo,n-1)+koong(memo,n-2)+koong(memo,n-3)+koong(memo,n-4);
    }
    return memo[n];
}

int main(){
    int n;
    cin>>n;
    long long int memo[69]={0,};
    memo[0]=1;
    memo[1]=1;
    memo[2]=2;
    memo[3]=4;
    for(int i=0;i<n;i++){
        int num;
        cin >>num;
        cout <<koong(memo,num)<<endl;
    }

}