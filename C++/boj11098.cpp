#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int main(){
    int n,p;
    vector <string> name;
    vector <int> dollar;
    cin>>n;
    for (int i=0;i<n;i++){
        cin >>p;
        name.clear();
        dollar.clear();
        for (int j=0;j<p;j++){
            int price;
            string Nam;
            cin >> price >>Nam;
            name.push_back(Nam);
            dollar.push_back(price);
        }
        cout << name[max_element(dollar.begin(), dollar.end()) - dollar.begin()] <<endl;
    }
}

