import java.util.*;
import java.io.*;
public class Solution_사칙연산유효성검사 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int tc =10;
    static int N;

    static class Node{
        char op;
        int num;
        int left;
        int right;

        Node(int num){
            this.num = num;
            this.left = 0;
            this.right =0;
            this.op=' ';
        }
        void setOP(char op){
            this.op =op;
        }

    }
    static Node [] tree;
    static int ans=0;


    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);

    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];
        ans =1;
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            char cur = st.nextToken().charAt(0);
            if (st.hasMoreTokens()){
                if(!(cur == '+' || cur == '-' ||cur == '*' ||cur == '/')){
                    ans =0;
                }
            }else{
                if((cur< '0' || cur> '9')){
                    ans =0;
                }
            }

        }

    }

}
