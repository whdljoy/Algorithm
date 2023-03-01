#include <stdio.h>
#include <malloc.h>

#define MAX_INPUT 10000
#define MAX_NUM 30000


void init();
void addUser(int uID, int income);
int getTop10(int result[10]);
int save[10];
int user[10];
static int input[MAX_INPUT];

static unsigned int seed = 13410;

static unsigned int pseudoRand() {
	seed = seed * 214013 + 2531011;
	return (unsigned int)(seed >> 11) % MAX_NUM;
}

static void makeInput(int inputLen) {
	for (int i = 0; i < inputLen; i++) {
		input[i] = pseudoRand();
	}
}

static int run() {
	int N, userNum, uID = 0;
	int score = 100, result[10], cnt;
	int sum, check;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &userNum);
		makeInput(userNum);
		for (int j = 0; j < userNum; j++) {
			addUser(uID++, input[j]);
		}
		cnt = getTop10(result);
    sum = 0;
    for (int j = 0; j < cnt; j++)
    {
      sum += result[j];
    }
    scanf("%d", &check);
    if (check != sum)
      score = 0;
	}
	return score;
}

int main(void) {
	setbuf(stdout, NULL);
	freopen("partial_sort_input.txt", "r", stdin);
	int T;
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		init();
		printf("#%d %d\n", tc, run());
	}
	return 0;
}


void init(){
  for (int j = 0; j < 10;j++){
      save[j] = -1;
      user[j] = -1;
  }
    return;
}
void addUser(int uID, int income){
    for (int i = 0; i < 10; i++){
      if(save[i] == -1){
        save[i] = income;
        user[i] = uID;
        break;
      }
      else
      {
        if(save[i] < income ){
          for (int j = 9; j > i; j--){
            save[j] = save[j - 1];
            user[j] = user[j - 1];
          }
          save[i] = income;
          user[i] = uID;
          break;
        }
        // else if( save[i] == income){
        //   if(user[i] > uID){
        //     for (int j = 9; j > i; j--){
        //       save[j] = save[j - 1];
        //       user[j] = user[j - 1];
        //     }
        //     save[i] = income;
        //     user[i] = uID;            
        //   }
        // }
      }
    }
}
int getTop10(int result[10]){
  if (save[9] == -1)
  {
      int len = 0;
      for (int i = 0; i < 10; i++)
      {
        if (save[i] != -1)
        {
          result[i] = user[i];
          len++;
        }
      }
      return len;
  }
  else
  {
    for (int i = 0; i < 10; i++){
      result[i] = user[i];
    }
    return 10;    
  }
}