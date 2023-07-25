#include <iostream>

using namespace std;


int main() {

	int N; // 색종이수

	cin >> N;
	int x, y;
	int ans = 0;
	int mp[101][101];
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			mp[i][j] = 0;
		}
	}
	for (int i = 0; i < N; i++) {
		cin >> x >> y; //x 는 왼쪽변과 도화지의 왼쪽변 사이의 거리 // y는  아래쪽 변과 아리쪽 변사이의 거리
		for (int a = x; a < (x + 10); a++) {
			for (int b = y; b < (y + 10); b++) {
				if (mp[a][b] == 0) {
					mp[a][b] = 1;
					ans += 1;
				}
			}
		}
	}
	cout << ans;
}