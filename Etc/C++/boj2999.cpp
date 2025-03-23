#include <iostream>
#include <string>
using namespace std;

int main() {
	string st;
	cin >> st;
	int N = st.length();
	int R;
	int c_num;
	for (int i = 1; i <= N; i++) {
		int val = N % i;
			if (val == 0) {
				int cur = N / i;
				if (cur >= i) {
					R = i;
					c_num = cur;
				}
			}
	}
	char **info = new char*[R];
	for (int i = 0; i < R; i++) {
		info[i] = new char[c_num];
	}
	int idx = 0;
	for (int i = 0; i < c_num; i++) {
		for (int j = 0; j < R; j++) { 
			info[j][i] = st[idx++];
		}
	}
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < c_num; j++) {
			cout << info[i][j];
		}
	}

}