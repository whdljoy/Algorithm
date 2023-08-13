# C++ 



- ```
  ios_base::sync_with_stdio(false); 
  ```

  **사용 이유** - 이 코드를 작성해줌으로서 동기화를 비활성화 시켜준다. 이로 인해 C++ 만의 독립적인 버퍼가 생성되어 C의 버퍼와 병행하여 사용할 수 없게 되지만, 사용하는 버퍼의 수가 줄어들었기 때문에 실행속도는 빨라지게 된다. 알고리즘 문제를 풀 때는 대부분 싱글 쓰레드 환경이기 때문에 해당 코드를 추가해줘도 문제가 발생하지 않을 확률 이높다.

​	**유의 할점** - 동기화된 C++ 버퍼의 경우 thread-safe하기 때문에 모든 IO의 순서가 예상한 것과 정확히 일치함을 보장할 수 있습니다. 코드를 추가함으로 	인해 동기화가 비활성화됐기 때문에 멀티 쓰레드 환경에서는 출력 순서를 보장할 수 없습니다. 그리고 버퍼가 분리되었기 때문에 cin과 C의 scanf, gets, 	getchar 등을 같이 사용하면 안되고 cout과 C의 printf, puts, putchar 등을 같이 사용하면 엉뚱한 결과가 나올 확률이 높기 때문에 사용하면 안 됩니다.



- ```
  cin.tie(null);
  ```

  **사용 이유** - 코드는 cin과 cout의 묶음을 풀어줍니다. 기본적으로 cin과 cout은 묶여있고 묶여있는 스트림들은 한 스트림이 다른 스트림에서 각 IO 작업을 진행하기 전에 자동으로 버퍼를 비워줌을 보장합니다.

​	**원래 `cout`와 `cin`, 입출력은 묶여있다.** 입력요청이 들어오면 그 전에 출력 작업이 있었을 경우(출력 버퍼에 내용이 있는 경우) 버퍼를 비워(flush) 출력하	게 된다. 이 경우, 입출력이 반복될 때, 일일이 버퍼를 지우느라 시간이 오래 걸린다. 따라서, `cin.tie(NULL)` 을 통해 입출력 묶음을 풀면 시간이 단축된다.



###  예시

```c++
#include<iostream>
using namespace std;

int main(void){
	cin.tie(NULL); //입출력 묶음 해제
    ios_base::sync_with_stdio(false);

    int t,num1,num2;
    cin>>t;

    for(int i=0;i<t;i++){
        cin>>num1>>num2;
        cout<<num1+num2<<"\n";
    }
}

2	  //test case -----------cin.tie(NULL); 를 썼을때
1 1   //입력
2 2   //입력
2     //출력
4	  //출력


2     //test case ----------- cin.tie(NULL); 를 안썼을 떄 
1 1   //입력
2     //출력
2 2   //입력
4     //출력
```



- ```
  endl의 경우 개행 문자 출력과 함께 출력 버퍼를 비우는 역할까지 같이 하기 때문에 딜레이가 발생합니다.
  알고리즘을 풀 때는 실행 속도를 높이기 위해 C 스타일의 '\n'을 통해 개행을 진행합시다
  ```





### 매크로

```c++
#define fastio ios_base::sync_with_stdio(false); cin:tie(null) cout:tie(null)

#define fileio freopen("input.txt","r", stdin); freopen("output.txt","w",w,stdout)

#define fori(a,b,c) for(int a=b; a<c; a++)

#define vec vector

#define NL "\n"

```

