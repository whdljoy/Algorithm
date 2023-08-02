#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int main(){
    int n;
    vector <string> permutation;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        string tmp;
        cin>>tmp;
        permutation.push_back(tmp);
    }
    for(int i=0;i<n;i++){
        if(next_permutation(permutation[i].begin(),permutation[i].end())){
            cout<<permutation[i];
            printf("\n");
        }
        else{
            reverse(permutation[i].begin(),permutation[i].end());
            cout<<permutation[i];
            printf("\n");
        }
    }
}