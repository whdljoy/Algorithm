import java.util.*;
import java.io.*;
public class Solution_사칙연산 {
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
    static int ans=1;
    static Deque <Character> dt;

    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            sb.append(simulate(1));

            sb.append("\n");
        }
        System.out.println(sb);

    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            String cur = st.nextToken();
            if(cur.charAt(0)=='+'||cur.charAt(0)=='-'||cur.charAt(0)=='/'||cur.charAt(0)=='*'){
                tree [start] = new Node(-1);
                tree[start].setOP(cur.charAt(0));
            }
            else{
                tree[start] = new Node(Integer.parseInt(cur));
            }
            if(st.countTokens() ==2){
                tree[start].left = Integer.parseInt(st.nextToken());
                tree[start].right = Integer.parseInt(st.nextToken());
            }else if (st.countTokens() ==1){
                tree[start].left = Integer.parseInt(st.nextToken());
            }
        }
        dt = new ArrayDeque<>();
    }

    static int simulate(int num){
        int first=0, second=0;
        if(tree[num].left != 0){
            first = simulate(tree[num].left);
        }
        if(tree[num].right != 0){
             second =simulate(tree[num].right);
        }
        if(tree[num].num != -1){
            return tree[num].num;
        }else{
            if(tree[num].op =='+'){
                return first +second;
            }else if(tree[num].op =='/' ){
                return first /second;
            }else if(tree[num].op =='-' ){
                return first - second;
            }else if(tree[num].op =='*' ){
                return first *second;
            }
        }
        return 0;
    }
}
