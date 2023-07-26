#include <iostream>
#include <algorithm>
using namespace std;
int num;
bool in_range(int cy, int cx) {
	return 0 <= cy && cy < num && 0 <= cx && cx < num;
}

int dx[8] = { -1,0,1,0,-1,-1, 1, 1 };
int dy[8] = { 0,-1,0,1,-1, 1,-1, 1 };

int main() {


	int tc;
	cin >> tc;
	for (int i = 1; i <= tc; i++) {
		cin >> num;
		int answer = 0;
		char **vile = new char *[num];
		for (int j = 0; j < num; j++) {
			vile[j] = new char[num];
		}
		for (int y = 0; y < num; y++) {
			for (int x = 0; x < num; x++) {
				cin >> vile[y][x];
			}
		}
		for (int y = 0; y < num; y++) {
			for (int x = 0; x < num; x++) {
				int check = 0;
				if (vile[y][x] == 'W') {
					for (int dir = 0; dir < 8; dir++) {
						int cy = y + dy[dir];
						int cx = x + dx[dir];
						if (in_range(cy, cx) && vile[cy][cx] == 'W') {
							check++;
						}
					}
					if (check == 0) {
						check = 1;
					}
					answer = max(answer, check);
				}
			}

		}


		cout << "#" << i << " " << answer << "\n";
	}
}