#include <iostream>
#include <sstream>
#include <algorithm>
#include <queue>
#include<string.h>
#include <climits>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 50
stringstream sb;

class hospital{
public:
  int x;
  int y;
  int turn;
  hospital(int nx,int ny, int nturn){
    x = nx;
    y = ny;
    turn = nturn;
  }
};
int N; // 격자 크기
int M; // 병원 갯수
vector <hospital> info;
int origin[MAX_N][MAX_N];
bool *check;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};
int ans = INT_MAX;
bool in_range(int x, int y)
{
  return 0 <= x && x < N && 0 <= y && y< N;
}
void cal_hospital(){
  deque<hospital> select;
  int cur_turn = 0;
  bool visited[MAX_N][MAX_N];
  memset(visited, false, sizeof(visited));
  for (int i = 0; i < info.size(); i++)
  {
    if(check[i]){
      select.push_back(info[i]);
      visited[info[i].y][info[i].x] = true;
    }
  }
  while (!select.empty())
  {
    hospital cur = select.front();
    select.pop_front();
    if(origin[cur.y][cur.x] == 0){
      cur_turn = max(cur_turn, cur.turn);
    }
    for (int dir = 0; dir < 4; dir++)
    {
      int cx = cur.x + dx[dir];
      int cy = cur.y + dy[dir];
      if (in_range(cx, cy) && (origin[cy][cx] == 0  || origin[cy][cx]==2) && !visited[cy][cx])
      {
        visited[cy][cx] = true;
        select.push_back(hospital(cx, cy, cur.turn + 1));
      }
    }
  }
  for (int y = 0; y < N; y++){
    for (int x = 0; x < N; x++){
      if(origin[y][x] == 0 && !visited[y][x]){
        cur_turn = -1;
        break;
      }
    }
  }
  if(cur_turn != -1){
    ans = min(cur_turn, ans);
  }
}

void dfs(int num,int idx)
{
  if(num == M){
    cal_hospital();
    return;
  }
  for (int i = idx; i < info.size();i++){
    if(!check[i]){
      check[i] = true;
      dfs(num + 1, i + 1);
      check[i] = false;
    }
  }
}

void input()
{
  cin >> N >> M;
  memset(origin, 0, sizeof(origin));
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < N;x++){
      cin >> origin[y][x];
      if(origin[y][x] ==2){
        info.push_back(hospital(x, y, 0));
      }
    }
  }
  check = new bool[info.size()];
  memset(check, 0, sizeof(bool) * info.size());
}

void run(){
  input();
  dfs(0, 0);
  if(ans ==INT_MAX){
    ans = -1;
  }
  sb << ans << NL;
}
int main()
{
  fileio;
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  run();
  cout << sb.str();
  return 0;
}