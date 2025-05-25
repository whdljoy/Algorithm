#include <iostream>
#include <vector>
using namespace std;
int sw_n;

int * mp;
void change(int i) {
	if (mp[i] == 1) {
		mp[i] = 0;
	}
	else {
		mp[i] = 1;
	}
}
void boys(int position) {
	for (int i = position; i <= sw_n; i = i + position) {
		change(i);
	}
}



void girls(int position) {
	change(position);
	int start = 1;
	while (1) {
		int mx = position - start;
		int px = position + start;
		if (1 <= mx && px <= sw_n) {
			if (mp[mx] == mp[px]) {
				change(mx);
				change(px);
				start += 1;
			}
			else {
				break;
			}
		}
		else {
			break;
		}
	}
}
int main() {

	int info;
	cin >> sw_n;
	mp = new int[sw_n + 1];
	for (int i = 1; i <= sw_n; i++) {
		int cur;
		cin >> cur;
		mp[i] = cur;
	}
	cin >> info;
	for (int j = 0; j < info; j++) {
		int sx, position;
		cin >> sx >> position;
		if (sx == 1) {
			boys(position);
		}
		else {
			girls(position);
		}
	}
	for (int i = 1; i <= sw_n; i++) {
		cout << mp[i] << " ";
		if (i % 20 == 0) {
			cout << endl;
		}
	}



}