#include<iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
bool compare(int a,int b){
    return a>b;
}
int main(){
    int n;
    priority_queue<int> max_heap; 
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        if (num==0){
            if(max_heap.empty()){
                 printf("0\n");
            }
            else{
                printf("%d\n",max_heap.top());
                max_heap.pop();
            }
        }
        else{
           max_heap.push(num);
        }
    }

}