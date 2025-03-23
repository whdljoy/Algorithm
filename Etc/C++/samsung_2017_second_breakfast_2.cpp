#include <iostream>
#include <sstream>
#include <algorithm>
using namespace std;


#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",stdout)
#define NL "\n"
#define MAX_N 100
stringstream sb;

int N, L;
int ans = 0;
int road[MAX_N][MAX_N];

void cal_row(int idx){
  for (int i = 0; i < N; i=i+L){   //시작부터
    int start = road[idx][i];
    int last = road[idx][i + L - 1];
  }
}
void cal_col(int idx){

}

void simulate(){
  for (int i = 0; i < N;i++){
    cal_row (i);
    cal_col(i);
  }
}


void input(){
  cin >> N >> L;
  for (int y = 0; y < N;y++){
    for (int x = 0; x < N;x++){
      cin >> road[y][x];
    }
  }
}

void run(){
  input();

}

int main(){

   ios_base::sync_with_stdio(false);
   cin.tie(NULL);


    return 0;
}