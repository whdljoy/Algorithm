#include <iostream>
#include <set>
using namespace std;


int main(){

    set <int> mp;
    for(int i=0; i<10; i++){
        int cur;
        cin >> cur;
        mp.insert(cur%42);
    }
    cout << mp.size();

}