#include <iostream>
#include <algorithm>
using namespace std;


int main(){
    int num;
    int ans= 0;
    cin >> num;
    int *info = new int[num];
    int *dp = new int[num];
    for(int i=0; i<num;i++){
        int cur;
        cin >>cur;
        info[i]= cur;
    }
    sort(info,info+num);
    dp[0] = info[0];
    ans = dp[0];
    for(int i=1;i<num;i++){
        dp[i]=dp[i-1] +info[i];
        ans += dp[i];

    }
    cout<<ans;

}