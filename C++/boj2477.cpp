#include <iostream>
#include <vector>
using namespace std;

int main()
{

  int N; // 밭 1m 당 참외 갯수
  vector<pair<int, int>> data;
  // 육각형 임의의 한꼭짓점에서 반시계방향
  cin >> N;
  int mx = 0;
  int my = 0;
  int x_idx, y_idx = 0;
  for (int i = 0; i < 6; i++)
  {
    int dir, dt;
    cin >> dir >> dt;
    data.push_back(make_pair(dir, dt));
    if (dir == 2 || dir == 1)
    {
      if (dt > mx)
      {
        mx = dt;
      }
    }
    else
    {
      if (dt > my)
      {
        my = dt;
      }
    }
  }
  int mul = 1;
  for (int i = 0; i < 6; i++)
  {
    int n = (i + 1) % 6;
    int nn = (i + 2) % 6;
    if (data[i].first == data[nn].first)
    {
      mul = mul * data[n].second;
    }
  }
  int ans = ((mx * my) - mul) * N;
  cout << ans;
}