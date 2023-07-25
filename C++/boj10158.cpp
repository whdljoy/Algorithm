#include <iostream>
#include <cmath>
using namespace std;



int main() {

	int w, h, p, q;  // 격자공간
	int ans_x = 0, ans_y = 0;
	cin >> w >> h;
	cin >> p >> q; // 개미 초기 위치
	int t;
	cin >> t;
	//      t%12  <=(w-p) 그냥 이동
	int ww = (p + t) %(2 * w);
	int hh = (q + t) % (2 * h);

	ans_x = w - abs(w - ww);
	ans_y = h - abs(h - hh);

	cout << ans_x << " " << ans_y;


}