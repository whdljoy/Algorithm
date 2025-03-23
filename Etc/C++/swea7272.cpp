#include <iostream>
#include <string>
using namespace std;


int main() {

	int tc;
	cin >> tc;
	string one = "ADOPQR";
	string two = "B";
	string zero = "CEFGHIJKLMNSTUVWXYZ";
	for (int i = 1; i <= tc; i++) {
		string answer = "SAME";
		string s1, s2, check1, check2;
		cin >> s1 >> s2;
		if (s1.length() == s2.length()) {
			for (int dis = 0; dis < s1.length(); dis++) {
				for (int a = 0; a < zero.length(); a++) {
					if (zero[a] == s1[dis]) {
						check1 = "zero";
					}
					if (zero[a] == s2[dis]) {
						check2 = "zero";
					}
				}
				for (int b = 0; b < one.length(); b++) {
					if (one[b] == s1[dis]) {
						check1 = "one";
					}
					if (one[b] == s2[dis]) {
						check2 = "one";
					}
				}
				for (int c = 0; c < two.length(); c++) {
					if (two[c] == s1[dis]) {
						check1 = "two";
					}
					if (two[c] == s2[dis]) {
						check2 = "two";
					}
				}
				if (check1 != check2) {
					answer = "DIFF";
					break;
				}
			}
		}
		else {
			answer = "DIFF";
		}
		cout << "#" << i << " " << answer << "\n";
	}
}