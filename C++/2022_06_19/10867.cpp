#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    vector<int> sorting;
    int n;
    cin >>n;
    for(int i=0;i<n;i++){
        int num;
        cin >>num;
        sorting.push_back(num);
    }
    sort(sorting.begin(),sorting.end());
    sorting.erase(unique(sorting.begin(),sorting.end()),sorting.end());
    for(auto i:sorting){
        cout <<i<<" ";
    }

}