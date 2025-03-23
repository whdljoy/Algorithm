#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(int num1,int num2){
    return num1>num2;
}


int main(){
    int n,min=0;
    vector <int> first,second;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        first.push_back(num);
    }
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        second.push_back(num);
    }
    sort(first.begin(),first.end());
    sort(second.begin(),second.end(),compare);
    for(int j=0;j<first.size();j++){
        min+=first[j]*second[j];
    } 
    printf("%d",min); 

}
