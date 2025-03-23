#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int cal(int num){
    if(num<2){
        return 1;
    }
    else{
        for(int i=2;i<num;i++){
            if(num%i==0){
                return 1;
            }
        }
    }
    return 0;
}

vector<int> prime(int m ,int n){
    vector <int> answer;
    for(int i=m;i<n+1;i++){
        if(cal(i)==0){
            answer.push_back(i);
        }
    }
    return answer;
}
 
int main(){
    int m,n,sum=0;
    vector <int> answer;
    cin >> m >> n;
    answer=prime(m,n);
    for(int i=0;i<answer.size();i++){
        sum+=answer[i];
    }
    if(sum==0){
        cout<<-1;
    }
    else{
        cout << sum<<endl<<*min_element(answer.begin(),answer.end());
    }
}