#include <iostream>
#include <malloc.h>
using namespace std;

#define MAX_NODE 10000

enum { ADD_HEAD = 1, ADD_TAIL, ADD_NUM, REMOVE, PRINT, END = 99 };

struct Node {
	int data;
	Node* next;
};

Node node[MAX_NODE];
int nodeCnt;
Node* head;

void init();
void addNode2Head(int data);
void addNode2Tail(int data);
void addNode2Num(int data, int num);
void removeNode(int data);
int getList(int output[MAX_NODE]);

static void run() {
	while (1) {
		int cmd, data, num, cnt, i;
		int output[MAX_NODE] = { 0 };

		scanf("%d", &cmd);
		switch (cmd) {
		case ADD_HEAD:
			scanf("%d", &data);
			addNode2Head(data);
			break;
		case ADD_TAIL:
			scanf("%d", &data);
			addNode2Tail(data);
			break;
		case ADD_NUM:
			scanf("%d %d", &data, &num);
			addNode2Num(data, num);
			break;
		case REMOVE:
			scanf("%d", &data);
			removeNode(data);
			break;
		case PRINT:
			cnt = getList(output);
			i = 0;
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
	freopen("sll_input.txt", "r", stdin);

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
	node[nodeCnt].next = nullptr;
	return &node[nodeCnt++];
}



void init()
{
  head = (Node*)malloc(sizeof(Node));
  head->data = -1;
  head->next = nullptr;
  nodeCnt = 0;
}

void addNode2Head(int data) 
{
	Node *node = getNode(data);
	node->next = head->next;
	head->next = node;
}

void addNode2Tail(int data) 
{
	Node *node =head;
	while(1){
		if(node ->next == nullptr){
      Node *cur= getNode(data);
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
  node->next = position->next;
  position->next = node;
  return;
}

void removeNode(int data) 
{
	Node *position;
	position = head;
  while ((position->next != nullptr) && (position->next->data != data)) {
			position = position->next;
	}
  if (position->next != nullptr) {
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
