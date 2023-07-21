#include <iostream>
#include <vector>

using namespace std;

int main(){
    vector <int> info;
    for(int i=0; i<10;i++){
        int cur;
        cin >> cur;
        info.push_back(cur);
    }
    int ans=0;
    for(int i=0; i<10; i++){
        int cur = ans + info[i];
        if (cur <= 100){
            ans = cur;
        }else{
            int md = cur - 100; // 100보다 큰 정도
            int before = 100 - ans;
            if (md <= before){
                ans = cur;
            }else{
                break;
            }

        }
    }
    cout <<ans;
}