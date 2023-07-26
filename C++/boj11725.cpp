#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;
#define MAX 100001


//  endl 보다 "\n" 을 지향하다 endl때문에 시간초과
vector <int>info[MAX];
int ans[MAX];
bool check[MAX];
void bfs(int start) {
	deque <int> q;
	check[start] = true;
	q.push_front(start);
	while (!q.empty()) {
		int cur = q.front();
		q.pop_front();
		for (auto iter : info[cur]) {
			int i_cur = iter;
			if (!check[i_cur]) {
				ans[i_cur] = cur;
				q.push_back(i_cur);
				check[i_cur] = true;
			}
		}
	}
}

int main() {

	int N;
	cin >> N;
	for (int i = 1; i < N; i++) {
		int st, end;
		cin >> st >> end;
		info[st].push_back(end);
		info[end].push_back(st);
	}
	bfs(1);
	for (int i = 2; i <= N; i++) {
		cout << ans[i] <<"\n";
	}
}