#include <iostream>
using namespace std;

int main(){
    int n,answer;
    bool total[1000001]={false,};
    bool checking=false;
    for(int i=2;i<1000001;i++){
        if(total[i]==false){
            for(int j=i*2;j<1000001;j=j+i){
                total[j]=true;
            }
        }
    }
    while(1){
        scanf("%d",&n);
        if(n==0){
            break;
        }
        for(int i=3;i<n;i=i+2){
            if(total[i]==false){
                if(total[n-i]==false && (n-i) % 2==1){
                    checking=true;
                    answer=i;
                    break;
                }
            }
        }
        if(checking==false){
            printf("Goldbach's conjecture is wrong\n");
       }
       else{
        printf("%d = %d + %d\n",n,answer,n-answer);
       }
    }

}