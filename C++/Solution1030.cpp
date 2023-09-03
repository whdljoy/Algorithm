#include <iostream>
#include <list>
#include <string>
#include <vector>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
int TC = 10;
vector <int> secret;
void print(){
  auto it = secret.begin();
  for (int i = 0; i < 10; i++)
  {
    cout << *(it)++ << " ";
  }
  cout << NL;
}
void change_command(string command){
  if(command == "I"){ //I(삽입) x, y, s
    int x, y, s;
    auto it = secret.begin();
    cin >> x >> y;
    for (int i = 0, insertIdx = x; i < y; i++, insertIdx++)
    {
      int k;
      cin >> k;
      secret.insert(secret.begin() + insertIdx, k);
    }
  }
  else if (command == "D")
  {
    auto it = secret.begin();
    int x,y;
    cin >> x;
    advance(it, x);
    cin >> y;
    for (int i = 0; i < y;i++){
      secret.erase(it);
    }
  }
  else if (command =="A"){
    int y;
    cin >> y;
    for (int i = 0; i < y;i++){
      int cur;
      cin >> cur;
      secret.push_back(cur);
    }
  }
}
void input()
{
  int num;
  cin >> num;
  for (int i = 0; i < num;i++){
    int cur;
    cin >> cur;
    secret.push_back(cur);
  }
  cin >> num;
  for (int i = 0; i < num;i++){
    string command;
    cin >> command;
    change_command(command);
  }
}
void run(){
  
  for(int i=1; i<=TC; i++){
    input();
    cout << "#" << i<<" ";
    print();
    secret.clear();
  }
}
int main(){
  fileio;
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  run();

  return 0;
}