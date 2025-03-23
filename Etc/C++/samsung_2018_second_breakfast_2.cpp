#include <iostream>
#include <sstream>
#include <algorithm>
#include <deque>
#include <climits>
#include <string.h>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 50
stringstream sb;

bool visited[MAX_N][MAX_N];
int grid[MAX_N][MAX_N];
int L, R,N;
int ans = 0;
bool flag = false;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

bool in_range(int x,int y){
  return 0 <= x && x < N && 0 <= y && y < N;
}

void bfs(int x, int y)
{
  deque<pair<int, int>> q;
  deque<pair<int, int>> tmp;
  q.push_back({x, y});
  tmp.push_back({x, y});
  int sum = grid[y][x];
  int num = 1;
  visited[y][x] = true;
  while(!q.empty()){
    pair<int, int> cur = q.front();
    q.pop_front();
    for (int dir = 0; dir < 4;dir++){
      int cx = cur.first + dx[dir];
      int cy = cur.second + dy[dir];
      if(in_range(cx,cy) && !visited[cy][cx] && L<=abs(grid[cy][cx]-grid[cur.second][cur.first]) && abs(grid[cy][cx]-grid[cur.second][cur.first])<=R){
        visited[cy][cx] = true;
        sum += grid[cy][cx];
        num += 1;
        flag = true;
        q.push_back({cx, cy});
        tmp.push_back({cx, cy});
      }
    }
  }

  while(!tmp.empty()){
    pair<int, int> cur = tmp.front();
    tmp.pop_front();
    grid[cur.second][cur.first] = sum / num;
  }
}

void simulate(){

  while(1){
          memset(visited, false, sizeof(visited));    
    flag = false;
    for (int y = 0; y < N; y++)
    {
      for (int x = 0; x < N;x++){
        if(!visited[y][x]){
          bfs(x, y);
        }
      }
    }
    if(flag){
      ans += 1;
    }else{
      break;
    }
  }
}

void input(){

  cin >> N >> L >> R;
  for (int y = 0; y < N;y++){
    for (int x = 0; x < N;x++){
      cin >> grid[y][x];
    }
  }
}

void run(){
  input();
  simulate();
  sb << ans << NL;
}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);
   run();
   cout << sb.str();

   return 0;
}