#include <iostream>
using namespace std;
#include <vector>
#include <algorithm>
bool compare(pair<int,int> num1,pair<int,int>num2){
    if(num1.second==num2.second){
        return num1.first<num2.first;
    }
    
    return num1.second<num2.second;
}
int main(){
    int n;
    vector <pair<int,int>> dot;
    scanf("%d",&n);
    for (int i=0;i<n;i++){
        int num1,num2;
        scanf("%d %d",&num1,&num2);
        dot.push_back(make_pair(num1,num2));
    }
    sort(dot.begin(),dot.end(),compare);
    for(auto i:dot){
        printf("%d %d\n",i.first,i.second);
    }
}