#include <iostream>
#include <vector>
using namespace std;


int main(){
    int num1,num2,num3,num4,tmp;
    int answer1=0,answer2=0,remainder,remainder2;
    scanf("%d",&num1);
    scanf("%d",&num2);
    scanf("%d",&num3);
    scanf("%d",&num4);
    answer1 =num1 * num4 + num3 *num2 ;
    answer2 =num2 * num4;
    if( answer1 >answer2){
        remainder= answer2;
        remainder2= answer1;
    }
    else{
        remainder= answer1;
        remainder2= answer2;
    }
    while(remainder2 !=1){
        if(remainder2 % remainder == 0 ){
            answer1 = answer1 / remainder;
            answer2= answer2 / remainder;
            break;
        }
        else{
            tmp=remainder2;
            remainder2= remainder;
            remainder=tmp %remainder;
        }       
    }

    printf("%d %d",answer1,answer2);

}