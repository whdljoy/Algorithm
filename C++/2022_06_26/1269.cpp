#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    int num_a,num_b,same_v=0;
    vector <int> com_A,com_B;
    scanf("%d %d",&num_a,&num_b);
    for(int i=0;i<num_a;i++){
        int tmp;
        scanf("%d",&tmp);
        com_A.push_back(tmp);
    }
    for(int i=0;i<num_b;i++){
        int tmp;
        scanf("%d",&tmp);
        com_B.push_back(tmp);
    }
    sort(com_A.begin(),com_A.end());
    sort(com_B.begin(),com_B.end());
    vector<int> buff(com_A.size() + com_B.size());
    vector<int> buff2(com_A.size() + com_B.size());
    auto iter = set_difference(com_A.begin(), com_A.end(), com_B.begin(), com_B.end(), buff.begin());
    buff.erase(iter, buff.end());
    auto iter2 = set_difference(com_B.begin(), com_B.end(), com_A.begin(), com_A.end(), buff2.begin());
    buff2.erase(iter2, buff2.end());
    printf("%ld",buff.size()+buff2.size());
}