import java.util.*;
import java.io.*;
public class Solution_1238_Contact {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int TC =10;
    static final int MAX_P =101;
    static int START ;
    static int data_length;
    static int [] check;
    static List <Integer> [] graph;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            bfs();
        }
        System.out.println(sb);
    }

    static void input() throws Exception{
        StringTokenizer st= new StringTokenizer(br.readLine());
        data_length  = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());

        check =new int[MAX_P];
        graph = new ArrayList[MAX_P];
        for(int i=0; i<MAX_P;i++){
            graph[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<data_length; i=i+2){
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
        }
    }

    static void bfs(){
        Queue <Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {START,1});
        check[START]=1;
        while(!q.isEmpty()){
            Integer [] current = q.poll();
            int cur = current[0];
            int score =current[1];
            if(!graph[cur].isEmpty()){
                for(int node :graph[cur]){
                    if(check[node] ==0){
                        q.add(new Integer[]{node,score+1});
                        check[node] = score+1;
                    }
                }
            }
            if(q.isEmpty()){
                int mx = 0;
                for(int i=1; i<MAX_P;i++){
                    if(check[i] == score && mx<i){
                        mx =i;
                    }
                }
                sb.append(mx).append("\n");
            }
        }
    }

}
