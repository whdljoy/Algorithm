#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;


int main(){
    vector <int> number;
    for(int i=0;i<9;i++){
        int num;
        cin >> num;
        number.push_back(num);
    }
    int an1=*max_element(number.begin(),number.end());
    cout << an1 <<endl<<find(number.begin(), number.end(),an1) - number.begin()+1;
}
