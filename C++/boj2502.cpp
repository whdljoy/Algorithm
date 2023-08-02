#include <iostream>
using namespace std;


int main(){
    int day,value;
    int check[31]={0,};
    int num1=1,num2=1;
    bool checking=false;
    scanf("%d %d",&day,&value);
    for(int i=1;i<value;i++){
        for(int j=1;j<value;j++){
            check[1]=i;
            check[2]=j;
            for(int a=3;a<day+1;a++){
                check[a]=check[a-1]+check[a-2];
            }
            if(check[day]==value){
                checking=true;
                num1=i;
                num2=j;
                break;
            }
        }
        if(checking==true){
            break;
        }
    }
    printf("%d \n%d",num1,num2);
    return 0;
}