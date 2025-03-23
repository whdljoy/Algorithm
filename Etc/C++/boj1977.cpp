#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int complete_val(vector <int> &vect){
    int start=1;
    int sum=0;
    int min=vect[3];
    while(1){
        int val_pow=pow(start,2);
        if (vect[0]<=val_pow && val_pow<=vect[1]){
            sum=sum+val_pow;
            if(min >val_pow){
                min=val_pow;
            }
        }
        else if(val_pow>vect[1]){
            break;
        }
        start++;
    }
    vect[2]=sum;
    vect[3]=min;
    return sum;
}
int main(){
    int m, n;
    vector <int> vect;
    cin >> m >>n;
    vect.push_back(m);
    vect.push_back(n);
    vect.push_back(0);
    vect.push_back(pow(10000,2));
    complete_val(vect);
    if(vect[2]==0){
        cout << "-1";
    }
    else{
        cout << vect[2]<<endl<<vect[3];
    }
    return 0;
}




