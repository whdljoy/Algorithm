#include <iostream>
#include <algorithm>
using namespace std;


int main() {

	int tc;
	cin >> tc;
	for (int i = 1; i <= tc; i++) {
		int N, D;
		cin >> N >> D;
		int *info = new int[N + 2];
		int ans = 0;
		fill(info, info + N + 2, 0);
		info[0] = 1;
		info[N + 1] = 1;
		for (int j = 1; j <= N; j++) {
			cin >> info[j];
		}
		for (int j = 0; j <= N; j++) {
			if (info[j] == 1) {
				bool check = false;
				for (int dis = 1; dis <= D; dis++) {
					int pos = j + dis;
					if (pos <= (N + 1) && info[pos] == 1) {
						check = true;
						break;
					}
				}
				if (!check) {
					info[j + D] = 1;
					ans++;
				}
			}
		}


		cout << "#" << i << " " << ans << "\n";
	}
}

