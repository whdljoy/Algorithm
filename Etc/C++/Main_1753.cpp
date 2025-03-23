#include <iostream>
#include <sstream>
#include <algorithm>
#include <functional>
#include <queue>
using namespace std;

#define NL "\n"

stringstream sb;
int V;
int E;
int start;
vector<pair<int,int>> *graph;
priority_queue<pair<int, int>> pq;
int *dist;
bool *visited;
void input()
{
  cin >> V >> E;
  cin >> start;
  graph = new vector<pair<int,int>>[V+1];
  dist = new int[V + 1];
  visited = new bool[V + 1];
  fill(visited, visited + V + 1, false);
  fill(dist, dist + V + 1, 987654321);
  for (int i = 0; i < E; i++)
  {
    int s, e, w;
    cin >> s >> e >> w;
    graph[s].push_back({w, e});
  }
}

void cal(){
  pq.push({0, start});
  dist[start] = 0;
  while(!pq.empty()){
    int cost = -pq.top().first;
    int node = pq.top().second;
    pq.pop();
    if(dist[node] < cost ){
      continue;
    }
    for (int i = 0; i < graph[node].size();i++){
      int to_cost = cost + graph[node][i].first;
      if(to_cost <dist[graph[node][i].second]){
        dist[graph[node][i].second] = to_cost;
        pq.push({-to_cost, graph[node][i].second});
      }
    }
  }
}

int main(){
  input();
  cal();
  for (int i = 1; i <= V;i++){
    if(dist[i] == 987654321){
      sb << "INF" << NL;
    }else{
      sb << dist[i] << NL;
    }
  }
  cout << sb.str();
}