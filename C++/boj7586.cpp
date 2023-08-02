#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
void cal(int dir,vector <pair<int,int>> &weight,vector <int> &answer){
    pair<int,int> tmp=weight[dir];
    int min=0;
    for(int i=0;i<weight.size();i++){
        if(tmp.first<weight[i].first &&tmp.second<weight[i].second){
            min++;
        }
    }
    min=min+1;
    answer.push_back(min);

}

int main(){
    int n;
    vector <pair<int,int>> weight;
    vector <int> answer;
    scanf("%d",&n);
    for(int i=0; i<n;i++){
        int num1,num2;
        cin >>num1>>num2;
        weight.push_back(make_pair(num1,num2));
    }
    for(int j=0;j<n;j++){
        cal(j,weight,answer);
    }
    for(auto i:answer){
        cout <<i<<" ";
    }
}