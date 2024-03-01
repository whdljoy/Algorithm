import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_기초PartialSort {

    private final static int MAX_INPUT = 100000;
    private final static int MAX_NUM = 30000;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int[] input = new int[MAX_INPUT];
    private static long seed = 13410;
    static public class UserSolution {
        static class User {
            int uID;
            int income;
            User (int uID,int income){
                this.uID = uID;
                this.income = income;
            }

        }
        static final int MAX_USER = 100001;
        PQ partial;

        static class PQ {
            User[] users;
            int size;

            PQ() {
                users = new User[MAX_USER];
                size = 0;
            }

            void add(int uID, int income) {
                size++;
                users[size] = new User(uID, income);
                int current = size;
                while (current > 1) {
                    int parent = current / 2;
                    if (users[current].income > users[parent].income) {
                        change(current, parent);
                        current = parent;
                    } else if(users[current].income == users[parent].income){
                        if(users[current].uID <users[parent].uID){
                            change(current, parent);
                            current = parent;
                        }else{
                            break;
                        }
                    }else break;
                }
            }

            void change(int current, int another) {
                User tmp = users[another];
                users[another] = users[current];
                users[current] = tmp;
            }

            User poll() {
                User max = users[1];

                users[1] = users[size];
                users[size] = null;
                size--;

                int now = 1;

                while ((now * 2 + 1) <= size) {
                    int larger = now;
                    int left = now * 2;
                    int right = now * 2 + 1;

                    if (users[left].income > users[larger].income) {
                        larger = left;
                    }

                    if (users[right].income > users[larger].income) {
                        larger = right;
                    }

                    if (users[right].income  == users[larger].income){
                        if (users[right].uID < users[larger].uID) {
                            larger = right;
                        }
                    }
                    if (users[left].income  == users[larger].income){
                        if (users[left].uID < users[larger].uID) {
                            larger = left;
                        }
                    }
                    if (larger != now) {
                        change(now, larger);
                        now = larger;
                    } else break;
                }

                return max;
            }
        }

        public void init() {
            partial = new PQ();
        }

        public void addUser(int uID, int income) {
            partial.add(uID, income);
        }

        int getTop10(int[] result) {
            int size = 10;
            if (partial.size < 10) {
                size = partial.size;
            }
            User [] tmp = new User[size];
            for (int i = 0; i < size; i++) {
                 tmp[i] = partial.poll();
                 result[i] = tmp[i].uID;
            }
            for (int i = 0; i < size; i++) {
                partial.add(tmp[i].uID,tmp[i].income);
            }
            return size;
        }
    }
    private static long pseudoRand() {
        seed = (seed * 214013 + 2531011) & 0xffffffffL;
        return (seed>>11) % MAX_NUM;
    }

    private static void makeInput(int inputLen) {
        for(int i = 0; i < inputLen; i++) {
            input[i] = (int)(pseudoRand());
        }
    }

    private static int run() throws Exception {
        int score = 100;
        int N, userNum, uID = 0, ret, sum, ans;
        int[] result = new int[10];
        String str;

        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            userNum = Integer.parseInt(str);
            makeInput(userNum);

            for(int j = 0; j < userNum; j++) {
                usersolution.addUser(uID++, input[j]);
            }
            ret = usersolution.getTop10(result);

            sum = 0;
            for(int j = 0; j < ret; j++) {
                sum += result[j];
            }

            str = br.readLine();
            ans = Integer.parseInt(str);
            if(sum != ans) {
                System.out.println(sum+" "+ans);
                score = 0;
            }
        }
        return score;
    }
    public static void main(String[] args) throws Exception {
        int TC;
        System.setIn(new java.io.FileInputStream("res/partial_sort_input (1).txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            usersolution.init();
            System.out.println("#" + tc + " " + run());
        }
    }
}