#include <iostream>
#include <vector>
#include <string>
#include <cctype>
#include <algorithm>
using namespace std;
bool compare(string A, string B){
    if (A.length()!=B.length()){
        return A.length()<B.length();
    }
    int num1=0,num2=0;
    if (A.length()==B.length()){
        for(int i=0;i<A.length();i++){
            if(isdigit(A[i])){
                num1+=A[i]-'0';
            }
             if(isdigit(B[i])){
                num2+=B[i]-'0';
            }
        }
        if(num1 != num2){
            return num1<num2;
        }
    }
    return A<B;
}

int main(){
    int n;
    vector <string> serial;
    scanf("%d",&n);
    for (int i=0;i<n;i++){
        string tmp;
        cin>> tmp;
        serial.push_back(tmp);
    }
    sort(serial.begin(),serial.end(),compare);
    for(auto i:serial){
        cout << i<<endl;
    }
}