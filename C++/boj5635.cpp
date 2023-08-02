#include <iostream>
#include <vector>
#include <string>
using namespace std;
int birth(vector <int> &number){
    int tmp=0,tmp1=0,tmp2=0,idx=0;
    for(int i=0;i<number.size()/3;i++){
        if(tmp<number[3*i+2]){
            tmp=number[3*i+2];
            tmp1=number[3*i+1];
            tmp2=number[3*i];
            idx=i;
        }
        else if(tmp==number[3*i+2]){
            if(tmp1<number[3*i+1]){
                tmp1=number[3*i+1];
                tmp2=number[3*i];
                idx=i;
            }
            else if(tmp1==number[3*i+1]){
                if(tmp2<number[3*i]){
                    tmp2=number[3*i];
                    idx=i;
                }
            }
        }
    }
    return idx;
}
int birth_max(vector <int> &number){
    int idx=0;
    int tmp=2010;
    int tmp1=12;
    int tmp2=31;
    for(int i=0;i<number.size()/3;i++){
        if(tmp>number[3*i+2]){
            tmp=number[3*i+2];
            tmp1=number[3*i+1];
            tmp2=number[3*i];
            idx=i;
        }
        else if(tmp==number[3*i+2]){
            if(tmp1>number[3*i+1]){
                tmp1=number[3*i+1];
                tmp2=number[3*i];
                idx=i;
            }
            else if(tmp1==number[3*i+1]){
                if(tmp2>number[3*i]){
                    tmp2=number[3*i];
                    idx=i;
                }
            }
        }
    }
    return idx;
}
int main(){
    int n;
    vector <string> name;
    vector <int> number;
    cin >> n;
    for(int i=0;i<n;i++){
        int month,day,year;
        string en_name;
        cin >> en_name >> month >> day >> year;
        name.push_back(en_name);
        number.push_back (month);
        number.push_back (day);
        number.push_back (year);
    }
    cout <<name[birth(number)]<<endl<<name[birth_max(number)];
}