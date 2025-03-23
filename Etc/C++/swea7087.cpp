#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <set>
using namespace std;

int main() {


	int tc;
	cin >> tc;
	for (int i = 1; i <= tc; i++) {
		char S = 65;
		int ans = 0;
		int N;
		cin >> N;
		set <char> check;
		for (int a = 0; a < N; a++) {
			string s;
			cin >> s;
			check.insert(s[0]);
		}
		for (auto iter : check) {
			if (iter == S) {
				S++;
				ans++;
			}
			else {
				break;
			}
		}

		cout << "#" << i << " " << ans << "\n";
	}
}