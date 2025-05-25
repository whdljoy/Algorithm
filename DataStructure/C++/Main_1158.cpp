#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {

	int N, K;
	cin >> N >> K;
	vector <int> ans;
	vector <int> person;
	for (int i = 1; i <= N; i++) {
		person.push_back(i);
	}
	auto it = person.begin();
	while (1) {
		if (person.empty()) {
			break;
		}
		for (int j = 1; j < K; j++) {
			it++;
			if (it == person.end()) {
				it = person.begin();
			}
		}
		ans.push_back(*(it));
		it=person.erase(it);
		if (it == person.end()) {
			it = person.begin();
		}
	}
	cout << "<";
	for (int i = 0; i < N; i++) {
		cout << ans[i];
		if (i != (N - 1)) {
			cout << ", ";
		}
	}
	cout << ">";
}