#include <iostream>
#include <string>
using namespace std;


int main() {
	int tc;
	cin >> tc;
	int *arr = new int[tc];
	for (int i = 0; i < tc; i++) {
		string cur;
		cin >> cur;
		int ans = 0;
		int save = 0;
		for (int j = 0; j < cur.length(); j++) {
			if (cur[j] == 'O') {
				save++;
				ans += save;
			}
			else {
				save = 0;
			}
		}
		arr[i] = ans;
	}
	for (int i = 0; i < tc; i++) {
		cout << arr[i]<<endl;
	}

}