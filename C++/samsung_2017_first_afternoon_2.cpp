#include <iostream>
#include <sstream>
#include <algorithm>
#include <climits>
#include <string.h>
#include <queue>
using namespace std;

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"

#define MAXARR 9

stringstream sb;
int N, M;
int origin[MAXARR][MAXARR];
vector<pair<int, int>> wall;
bool *checked;

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0,-1,0,1};
int ans = INT_MIN;
bool in_range(int y, int x){
  return 0 <= y && y < N && 0 <= x && x < M;
}

void cal_fire(){
  int cp_origin[MAXARR][MAXARR];
  memset(cp_origin, 0, sizeof(cp_origin));
  deque<pair<int, int>> fire;
  for (int y = 0; y < N;y++){
    for (int x = 0; x < M; x++){
      cp_origin[y][x] = origin[y][x];
      if (origin[y][x] == 2){
        fire.push_back({y, x});
      }
    }
  }
  for (int i = 0; i < wall.size(); i++){
    if(checked[i]){
      cp_origin[wall[i].first][wall[i].second] = 1;
    }
  }
  while(!fire.empty()){
    pair<int, int> points = fire.front();
    fire.pop_front();
    for (int dir = 0; dir < 4; dir++){
      int cy = points.first + dy[dir];
      int cx = points.second + dx[dir];
      if(in_range(cy,cx) && cp_origin[cy][cx] == 0){
        cp_origin[cy][cx] = 2;
        fire.push_back({cy, cx});
      }
    }
  }
  int cur = 0;
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < M; x++){
      if(cp_origin[y][x]== 0){
        cur += 1;
      }
    }
  }
  ans = max(ans, cur);
}
void dfs(int num, int idx)
{
  if(num ==3){
    cal_fire();
    return;
  }
  for (int i = idx; i < wall.size(); i++){
    if(!checked[i]){
      checked[i] = true;
      dfs(num + 1, i + 1);
      checked[i] = false;
    }
  }
}
void input()
{
  cin >> N >> M;
  memset(origin, 0, sizeof(origin));
  for (int y = 0; y < N; y++)
  {
    for (int x = 0; x < M;x++){
      cin >> origin[y][x];// 불이 있는 경우 2  방화벽 1 빈칸 0
      if(origin[y][x] == 0){
        wall.push_back({y, x});
      }
    }
  }
  checked = new bool[wall.size()];
  memset(checked, false, sizeof(bool) *wall.size());  //동적할당일 때는 memset(checked,false,sizeof(checked)) 안됨
}
void run(){
  input();
  dfs(0, 0);
  if(ans == INT_MIN){
    ans = 0;
  }
  sb << ans << NL;
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();
   cout << sb.str();

   return 0;
}