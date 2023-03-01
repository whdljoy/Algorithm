#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;


int main(){
	char info[20][20];
  bool visit[20][20];
  int tc;
  stack<string> street;
	stack<pair<int,int> > dot;
	vector <char> answer;
	scanf("%d",&tc);
	for (int i=0; i<tc ;i ++){
		int row,col,mx=0;
		scanf("%d %d",&col,&row);
		for (int c=0; c<col; c++){
			cin >> info[c];
		} 
    dot.push(make_pair(0,0));
    static int di[] = {-1, 0, 1, 0};
    static int dj[] = {0, 1, 0 - 1};
    answer.push_back(info[0][0]);
    visit[0][0] = true;
    while (!dot.empty())
    {
      pair<int, int> cur = dot.top();
      for (int i = 0; i < 4; i++){
        int x = cur.second + di[i];
        int y = cur.first + dj[i];
        if ((x >= 0) && (y >= 0) && (x < col) && (x < row))
        {
          if(!visit[y][x]){
            auto it = find(answer.begin(), answer.end(), info[y][x]);
            if(it == answer.end()){
              dot.push(make_pair(x, y));
              cout << info[x][y];
              continue;
            }
          }
        }
      }

      if(mx < answer.size()){
        mx = answer.size();
      }
      dot.pop();
      answer.pop_back();
    }
    cout << mx;
    // for(auto loop : answer)
    // {
    //     cout << loop << endl;
    // }
  }
}
