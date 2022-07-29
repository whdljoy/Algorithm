#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
  int n ,storage,info;
  vector <tuple <int,int,int>> target;
  cin >> n >> stroage >> info;
  for( int i = 0; i < info ; i++){
    int n1,n2,n3;
    cin >> n1 >> n2 >>n3;
    target.push_back(make_tuple(n1,n2,n3));
  }

}