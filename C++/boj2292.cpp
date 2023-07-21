#include <iostream>
using namespace std;

int main(){

    int N;
    cin >> N;
    int ans =1;
    int start =1;
    int ran = 6;
    while(1){
        if (N <= start){
            cout << ans;
            break;
        }else if (N >start){
            start = start + ran*ans;
            ans +=1;
        }
    }

}