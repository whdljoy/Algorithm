/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// float b, c;
// double d, e, f;
// char g;
// char var[256];
// long long AB;
// cin >> a;                            // int 변수 1개 입력받는 예제
// cin >> b >> c;                       // float 변수 2개 입력받는 예제 
// cin >> d >> e >> f;                  // double 변수 3개 입력받는 예제
// cin >> g;                            // char 변수 1개 입력받는 예제
// cin >> var;                          // 문자열 1개 입력받는 예제
// cin >> AB;                           // long long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// float b = 1.0, c = 2.0;               
// double d = 3.0, e = 0.0; f = 1.0;
// char g = 'b';
// char var[256] = "ABCDEFG";
// long long AB = 12345678901234567L;
// cout << a;                           // int 변수 1개 출력하는 예제
// cout << b << " " << c;               // float 변수 2개 출력하는 예제
// cout << d << " " << e << " " << f;   // double 변수 3개 출력하는 예제
// cout << g;                           // char 변수 1개 출력하는 예제
// cout << var;                         // 문자열 1개 출력하는 예제
// cout << AB;                          // long long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

#include<iostream>
#include <cstring>
#include <vector>
#include <deque>

using namespace std;

struct Node {
	int data;
	Node* next;
  Node* prev;
};

int s_len,o_len;
Node* head;
void run();
void init();
void addNodeNum(int idx,int val);
void removeNode(int idx);
void changeNode(int idx,int val);
void addNodeHead(int data);

int main(int argc, char **argv)
{
	int test_case;
	int T;
	/*
	*/
	freopen("sample_input.txt", "r", stdin);
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    int N, M, L;
    cin >> N >> M >> L;
    init();
    for (int j = 0; j < N; j++)
    {
      int num;
      cin >> num;
      addNodeHead(num);
    }
    for (int i = 0; i < M; i++)
    {
      run();
    }
  }
  return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

void run() {

  int od, idx, val;
  char cmd[1];
  cin >> cmd[0];    
  if( strcmp(cmd,"I") == 0){
    od = 0;
  }else if( strcmp(cmd,"D") == 0){
    od = 1;
  }else if( strcmp(cmd,"C") == 0){
    od = 2;
  }
  switch (od)
  {
  case 0:
    scanf("%d %d", &idx, &val);
    addNodeNum(idx, val);
    break;
  case 1:
    scanf("%d",&idx);
    removeNode(idx);
    break;
  case 2:
    scanf("%d %d", &idx, &val);
    changeNode(idx,val);
    break;
  }
  
}

Node* getNode(int data) {
	Node* node = (Node*)malloc(sizeof(Node));
	node->data = data;
	node->next = nullptr;
  node->prev = nullptr;
  return node;
}

void init()
{
  head = (Node*)malloc(sizeof(Node));
  head->data = -1;
  head->next = nullptr;
  head->prev = nullptr;
}

void addNodeHead(int data) 
{
	Node *node = getNode(data);
	node->next = head->next;
  node->prev = head;
  if(head->next != nullptr){
    head->next->prev = node;
  }
  head->next = node;

}

void addNodeNum(int idx,int val){
	Node *position;
	position = head;
  int cnt = 1;
  while(1){
    if((cnt == idx) || (position->next == nullptr)){
      break;
    }
    else{
      position = position->next;
      cnt++;
    }
  }
  Node *node = getNode(val);
  if(position ->next != nullptr){
    position->next->prev = node;
  }
  node->prev = position;
  node->next = position->next;
  position->next = node;
  return;
}

void removeNode(int idx){
	Node *position;
	position = head;
  int cnt = 1;
  while(1){
    if((cnt == idx) || (position->next == nullptr)){
      break;
    }
    else{
      position = position->next;
      cnt++;
    }
  }
  if(position->next !=nullptr){
    if (position->next->next != nullptr) {
        position->next->next->prev = position->next;
    }
    position->next = position->next->next;
  }
}
void changeNode(int idx,int val){
	Node *position;
	position = head;
  while ((position->next != nullptr) && (position->next->data != val)) {
			position = position->next;
	}
  if (position->next != nullptr) {
			position->next->data = val;
  }
}