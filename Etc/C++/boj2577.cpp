#include < iostream>
#include <algorithm>
using namespace std;

int main()
{
  int dx[4] = {-1, 0, 1, 0};
  int dy[4] = {0, -1, 0, 1};
  int cy[4] = {-1, -1, 1, 1};
  int cx[4] = {-1, 1, -1, 1};
  for (int i = 0; i < 4; i++)
  {
    int *first = new int[8];
    for (int j = 0; j < 8; j++)
    {
      cin >> first[j];
    }
    int mx = max(first[2], first[6]);
    int my = max(first[3], first[7]);
    my += 10;
    mx += 10;
    int **second = new int *[my];
    int sm = 0;  // 겹치는 부분 직사각형
    int dir = 0; // 4각으로 선분;
    int cross = 0;
    for (int y = 0; y < (my); y++)
    {
      second[y] = new int[mx];
    }
    for (int sy = first[1]; sy < first[3]; sy++)
    {
      for (int sx = first[0]; sx < first[2]; sx++)
      {
        second[sy][sx] = 1;
      }
    }
    for (int sy = first[5]; sy < first[7]; sy++)
    {
      for (int sx = first[4]; sx < first[6]; sx++)
      {
        if (second[sy][sx] == 1)
        {
          sm++;
        }
        for (int dire = 0; dire < 4; dire++)
        {
          int cur_x = sx + dx[dire];
          int cur_y = sy + dy[dire];
          if (0 <= cur_x && cur_x < mx && 0 <= cur_y && cur_y < my && second[cur_y][cur_x] == 1)
          {
            dir++;
          }
        }
      }
    }

    if (sm > 0)
    {
      cout << "a";
    }
    else if (dir > 0)
    {
      cout << "b";
    }
    else
    {
      cout << "d";
    }
  }
}