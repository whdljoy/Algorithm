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
};

int s_len,o_len;
Node* head;

void init();
void addNodeTail(deque <int> &data);
void addNodeNum(int position, deque <int> &data);
void removeNode(int position,int num);
void getList();
enum
{
  ADD_NUM = 0,
  ADD_TAIL = 1,
  REMOVE = 2
};
static void run() {
  for (int a = 0; a < o_len; a++)
  {
    int od, position, num, val;
    char cmd[1];
    cin >> cmd[0];    
    if( strcmp(cmd,"I") == 0){
      od = 0;
    }else if( strcmp(cmd,"A") == 0){
      od = 1;
    }else if( strcmp(cmd,"D") == 0){
      od = 2;
    }
    deque <int> data;
    switch (od)
    {
    case ADD_NUM:
			scanf("%d %d", &position, &num);
      for (int i = 0; i < num;i++){
        scanf("%d", &val);
        data.push_back(val);
      }
      addNodeNum(position, data);
      break;
		case ADD_TAIL:
			scanf("%d",  &num);
      for (int i = 0; i < num;i++){
        scanf("%d", &val);
        data.push_back(val);
      } 
			addNodeTail(data);
			break;
		case REMOVE:
			scanf("%d %d", &position,&num);
			removeNode(position,num);
			break;
    }
  }
}
int main(int argc, char** argv)
{
	int test_case;
	int T = 10;

	// freopen("input.txt", "r", stdin);
	for(test_case = 1; test_case <= T; ++test_case)
	{
    cin >> s_len;
    init();
    deque <int> s_secret;
    for (int i = 0; i < s_len; i++)
    {
      int s_val;
      cin >> s_val;
      s_secret.push_back(s_val);
    }
    addNodeTail(s_secret);
    cin >> o_len;
    run();
    printf("#%d", test_case);
    getList();
    printf("\n");
  }
  return 0;//정상종료시 반드시 0을 리턴해야합니다.
}


Node* getNode(int data) {
	Node* node = (Node*)malloc(sizeof(Node));
	node->data = data;
	node->next = nullptr;  
  return node;
}

void init()
{
  head = (Node*)malloc(sizeof(Node));
  head->data = -1;
  head->next = nullptr;
}

void addNodeTail(deque <int> &data)
{
	Node *node =head;
	while(1){
		if(node ->next == nullptr){
      while(!data.empty()){
        int val = data.back();
        Node *cur= getNode(val);
        cur->next = node->next;
        node->next = cur;
        data.pop_back();
      }
      break;
    }
		else
		{
			node = node->next;
		}
	}
  return;
}

void addNodeNum(int position, deque <int> &data) 
{
	Node *pos =head;
  int cnt = 0;
  while(1){
    if((cnt == position) || (pos->next == nullptr)){
      break;
    }
    else{
      pos = pos->next;
      cnt++;
    }
  }
  while(!data.empty()){
    int val = data.back();
    Node *node = getNode(val);
    node->next = pos->next;
    pos->next = node;
    data.pop_back();
  }  
  return;
}

void removeNode(int position,int num)
{
	Node *pos = head;
  int cnt = 0;
  while(1){
    if((cnt == position) || (pos->next == nullptr)){
      break;
    }
    else{
      pos = pos->next;
      cnt++;
    }
  }
  for (int i = 0; i < num; i++)
  {
    if (pos->next != nullptr){
			pos->next = pos->next->next;
    }
  }
}

void getList() 
{
  Node *cur = head->next;
  for (int i = 0; i < 10;  i++)
  {
      cout <<" "<<cur->data;
      if(cur->next == nullptr){
        break;
      }
      cur = cur->next;
  }
  free(head);
}
