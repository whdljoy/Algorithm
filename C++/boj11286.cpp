#include <iostream>
#include <math.h>
#include <vector>
using namespace std;

int absheap(vector <int> &heap){
    int index=0,min=abs(heap[0]);
    for(int i=1;i<heap.size();i++){
        if(min>abs(heap[i])){
            index=i;
            min=abs(heap[i]);
        }
        else if(min==abs(heap[i])){
            if(heap[index]>heap[i]){
                index=i;
                min=abs(heap[i]);
            }
        }
    }
    min=heap[index];
    heap.erase(heap.begin()+index);
    return min;
}

int main(){
    int n;
    vector <int> heap;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        int num;
        scanf("%d",&num);
        if(num!=0){
            heap.push_back(num);
        }
        else{
            if(heap.empty()){
                printf("0\n");
            }
            else{
                printf("%d\n",absheap(heap));  
            }
        }
    }

}