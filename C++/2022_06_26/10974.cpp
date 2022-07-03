#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
    int n;
    vector <int> permutation;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        permutation.push_back(i+1);
    }
    do{
        for(auto i:permutation){
            cout<<i<<" ";
        }
        printf("\n");
    }
    while(next_permutation(permutation.begin(),permutation.end()));

    
}