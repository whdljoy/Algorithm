#include <iostream>
#include <sstream>
#include <algorithm>
#include <string.h>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX 20
#define K_MAX 1000
stringstream sb;
/*
첫째줄에 말판의 세로 크기 n, 가로 크기 m, 정육면체의 처음 위치 x, y, 그리고 굴리기 횟수 k가 공백을 사이에 두고 주어집니다. 정육면체의 처음 위치 x는 위부터 아래까지 0부터 n-1까지 차례대로 번호를 매겨 구분하며, y는 왼쪽에서 오른쪽까지 차례대로 번호를 매겨 구분합니다.

둘째줄부터 n+1번째 줄까지 지도에 쓰여져있는 수가 주어집니다.

마지막 줄에는 굴리기 방향이 공백을 사이에 두고 주어집니다. 1이상 4이하의 숫자 중 하나이고, 순서대로 동쪽, 서쪽, 북쪽, 남쪽을 의미합니다. 말판의 범위를 벗어나는 방향이 주어졌을 경우에는 굴리기를 수행하지 않습니다.

1 ≤ n, m ≤ 20

0 ≤ x ≤ n - 1, 0 ≤ y ≤ m-1

1 ≤ k ≤ 1000

0 ≤ 말판에 쓰여진 수 ≤ 9
칸에 쓰여져 있는 수가 0이면, 주사위의 바닥면에 쓰여져있는 수가 칸에 복사됩니다. 이때 정육면체의 숫자는 변하지 않습니다.

칸에 쓰여져 있는 수가 0이 아니면 칸에 쓰여져있는 수가 정육면체 바닥면으로 복사되며, 해당 칸의 수는 0이 됩니다.

주사위가 초기에 위치하는 칸은 초기값이 0이라고 가정해도 좋습니다.

동쪽, 서쪽, 북쪽, 남쪽

*/

int origin[MAX][MAX];
int dir[K_MAX];
int N, M, X, Y, k;

int dx[5] = {0, 1, -1, 0, 0};
int dy[5] = {0, 0, 0, -1, 1};
int f = 2;
int u = 1;
int r = 3;
int dices[7] = {0, 0, 0, 0, 0, 0, 0};

bool in_range(int x ,int y){
  return 0 <= x && x < M && 0 <= y && y < N;
}

void cal(){
  int cy, cx;
  for (int i = 0; i < k; i++)
  {

    cx = X + dx[dir[i]];
    cy = Y + dy[dir[i]];
    if(in_range(cx,cy)){
      int tmp ;
      if(dir[i] == 1){
        tmp = r;
        r = u;
        u = 7 - tmp;
      }
      else if (dir[i] == 2)
      {
        tmp = u;
        u = r;
        r = 7 -tmp;
      }
      else if (dir[i] == 3)
      {
        tmp = u;
        u = f;
        f = 7-tmp;
      }
      else if (dir[i] == 4)
      {
        tmp = f;
        f = u;
        u = 7 - tmp;
      }
      if (origin[cy][cx] == 0)
      {
        origin[cy][cx] = dices[7-u];
      }
      else
      {
        dices[7-u] = origin[cy][cx];
        origin[cy][cx] = 0;
      }
      X = cx;
      Y = cy;
      sb << dices[u] << NL;  
    }
  }
}

void input()
{
  cin >> N >> M >> Y >> X >> k;
  memset(origin, 0, sizeof(origin));
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < M;x++){
      cin >> origin[y][x];
    }
  }

  for (int i = 0; i < k;i++){
    cin >> dir[i];
  }
}

void run(){
  input();
  cal();
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();
   cout << sb.str();

   return 0;
}