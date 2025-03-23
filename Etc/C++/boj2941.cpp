#include <iostream>
#include <string>
using namespace std;


int main() {

	string alpa[8] = { "c=","c-","dz=","d-","lj","nj","s=","z=" };
	string ip;
	cin >> ip;
	int *info = new int[ip.length()];
	for (int i = 0; i < ip.length(); i++) {
		info[i] = 0;
	}
	for (int j = 0; j < 8; j++) {
		while (1) {
			auto idx = ip.find(alpa[j]);
			if (idx == string::npos) {
				break;
			}
			ip.replace(idx, alpa[j].length(), "#");
		}
	}
	cout << ip.length();
}