import java.util.*;
import java.io.*;
public class Main_2252_줄세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int M;
    static int [] depth;
    static List<Integer> ans;
    static Queue<Integer> q;
    static List <Integer> [] graph;
    public static void main(String[] args)  throws  Exception{
        run();
    }
    static void run() throws Exception{
        input();
        for(int i=1;i<=N;i++){
            if(depth [i] == 0){
                q.add(i);
            }
        }
        bfs();
        for(int a :ans){
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        depth = new int [N+1];
        graph = new ArrayList[N+1];
        ans = new ArrayList<>();
        q= new ArrayDeque<>();
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st= new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            depth[e] +=1;
        }
    }

    static void bfs(){
        while(!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);
            for(int node : graph[cur]){
                depth[node] -=1;
                if(depth[node] == 0){
                    q.add(node);
                }
            }
        }
    }

}
