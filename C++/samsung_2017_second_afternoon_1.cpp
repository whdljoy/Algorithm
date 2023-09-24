#include <iostream>
#include <sstream>
#include <algorithm>
#include <deque>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_K 100
stringstream sb;

int chair[4][8];
int k;
int k_info[MAX_K][2];
deque<pair<int,int>> change;
/*
첫번째 줄에는 첫번째 팔각 의자에 앉아있는 사람들의 지역이 주어집니다.

두번째 줄에는 두번째 팔각 의자에 앉아있는 사람들의 지역이 주어집니다.

세번째 줄에는 세번째 팔각 의자에 앉아있는 사람들의 지역이 주어집니다.

네번째 줄에는 네번째 팔각 의자에 앉아있는 사람들의 지역이 주어집니다.

각 지역은 12시 방향부터 시계 방향 순서대로 공백없이 주어지며, 0은 북쪽 지방, 1은 남쪽 지방을 의미합니다.

다섯번째 줄에는 회전 횟수 k가 주어집니다.

6번째 줄부터 (k+5)번째 줄까지 회전시킬 팔각의자의 번호 n와 방향 d가 공백을 사이에 두고 주어집니다. 방향은 1과 -1로 주어지며 1은 시계방향, -1은 반시계 방향을 의미합니다.

1 ≤ k ≤ 100

*/

void rotate(int start, int dir){
  int front = start - 1;
  int back = start + 1;
  if(0<= front && front <4){  //현재 3 과 앞의6 비교

  }
  if(0<= back && back <4){  //뒤 3 과 현재 6 비교
    
  }  
}

void simulate(){
  for (int y = 0; y < k;y++){
    change.push_back({k_info[y][0], k_info[y][1]});
    rotate(k_info[y][0], k_info[y][1]);

    // deque에서 빼면서 의자변화시키기
  }
}


void input(){
  for (int y = 0; y < 4;y++){
    for (int x = 0; x < 8;x++){
      cin >> chair[y][x];
    }
  }
  cin >> k;
  for (int y = 0; y < k;y++){
    cin >> k_info[y][0] >> k_info[y][1];
  }
}

void run(){
  input();
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);


    return 0;
}