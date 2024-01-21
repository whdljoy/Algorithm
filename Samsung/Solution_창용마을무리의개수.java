import java.util.*;
import java.io.*;
public class Solution_창용마을무리의개수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int M;

    static int [] parent;
    static int find(int num){
        if(num != parent[num]){
            return find(parent[num]);
        }
        return parent[num];
    }
    static void union(int a, int b){
        int A = find (a);
        int B = find (b);
        parent [A] = B;
        for(int i=1;i<=N;i++){
            if(parent[i]==A ){
                parent[i] =B;
            }
        }
    }
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            cal();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int [N+1];
        for(int i=0; i<=N;i++){
            parent[i] = i;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

    }
    static void cal() {
        Set <Integer> cur = new HashSet<>();
        for(int i=1;i<=N;i++){
            cur.add(parent[i]);
        }
        sb.append(cur.size()).append("\n");
    }
}
