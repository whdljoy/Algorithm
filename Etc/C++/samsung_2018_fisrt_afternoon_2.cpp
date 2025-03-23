#include <iostream>
#include <sstream>
#include <algorithm>
#include <vector>
#include <math.h>
using namespace std;

#define NL "\n"

int N; // 격자 크기
int m; // 구해야 될 사람수
int **origin;  //처음 정보
vector<pair<int, int>> hospital;
vector<pair<int, int>> people;
bool *checked;

int ans=1e9;
stringstream sb;

void cal_distance(){
  int cur_ans = 0;
  for (int i = 0; i < people.size(); i++)
  {
    int px = people[i].second;
    int py = people[i].first;
    int cur_distance = 1e9;
    for (int i = 0; i < hospital.size(); i++)
    {
      if(checked[i]){
        int hx = hospital[i].second;
        int hy = hospital[i].first;
        cur_distance = min(cur_distance, abs(hx - px) + abs(py - hy));
      }
    }
    cur_ans += cur_distance;
  }
  ans = min(cur_ans, ans);
}

void dfs(int num, int idx){
  if(num == m){
    cal_distance();
    return;
  }
  for (int i = idx; i < hospital.size();i++){
    if(!checked[i]){
      checked[i] = true;
      dfs(num + 1,i+1);
      checked[i] = false;
    }
  }
}


void input(){
  cin >> N >> m;
  origin = new int *[N];
  for (int i = 0; i < N;i++){
    origin[i] = new int[N];
  }
  for (int y = 0; y < N;y++){
    for (int x = 0; x < N; x++){
      cin >> origin[y][x];
      if(origin[y][x]==1){
        people.push_back({y, x});
      }else if(origin[y][x]==2){
        hospital.push_back({y, x});
      }
    }
  }
  checked = new bool[hospital.size()];
  fill(checked, checked + hospital.size(), false);
}

void run(){
  input();
  dfs(0, 0);
  sb << ans <<NL;
  cout << sb.str();
}

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  run();
}