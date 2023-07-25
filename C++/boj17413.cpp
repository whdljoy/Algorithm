#include <iostream>
#include <string>
#include <vector>
using namespace std;
vector<char> ans;
vector<char> tmp;

void change_vec(){

  while(!(tmp.empty())){
    ans.push_back(tmp.back());
    tmp.pop_back();
  }
}

int main(){
  string s;
  bool st = false;
  getline(cin, s);

  for (int i = 0; i < s.length(); i++)
  {
    if(st){
      if(s[i]=='>'){
        ans.push_back(s[i]);
        st = false;
      }else{
        ans.push_back(s[i]);
      }
    }else{
      if(s[i] =='<'){
        change_vec();
        ans.push_back(s[i]);
        st = true;
      }else if(s[i]==' '){
        change_vec();
        ans.push_back(' ');
      }else{
      	tmp.push_back(s[i]);
	  }
    }
  }
  change_vec();
  for (int i = 0; i < ans.size(); i++){
    cout << ans[i];
  }
}