#include <iostream>
#include <sstream>
#include <algorithm>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
stringstream sb;

int tc;


void input(){
  
}
void run(){
  cin >> tc;
  for (int i = 1; i <= tc; i++){
    input();
    sb << "#" << i << " ";
  }
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);


    return 0;
}