#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;
int main(){
	int n,k;
	string number;
	vector <char> divide;
	cin>>n>>k>>number;
	for(int i=0;i<number.length();i++){
		while(k>0 && !divide.empty() && (divide.back()<number[i])){
			divide.pop_back();
			k--;
		}
		divide.push_back(number[i]);
	}
	for(int i=0;i<divide.size()-k;i++){
		cout <<divide[i];
	}

}