#include <iostream>
using namespace std;
#include <vector>
#include <algorithm>

int main(){
    int n;
    vector <pair<int,int>> dot;
    scanf("%d",&n);
    for (int i=0;i<n;i++){
        int num1,num2;
        scanf("%d %d",&num1,&num2);
        dot.push_back(make_pair(num1,num2));
    }
    sort(dot.begin(),dot.end());
    for(auto i:dot){
        printf("%d %d\n",i.first,i.second);
    }
}