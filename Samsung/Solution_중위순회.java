import java.util.*;
import java.io.*;
public class Solution_중위순회 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int tc =10;
    static int N;

    static class Node{
        char data;
        int left;
        int right;

        Node(char data){
            this.data = data;
            this.left = 0;
            this.right =0;
        }

    }
    static Node [] tree;

    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            simulate(1);
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
            tree [start] = new Node(st.nextToken().charAt(0));
            if(st.countTokens() ==2){
                tree[start].left = Integer.parseInt(st.nextToken());
                tree[start].right = Integer.parseInt(st.nextToken());
            }else if (st.countTokens() ==1){
                tree[start].left = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void simulate(int num){
        if(tree[num].left != 0){
            simulate(tree[num].left);
        }
        sb.append(tree[num].data);
        if(tree[num].right != 0){
            simulate(tree[num].right);
        }
    }
}
