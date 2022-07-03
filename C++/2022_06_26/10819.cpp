#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
int cal_max(vector <int> &max_v){
    int max_num=0;
    for(int i=0;i<max_v.size()-1;i++){
        max_num+=abs(max_v[i]-max_v[i+1]);
    }
    return max_num;
}
int main(){
    int n,max_num=0;
    vector <int> max_v;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        max_v.push_back(num);
    }
    sort(max_v.begin(),max_v.end());
    do{
        int tmp=cal_max(max_v);
        if(max_num<tmp){
            max_num=tmp;
        }   
    }
    while(next_permutation(max_v.begin(),max_v.end()));
    cout<<max_num;

}

    else if (A.length()==B.length()){
        int num1=0,num2=0;
        for(int i=0;i<A.length();i++){
            if(isdigit(A[i])){
                num1+=A[i];
            }
             if(isdigit(B[i])){
                num2+=B[i];
            }
        }
        if(num1>num2){
            return A>B;
        } 
        else if (num1==num2){
            if( A.compare(B) > 0){
                return A<B;
            }
            else{
                return A>B;
            }
        }
        return A<B;       
    }