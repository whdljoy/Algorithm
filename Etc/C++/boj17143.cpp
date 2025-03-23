#include <iostream>
using namespace std;
int R, C, M;
// R  -- Y 행
// C -- X 열
int cur_x = 0;
int cur_y = 0; // 현재 낚시왕의 위치;
int ans = 0;

struct shark
{
  bool in = false;
  int s = 0; // 속력
  int d = 0; // 이동방향
  int z = 0; // 크기
};
shark **info;
// r 행 c 열   y,x
// 크기 속도 한칸당 최대 1마리
// 낚이왕은 1번열 한칸 왼쪽 가장 오른쪽 열 오른쪽 칸에 이동하면 멈춤

// 낚시왕이 잡은 상어 크기의 합을 구해보자.

// r c M  크기 , 상어의 수 M
//   r c s d z   r,c 위치 s 속력 d 이동방향 z 크기

//
// 1 낚시왕이 오른쪾으로 한칸
void move()
{
  cur_x += 1;
}

bool in_range(int x, int y)
{
  return (1 <= x && x <= C && 1 <= y && y <= R);
}

// 2 낚시왕이 있는 열에 있는 상어중에서 땅과 제일 가까운 상어를 잡는다
// 격자판 에서 사라짐
void kill()
{

  for (int i = 1; i < R + 1; i++)
  {
    int cy = cur_y + i;
    if (in_range(cur_x, cy) && info[cy][cur_x].in)
    {
      ans += info[cy][cur_x].z;
      info[cy][cur_x] = {false, 0, 0, 0};
      return;
    }
  }
}

// 3 상어가 이동한다.
// d가 1 위 2아래 3 오른쪾 4 왼쪽
// 상어는 입력으로 주어진 속도오 이동 칸/ 초
// 격자판의 경계를 넘는 경우 방향을 바꿔서 속력 유치한채
// 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다.
// 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다

pair<int, int> change_y(int mod, int y, int dir)
{
  int cy = y;
  if (dir == 1)
  {
    if (cy - mod <= 1)
    { // 방향변화
      dir = 2;
      cy = abs(cy - mod) + 1;
    }
    else
    {
      cy -= mod;
    }
  }
  else
  {
    if (cy + mod >= R)
    {
      dir = 1;
      cy = R + (R - (cy + mod));
    }
    else
    {
      cy += mod;
    }
  }
  return make_pair(cy, dir);
}

pair<int, int> move_y(shark c_shark, pair<int, int> where)
{
  int x = where.first;
  int y = where.second;
  // r-1 로 계산
  int mod = c_shark.s % (R - 1); // 마지막 쪽에서 이동거리
  int div = c_shark.s / (R - 1); // 방향 결정
  if (div % 2 == 0)
  { // 방향 그대로 // 원래 위치에서 이동
    return change_y(mod, y, c_shark.d);
  }
  else
  { // 방향 반대로 // 양끝에서 이동
    if (c_shark.d == 1)
    {
      return change_y(mod, y, c_shark.d);
    }
    else
    {
      c_shark.d = 1; // R 부터 시작
      return change_y(mod, y, c_shark.d);
    }
  }
}

pair<int, int> change_x(int mod, int x, int dir)
{
  int cx = x;
  if (dir == 4)
  {
    if (cx - mod <= 1)
    { // 방향변화
      dir = 3;
      cx = abs(cx - mod) + 1;
    }
    else
    {
      cx -= mod;
    }
  }
  else
  {
    if (cx + mod >= R)
    {
      dir = 1;
      cx = C + (C - (cx + mod));
    }
    else
    {
      cx += mod;
    }
  }
  return make_pair(cx, dir);
}
pair<int, int> move_x(shark c_shark, pair<int, int> where)
{
  int x = where.first;
  int y = where.second;
  // r-1 로 계산
  int mod = c_shark.s % (C - 1); // 마지막 쪽에서 이동거리
  int div = c_shark.s / (C - 1); // 방향 결정
  if (div % 2 == 0)
  { // 방향 그대로 // 원래 위치에서 이동
    return change_x(mod, x, c_shark.d);
  }
  else
  { // 방향 반대로 // 양끝에서 이동
    if (c_shark.d == 3)
    {
      c_shark.d = 4;
      return change_x(mod, x, c_shark.d);
    }
    else
    {
      c_shark.d = 3; // C 부터 시작
      return change_x(mod, x, c_shark.d);
    }
  }
}
void shark_move()
{
  shark **tmp;
  tmp = new shark *[R + 1];
  for (int i = 0; i < R + 1; i++)
  {
    tmp[i] = new shark[C + 1];
  } // 낚시왕 0,0 c출발
  for (int i = 0; i < R + 1; i++)
  {
    for (int j = 0; j < C + 1; j++)
    {
      tmp[i][j] = {false, 0, 0, 0};
    }
  }
  for (int y = 1; y < R + 1; y++)
  {
    for (int x = 1; x < C + 1; x++)
    {
      if (info[y][x].in) // 상어가 있을때 위치계산
      {
        if (info[y][x].d == 1 || info[y][x].d == 2)
        {
          pair<int, int> change = move_y(info[y][x], make_pair(x, y));
          int cy = change.first;
          int dd = change.second;
          tmp[cy][x] = {true, info[y][x].s, dd, info[y][x].z};
        }
        else
        {
          pair<int, int> change = move_x(info[y][x], make_pair(x, y));
          int cx = change.first;
          int dd = change.second;
          tmp[y][cx] = {true, info[y][x].s, dd, info[y][x].z};
        }
      }
    }
  }
  check(tmp);
}

void check(shark **tmp)
{
}
//  상어를 잡으면 겪자판에서 잡은 상어가 사라진다.
int main()
{
  cin >> R >> C >> M;
  info = new shark *[R + 1];
  for (int i = 0; i < R + 1; i++)
  {
    info[i] = new shark[C + 1];
  } // 낚시왕 0,0 c출발
  for (int i = 0; i < R + 1; i++)
  {
    for (int j = 0; j < C + 1; j++)
    {
      info[i][j] = {false, 0, 0, 0};
    }
  }
  for (int i = 0; i < M; i++)
  {
    int r, c, s, d, z;
    cin >> r >> c >> s >> d >> z;
    info[r][c].s = s;
    info[r][c].d = d;
    info[r][c].z = z;
    info[r][c].in = true;
  }
  while (1)
  {
    if (cur_x == C + 1)
    {
      break;
    }
    move();
    kill();
  }
}