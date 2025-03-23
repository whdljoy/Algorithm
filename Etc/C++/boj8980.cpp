#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>
using namespace std;
bool comp(tuple<int, int ,int> &n1, tuple<int,int,int> &n2 ){
  if(get<1>(n1) == get<1>(n2)){
    return get<0>(n1) < get<0>(n2);
  }
  return get<1>(n1) < get<1>(n2);
}
int main(){
  int n ,storage,info;
  vector <tuple <int,int,int>> target;
  cin >> n >> storage >> info;
  for( int i = 0; i < info ; i++){
    int n1,n2,n3;
    cin >> n1 >> n2 >>n3;
    target.push_back(make_tuple(n1,n2,n3));
  }
  sort(target.begin(),target.end(),comp);

  for(auto i:target){
    cout << get<0>(i) <<get<1>(i)<<endl;
  }
  int c_sum=0;
  vector<int> capacity(n+1,storage);
  for(int i=0;i<info;i++){
    int min=*min_element(capacity.begin()+get<0>(target[i]),capacity.begin()+get<1>(target[i]));

    if(get<2>(target[i]) <= min){
      c_sum+=get<2>(target[i]);
      for(int j=get<0>(target[i]); j < get<1>(target[i]); j++){
        capacity[j]-=get<2>(target[i]);
      }
    }
    else{
      c_sum+=min;
      for(int j=get<0>(target[i]); j < get<1>(target[i]); j++){
        capacity[j]-=min;
      }
    }
  }
  cout<<c_sum<<endl;
}