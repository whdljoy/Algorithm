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
    priority_queue<int> min_heap; 
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        if (num==0){
            if(min_heap.empty()){
                 printf("0\n");
            }
            else{
                printf("%d\n",-min_heap.top());
                min_heap.pop();
            }
        }
        else{
           min_heap.push(-num);
        }
    }

}