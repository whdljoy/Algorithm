#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

bool compare(string s1,string s2){
    if(s1.length()==s2.length()){
        return s1<s2;
    }

    return s1.length()<s2.length();
}

int main(){
    int n;
    vector<string> re_st;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        string tmp;
        cin >>tmp;
        re_st.push_back(tmp);
    }
    sort(re_st.begin(),re_st.end(),compare);
    re_st.erase(unique(re_st.begin(),re_st.end()),re_st.end());
    for(auto i:re_st){
        cout <<i<<endl;
    }
}