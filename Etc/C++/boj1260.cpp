#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;

vector <vector <int>>info;

bool *check;
void bfs(int start) {
	deque <int> q;
	check[start] = true;
	q.push_front(start);
	while (!q.empty()) {
		int cur = q.front();
		cout << cur << " ";
		q.pop_front();
		for (auto iter : info[cur]) {
			int i_cur = iter;
			if (check[i_cur] == false) {
				q.push_back(i_cur);
				check[i_cur] = true;
			}
		}
	}
}


void dfs(int cur) {
	if (check[cur] == true) {
		return;
	}
	else {
		check[cur] = true;
		cout << cur << " ";
		for (auto iter : info[cur]) {
			dfs(iter);
		}
	}


}
int main() {

	int N, M, V;
	cin >> N >> M >> V;
	for (int i = 0; i <= N; i++) {
		vector <int> tmp;
		info.push_back(tmp);
	}
	for (int i = 0; i < M; i++) {
		int st, end;
		cin >> st >> end;
		info[st].push_back(end);
		info[end].push_back(st);
	}
	for (int i = 0; i <= N; i++) {
		sort(info[i].begin(), info[i].end());
	}
	check = new bool[N + 1];
	fill(check, check + N + 1, false);
	dfs(V);
	cout << endl;
	fill(check, check + N + 1, false);
	bfs(V);
}