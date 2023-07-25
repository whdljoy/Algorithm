#include <iostream>
#include <list>
using namespace std;


int main() {

	int tc = 10;
	int N = 0;
	int nN = 0;
	for (int i = 0; i < tc; i++) {
		list <int> secret;
		cin >> N;
		for (int a = 0; a < N; a++) {
			int cur;
			cin >> cur;
			secret.push_back(cur);
		}
		cin >> nN;
		char cur_n;
        for(int b =0; b<nN; b++){
            cin >> cur_n;
            if (cur_n == 'I') {// x 번째 암호분 바로 다음에 y 개의 암호문을 삽입한다. S는 덧붙일 암호문
                int x, y;
                auto st = secret.begin();
                cin >> x >> y;
                list <int> tmp;
                for (int j = 0; j < x; j++) st++;
                for (int j = 0; j < y; j++) {
                    int cur;
                    cin >> cur;
                    tmp.push_back(cur);
                }
                secret.splice(st, tmp);
            }
            else if (cur_n == 'D') {
                int x, y; //x번째 암호분 바로 다음부터 y개의 암호문 삭제
                auto st = secret.begin();
                cin >> x >> y;
                for (int j = 0; j < x; j++) st++;

                for (int j = 0; j < y; j++) {
                    secret.e rase(st);
                }
            }
            else if (cur_n == 'A') {// y,s  맨뒤에 y개의 암호문을 덧붙인다.
                int y;
                cin >> y;
                for (int j = 0; j < y; j++) {
                    int cur;
                    cin >> cur;
                    secret.push_back(cur);
                }
            }            
        }
		cout << "#" << i+1 << " ";
        auto itr = secret.begin();
		for (int b = 0; b < 10; b++, itr++) {
			cout << *itr << " ";
		}
		cout << endl;
	}

}