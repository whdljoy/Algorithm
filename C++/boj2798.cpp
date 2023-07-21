#include <iostream>
#include <vector>
using namespace std;
int N,M;
int *mp;
int answer;
void checkMax(vector<int> v)
{
	int cursum = 0;
	for (auto &el : v) {
		cursum += el;
	}
	if (cursum <= M && answer < cursum) {
		answer = cursum;
	}
}
void combi(int start, vector<int> v)
{
    if(v.size() == 3)
    {
		checkMax(v);
        return;
    }
    for(int i=start+1; i < N; i++)
    {
        v.push_back(mp[i]);
        combi(i, v);
        v.pop_back();
    }
    return;
}

int main(){
    cin >> N >> M;
    vector <int> ans;
	mp = new int[N];
    for(int i=0; i<N; i++ ){
        cin >>mp[i];
    }
    combi(-1,ans);

	cout << answer;

}