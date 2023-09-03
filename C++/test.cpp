

#include <iostream>
#include <algorithm>
using namespace std;

bool **origin;

int main(){
  int n = 4;
  int m = 5;
  origin = new bool *[5];
  for (int i = 0; i < 5;i++){
    origin[i] = new bool[6];
  }
  // fill(&origin[0][0], &origin[n-1][m], 12);
  for (int y = 0; y < 5;y++){
    for (int x = 0; x < 6;x++){
      cout << origin[y][x] << " ";
    }
    cout << "\n";
  }
}