#include<iostream>
#include<vector>
#include<string>
#include <algorithm>
using namespace std;
string check(string a){
    vector <int> checking(10,0);
    string answer="BIGGEST";
    for(int i=a.length()-1;i>0;i--){
        if((a[i]-'0') > (a[i-1]-'0')){
            answer=a.substr(0,i-1);
            for(int j=i-1; j<a.length();j++){
                checking[a[j]-'0']+=1;
            }
            for(int j=(a[i-1]-'0')+1;j<10;j++){
                if(checking[j] > 0){
                    checking[j]-=1;
                    answer.append(to_string(j));
                    break;
                }
            }
            for(int j=0;j<10;j++){
                if(checking[j] > 0){
                    while(checking[j]!=0){
                        checking[j]-=1;
                        answer.append(to_string(j));    
                    }
                }
            }
            return answer;
        }
    }
    return answer;
}

int main(){
    int n;
    string answer;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        string num;
        cin >>num;
        cout<< check(num) <<endl;
    }
}