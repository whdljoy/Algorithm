import java.util.*;
import java.io.*;

public class Solution_3124_최소스패닝트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int V; //정점
    static int E; //간선
    static List <Node> [] graph;
    static boolean [] visited;
    static long [] distance;
    static PriorityQueue<Node> q;
    static long ans =0;
    static int cnt;
    static class Node implements Comparable<Node>{
        int v;
        long dist;
        Node (int v , long dist){
            this.v = v;
            this.dist =dist;
        }
        @Override
        public int compareTo(Node node){
            return Long.compare(this.dist,node.dist);
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
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        StringTokenizer st =new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V+1];
        for(int i=0;i<=V;i++){
            graph[i] =new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,dis));
            graph[e].add(new Node(s,dis));
        }
        visited = new boolean[V+1];
        distance = new long [V+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        q = new PriorityQueue<>();
        distance[1]=0;
        q.offer(new Node(1,distance[1]));
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
            if(cnt ==V){
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
