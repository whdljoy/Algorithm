#include <iostream>
#include <sstream>
#include <algorithm>
#include <vector>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
stringstream sb;

int tc,N,M,L;
vector <int> origin;


void cal(){
  for (int i = 0; i < M; i++){
    char ch;
    cin >> ch;
    if(ch == 'I'){
      int val, position;
      cin >> val >> position;
      auto it = origin.begin();
      advance(it, position);
      origin.insert(it, val);
    }
    else if (ch == 'D')
    {
      int position;
      cin >> position;
      auto it = origin.begin();
      advance(it, position);
      origin.erase(it);
      origin.shrink_to_fit();
    }
    else if (ch == 'C')
    {
      int p1, p2;
      cin >> p1 >> p2;
    }
  }
}

void input(){
  cin >> N >> M >> L;
  for (int i = 0; i < N;i++){
    int cur;
    cin >> cur;
    origin.push_back(cur);
  }
}
void run(){
  cin >> tc;
  for (int i = 1; i <= tc; i++){
    input();
    cal();
    sb << "#" << i << " ";
  }
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);


    return 0;
}