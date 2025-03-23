#include <iostream>
#include <string>
#include<vector>
using namespace std;

int main() {
	int N;
	cin >> N;
	string seat;
	cin >> seat;
	vector <int> info;
	int ans = 0;
	info.push_back(0);
	int i = 0;
	while (!(i == N)) {
		switch (seat[i]) {
		case 'S':
			info.push_back(2);
			info.push_back(0);
			i += 1;
			break;
		case 'L':
			info.push_back(3);
			info.push_back(3);
			info.push_back(0);
			i += 2;
			break;
		}
	}


	for (int i = 0; i < info.size(); i++) {
		switch (info[i]) {
		case 2:
			if (info[i - 1] == 0) {
				info[i - 1] = 1;
				ans += 1;
			}
			else if (info[i - 1] == 1) {
				if (info[i + 1] == 0) {
					info[i + 1] = 1;
					ans += 1;
				}
			}
			break;
		case 3:
			if (info[i - 1] == 0) {
				info[i - 1] = 1;
				ans += 1;
			}
			else if (info[i - 1] == 3) {
				if (info[i + 1] == 0) {
					info[i + 1] = 1;
					ans += 1;
				}
			}
			break;
		case 1:
		case 0:
		default:
			break;
		}
	}
	cout << ans;

}