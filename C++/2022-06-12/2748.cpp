#include<iostream>
using namespace std;
long long int fibo(long long int *memo,int n){
    if(memo[n]==0){
        memo[n]=fibo(memo,n-1)+fibo(memo,n-2);
    }
    return memo[n];
}
int main(){
    int n;
    cin >> n;
    long long int memo[92]={0,};
    memo[1]=1;
    memo[2]=1;
    cout << fibo(memo,n);
}