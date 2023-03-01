#include <iostream>
#include <malloc.h>
using namespace std;

#define MAX_NODE 10000

enum { ADD_HEAD = 1, ADD_TAIL, ADD_NUM, FIND, REMOVE, PRINT, PRINT_REVERSE, END = 99 };

struct Node {
	int data;
	Node* prev;
	Node* next;
};

Node node[MAX_NODE];
int nodeCnt;
Node* head;

void init();
void addNode2Head(int data);
void addNode2Tail(int data);
void addNode2Num(int data, int num);
int findNode(int data);
void removeNode(int data);
int getList(int output[MAX_NODE]);
int getReversedList(int output[MAX_NODE]);

static void run() {
	while (1) {
		int cmd, data, num, cnt, i = 0;
		int output[MAX_NODE] = { 0 };

		scanf("%d", &cmd);
		switch (cmd) {
		case ADD_HEAD: // 1
			scanf("%d", &data);
			addNode2Head(data);
			break;
		case ADD_TAIL: // 2
			scanf("%d", &data);
			addNode2Tail(data);
			break;
		case ADD_NUM: // 3
			scanf("%d %d", &data, &num);
			addNode2Num(data, num);
			break;
		case FIND: // 4
			scanf("%d", &data);
			num = findNode(data);
			printf("%d\n", num);
			break;
		case REMOVE: // 5
			scanf("%d", &data);
			removeNode(data);
			break;
		case PRINT: // 6
			cnt = getList(output);
			while (cnt--)
				printf("%d ", output[i++]);
			printf("\n");
			break;
		case PRINT_REVERSE: // 7
			cnt = getReversedList(output);
			while (cnt--)
				printf("%d ", output[i++]);
			printf("\n");
			break;
		case END:
			return;
		}
	}
}


int main(void) {
	setbuf(stdout, NULL);
	freopen("dll_input.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		printf("#%d\n", t);
		init();
		run();
		printf("\n");
	}

	return 0;
}


Node* getNode(int data) {
	node[nodeCnt].data = data;
	node[nodeCnt].prev = nullptr;
	node[nodeCnt].next = nullptr;
	return &node[nodeCnt++];
}



void init()
{
  head = (Node*)malloc(sizeof(Node));
  head->data = -1;
  head->next = nullptr;
  head->prev = nullptr;
  nodeCnt = 0;
}

void addNode2Head(int data) 
{
	Node *node = getNode(data);
	node->next = head->next;
  node->prev = head;
  if(head->next != nullptr){
    head->next->prev = node;
  }
  head->next = node;

}

void addNode2Tail(int data) 
{
	Node *node =head;
	while(1){
		if(node ->next == nullptr){
      Node *cur= getNode(data);
      cur->prev = node;
      cur->next = node->next;
      node->next = cur;
      break;
    }
		else
		{
			node = node->next;
		}
	}
  return;
}

void addNode2Num(int data, int num) 
{
	Node *position;
	position = head;
  int cnt = 1;
  while(1){
    if((cnt == num) || (position->next == nullptr)){
      break;
    }
    else{
      position = position->next;
      cnt++;
    }
  }
  Node *node = getNode(data);
  if(position ->next != nullptr){
    position->next->prev = node;
  }
  node->prev = position;
  node->next = position->next;
  position->next = node;
  return;
}

int findNode(int data) 
{
  Node *position;
  int cnt=1;
  position = head;
  while ((position->next != nullptr) && (position->next->data != data)) {
    cnt++;
    position = position->next;
  }
  return cnt;
}

void removeNode(int data) 
{
	Node *position;
	position = head;
  while ((position->next != nullptr) && (position->next->data != data))
  {
    position = position->next;
  }
  if(position->next !=nullptr){
    if (position->next->next != nullptr) {
        position->next->next->prev = position->next;
    }
    position->next = position->next->next;
  }

}


int getList(int output[MAX_NODE]) 
{
  Node *cur = head->next;
  int cnt = 0;
  while (1)
  {
      output[cnt] = cur->data;
      cnt++;
      if(cur->next == nullptr){
        break;
      }
      cur = cur->next;
  }
  return cnt;
}

int getReversedList(int output[MAX_NODE]) 
{
  Node *cur = head->next;
  int cnt = 0;
  while (cur->next != nullptr)
  {
      if(cur->prev != nullptr){
      }
      cur = cur->next;
  }
  while (1)
  {
      output[cnt] = cur->data;
      cnt++;    
      if(cur->prev == head){
        break;
      }
      cur = cur->prev;
  }
  return cnt;
}