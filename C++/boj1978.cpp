#include <iostream>
#include <vector>
using namespace std;
int cal(vector <int> & prime){
    int val=0,answer=0;
    for(int i=0;i<prime.size();i++){
        val=0;
        if(prime[i]!=1){
            for(int j=1;j<prime[i]+1;j++){
                if(prime[i]%j==0){
                    val+=1;
                }
            }
        }
        if(val==2){
            answer+=1;
        }
    }
    return answer;
}


int main(){
    int n;
    vector <int> prime;
    cin >> n;
    for (int i=0;i<n;i++){
        int num;
        cin >>num;
        prime.push_back(num);
    }
    cout <<cal(prime);
}