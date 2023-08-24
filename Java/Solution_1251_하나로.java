import java.util.*;
import java.io.*;

public class Solution_1251_하나로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int N; //섬의 갯수
    static long []island_x;
    static long []island_y;
    static double E;
    static List <Node> [] graph;
    static boolean [] visited;
    static double [] distance;
    static PriorityQueue<Node> q;
    static double ans =0;
    static int cnt;
    static class Node implements Comparable<Node>{
        int v;
        double dist;
        Node (int v , double dist){
            this.v = v;
            this.dist =dist;
        }
        @Override
        public int compareTo(Node node){
            return Double.compare(this.dist,node.dist);
        }

    }

    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        tc = Integer.parseInt(br.readLine());
        for(int i=1 ;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            cal();
            sb.append(Math.round(ans)).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        island_x = new long [N];
        island_y = new long [N];
        st =new StringTokenizer(br.readLine());
        for(int x = 0;x <N;x++){
            island_x[x] = Integer.parseInt(st.nextToken());
        }
        st =new StringTokenizer(br.readLine());
        for(int y = 0;y <N;y++){
            island_y[y] = Integer.parseInt(st.nextToken());
        }
        E = Double.parseDouble(br.readLine());
        graph = new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i] =new ArrayList<>();
        }
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++) {
                double dist =  (Math.pow(Math.sqrt(Math.pow(island_x[y] -island_x[x], 2) + Math.pow(island_y[y] -island_y[x], 2)),2)* E);
                graph[y].add(new Node(x,dist));
            }
        }
        visited = new boolean[N];
        distance = new double [N];
        Arrays.fill(distance, Double.MAX_VALUE);
        q = new PriorityQueue<>();
        distance[0]=0;
        q.offer(new Node(0,distance[0]));
        cnt =0;
        ans =0;
    }
    static void cal(){
        while(true){
            Node cur =q.poll();
            if(visited[cur.v]) {
                continue;
            }

            visited[cur.v] = true;
            ans += cur.dist;
            cnt++;
            if(cnt ==N){
                break;
            }
            for (Node v : graph[cur.v]){
                if(!visited[v.v] && distance[v.v] > v.dist) {
                    distance[v.v] = v.dist;
                    q.offer(new Node(v.v,distance[v.v]));
                }
            }
        }
    }
}
