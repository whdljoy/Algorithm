#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

void moveHanoi(int num,int from,int to,int by){
    if(num == 1) {
        printf("%d %d\n",from,to);
    } else {
        moveHanoi(num-1, from,by,to);
        printf("%d %d\n",from,to);
        moveHanoi(num-1, by, to,from);
    }	
}

int main(){
	int n;
    scanf("%d",&n);
    cout<<(int)pow(2,n)-1<<endl;
    moveHanoi(n,1,3,2);

}