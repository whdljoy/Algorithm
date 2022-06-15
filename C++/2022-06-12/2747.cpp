#include<iostream>
using namespace std;
int fibo( int *memo,int n){
    if(memo[n]==0){
        memo[n]=fibo(memo,n-1)+fibo(memo,n-2);
    }
    return memo[n];
}
int main(){
    int n;
    cin >> n;
    int memo[90]={0,};
    memo[1]=1;
    memo[2]=1;
    cout << fibo(memo,n);
}