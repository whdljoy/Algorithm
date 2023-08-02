#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
    int n,check=0;
    vector <int> first;
    vector <int> third;
    cin>>n;
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        first.push_back(num);
    }

    third=first;
    do{
        if(check==1){            
            for(auto j:third){
                cout << j<<" ";
            }
            return 0;
        }
        if(third==first){
            check=1;
        }
    }
    while(next_permutation(third.begin(),third.end()));
    cout<<-1;
    return 0;
}