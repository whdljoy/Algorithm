#include <iostream>
//최대 공약수 유클리드 호제법
// 시간 초과 
using namespace std;
int gcd(int a, int b) 
{ 
	int mod = a % b;
    
	while (mod > 0)
	{
		a = b;
		b = mod;
		mod = a % b;
	}
	
    return b;
}
int main(){
    int first,second,answer1=0,answer2=0,idx2=1;
    cin >> first >>second;;   
    answer1=gcd(first,second);
    int num1=first/answer1;
    int num2=second/answer1;
    answer2= answer1*num1*num2;
    cout << answer1 <<endl<< answer2;
}
