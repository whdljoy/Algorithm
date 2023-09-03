#include <iostream>
using namespace std;

void run();

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",w,stdout)
#define NL "\n"

int main(){
	  cin.tie(NULL); //입출력 묶음 해제
    ios_base::sync_with_stdio(false);
    run();
    return 0;
}


void run(){
  int tc;
  cin >> tc;

  for(int i=1; i<=tc; i++){
      int N, M;
      cin >> N >> M;
      int lastNBit = (1 << N) - 1;  // 111...1 (길이 N)
      if (lastNBit == (M & lastNBit)) {
          cout << "#" << i << " " << "ON" << endl;
      } else {
          cout << "#" << i << " " << "OFF" << endl;
      }
  }
}

