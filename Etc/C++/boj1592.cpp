#include <iostream>
#include <cstring>
using namespace std;

int main() {


	int N, M, L;
	cin >> N >> M >> L;
	int *stu = new int[N+1];
	memset(stu, 0, sizeof(int) *(N+1));
	int start = 1;
	int ans = 0;
	while (1) {
		stu[start] += 1;
		if (stu[start] == M) {
			break;
		}
		// 짝수 반시계방향으로 L번째
		if (stu[start] % 2 == 0) {
			start = start - L;
			if (start < 1) {
				start = start + N;
			}
		}
		//홀수 시계 방향으로 L번째
		else {
			start = start + L;
			if (start> N) {
				start = start - N;
			}
		}
		ans += 1;
	}
	cout << ans;
}