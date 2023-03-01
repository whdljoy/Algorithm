
#include<iostream>
#include <algorithm>
#include <vector>
using namespace std;
bool compare(pair<int, double>a, pair<int, double>b) {
	if (a.second == b.second) {
		return a.first > b.first;
	}
	else {
		return a.second > b.second;
	}
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	
	//freopen("sample_input.txt", "r", stdin);
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    int n, k,ci=0;
    int vi = 0;
    cin >> n >> k;
    vector<pair<int, int>> save;
    for (int i = 0; i < n; i++)
    {
      int v, c;
      cin >> v >> c;
      if (vi + v <= k)
      {
        save.push_back(make_pair(v, c));
        ci = ci + c;
        vi = vi + v;
        sort(save.begin(), save.end(),compare);
      }
      else
      {
        int sl = save.size();
        int tmpv, tmpc;
        for (int a = sl - 1; a > -1; a--)
        {
          tmpv = vi -save[a].first;
          tmpc = ci - save[a].second;
          // cout << "tmpv " << save[a].first << "tempc " << save[a].second<< endl;
          // cout << "tmpv " << tmpv << "tempc " << tmpc<< endl;
          // cout << "vi" << vi << "ci" << ci << endl;          
          if ((tmpv + v) <= k && (tmpc + c) > ci)
          {
            save[a] = make_pair(v, c);
            ci = tmpc + c;
            vi = tmpv + v;
            // sort(save.begin(), save.end(),compare);
            break;
          }
        }
      } 
    }
      for (int j = 0; j < save.size();j++)
      {
        cout << save[j].first << " " << save[j].second << endl;
      }
      cout << "#" << test_case << " " << ci << endl;
  }
  return 0;//정상종료시 반드시 0을 리턴해야합니다.
}