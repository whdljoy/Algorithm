#include <iostream>
#include <sstream>
#include <algorithm>
#include <string.h>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_L 50
stringstream sb;
/*
첫째 줄에 도로의 세로 크기 n과 가로 크기 m이 주어집니다.

둘째 줄에 자율주행 자동차의 초기 위치 (x, y)와 바라보는 방향 d가 주어집니다. d는 0부터 3까지 숫자로 주어지고, 순서대로 북쪽, 동쪽, 남쪽, 서쪽을 의미합니다. 자동차의 처음 위치 x는 위쪽에서부터 아래쪽까지 0부터 n-1까지 차례대로 번호를 매겨 구분하며, y는 왼쪽에서 오른쪽까지 차례대로 번호를 매겨 구분합니다.

셋째줄부터 n+2번째 줄까지는 도로의 상태가 주어집니다. 도로는 0, 인도는 1으로 주어집니다.

3 ≤ n, m ≤ 50

자율주행 자동차가 있는 칸은 도로일 것이라 가정해도 좋습니다.

격자의 첫번째 행과 마지막 행, 첫번째 열과 마지막 열은 항상 인도일 것이라고 가정해도 좋습니다.


현재 방향을 기준으로 왼쪽 방향으로 한 번도 간 적이 없다면 좌회전해서 해당 방향으로 1 칸 전진합니다.
만약 왼쪽 방향이 인도거나 이미 방문한 도로인 경우 좌회전하고 다시 1번 과정을 시도합니다.
2번에 대해 4방향 모두 확인하였으나 전진하지 못한 경우에는 바라보는 방향을 유지한 채로 한 칸 후진을 하고 다시 1번 과정을 시도합니다.
 3번 과정을 시도하려 했지만 뒷 공간이 인도여서 후진조차 하지 못한다면 작동을 멈춥니다.


*/

int n, m,car_x,car_y,d;
int road[MAX_L][MAX_L];
bool visited[MAX_L][MAX_L];
int ans = 0;
bool in_range(int x, int y)
{
  return 0 <= x && x < m && 0 <= y && y < n;
}

void cal_ans(){
  for (int y = 0; y < n; y++)
  {
    for (int x = 0; x < m;x++){
      if(visited[y][x]){
        ans++;
      }
    }
  }
  sb << ans << NL;
}
//북쪽, 동쪽, 남쪽, 서쪽
void moving(){
  bool flag = false;
  int dx[4] = {0, 1, 0, -1};
  int dy[4] = {-1,0, 1,0};
  int dmove[16] = {3, 2, 1, 0, 0, 3, 2, 1, 1, 0, 3, 2, 2, 1, 0, 3};
  while (true)
  {
    int dir,cy,cx;
    int cur_move =4 *d;
    while (true)
    {
      if(cur_move == 4*(d+1)){
        flag = true;
        break;
      }      
      dir = dmove[cur_move];
      cx = car_x + dx[dir];
      cy = car_y + dy[dir];
      if(in_range(cx,cy) && !visited[cy][cx] && road[cy][cx]==0){
        car_x = cx;
        car_y = cy;
        visited[cy][cx] = true;
        d = dir;
        break;
      }
      else
      {
        cur_move += 1;
      }
    }
    if(flag){
      dir = dmove[d * 4 + 1];
      cx = car_x + dx[dir];
      cy = car_y + dy[dir];
      if(road[cy][cx] == 0){
        car_x = cx;
        car_y = cy;
        flag = false;
      }else{
        break;
      }
    }
  }

}
void input()
{

  memset(visited, false, sizeof(visited));
  cin >> n >> m;
  cin >> car_y >> car_x >> d;
  for (int y = 0; y < n;y++){
    for (int x = 0; x < m;x++){
      cin >> road[y][x];
    }
  }
  visited[car_y][car_x] = true;
}

void run(){
  input();
  moving();
  cal_ans();
  cout << sb.str();
}
int main()
{

  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  run();
  return 0;
}