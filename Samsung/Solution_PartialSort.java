import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_PartialSort  {

    private final static int MAX_INPUT = 100000;
    private final static int MAX_NUM = 30000;
    static class User implements Comparable<User>{
        int uID;
        int income;
        User (int uID,int income){
            this.uID = uID;
            this.income = income;
        }
        @Override
        public int compareTo(User cur){
            if(cur.income == this.income){
                return Integer.compare(this.uID,cur.uID);
            }
            return Integer.compare(cur.income,this.income);

        }
    }
    static public class UserSolution {
        PriorityQueue <User> partial;

        public void init() {
            partial = new PriorityQueue<>();
        }

        public void addUser(int uID, int income) {
            partial.add(new User(uID,income));
        }

        int getTop10(int[] result) {
            int size = 10;
            if(partial.size() <10){
                size = partial.size();
            }
            for(int i=0; i<size;i++){
                result[i] = partial.poll().uID;
            }
            return size;
        }
    }
    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int[] input = new int[MAX_INPUT];
    private static long seed = 13410;

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
                score = 0;
            }
        }
        return score;
    }
    public static void main(String[] args) throws Exception {
        int TC;

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            usersolution.init();
            System.out.println("#" + tc + " " + run());
        }
    }
}