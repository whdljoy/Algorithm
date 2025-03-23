#include <iostream>
#include <string>
using namespace std;
int N;

bool in_range(int cx, int cy) {
	if (0 <= cx && cx < N && 0 <= cy && cy < N) {
		return true;
	}
	return false;
}

int dx[8] = { -1,1,0,0,-1,-1, 1, 1 };
int dy[8] = { 0,0,-1,1,-1, 1,-1, 1 };
int main() {

	int tc;
	cin >> tc;
	for (int i = 1; i <= tc; i++) {
		cin >> N;
		string ans = "NO";
		char ** info = new char *[N];
		for (int j = 0; j < N; j++) {
			info[j] = new char[N];
		}
		for (int y = 0; y < N; y++) {
			string s;
			cin >> s;
			for (int x = 0; x < N; x++) {
				info[y][x] = s[x];
			}
		}
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				char before = info[y][x];
				if (ans == "NO" && before =='o') {
					for (int dir = 0; dir < 8; dir++) {
						int check = 1;
						for (int dis = 1; dis < 5; dis++) {
							int cx = x + dis * dx[dir];
							int cy = y + dis * dy[dir];
							if (in_range(cy, cy) && info[cy][cx] == before) {
								check++;
                                
							}
							else {
								break;
							}
						}
						if (check == 5) {
							ans = "YES";
						}
					}
				}
			}
		}
		cout << "#" << i << " " << ans << "\n";
	}
}

