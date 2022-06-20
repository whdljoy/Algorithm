#include <iostream>
using namespace std;
#include <string>
#include <vector>

int main(){
    int n;
    vector <int> vct;
    scanf("%d",&n);
    for (int i=0;i<n;i++){
        string dir;
        int num;
        cin >> dir;
        if(dir=="push"){
            cin>>num;
            vct.push_back(num);
        }
        else if(dir=="pop"){
            if(vct.empty()){
                cout<<-1<<endl;
            }
            else{
                cout<<vct.front()<<endl;
                vct.erase(vct.begin());
            }
        }
        else if(dir=="size"){
            cout <<vct.size()<<endl;
        }               
        else if(dir=="empty"){
            if(vct.empty()){
                cout <<1<<endl;
            }
            else{
                cout <<0<<endl;
            }
        }
        else if(dir=="front"){
            if(vct.empty()){
                cout<<-1<<endl;
            }
            else{
                cout<<vct.front()<<endl;
            }
        }
        else if(dir=="back"){
            if(vct.empty()){
                cout<<-1<<endl;
            }
            else{
                cout<<vct.back()<<endl;
            }
        }                 
    }
}