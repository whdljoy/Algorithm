#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int main(){
    string sentance;
    cin >>sentance;
    vector <string> divid;
    for(int i=0;i<sentance.length();i++){
        divid.push_back(sentance.substr(i));
    }
    sort(divid.begin(),divid.end());
    for(auto i:divid){
        cout <<i<<endl;
    }
}