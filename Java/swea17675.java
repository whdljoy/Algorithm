import java.util.*;
import java.io.*;
public class swea17675 {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int minCost;

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        for(int test_case = 1; test_case <= TC; test_case++)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("#"+test_case+" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                graph[X].add(new Edge(Y, C));
            }
            minCost = Integer.MAX_VALUE;
            visited = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                if(!visited[i]){
                    dfs(i, i, 0);
                }
            }
            if(minCost == Integer.MAX_VALUE){
                minCost = -1;
            }
            sb.append(minCost);
            System.out.println(sb);
        }
    }
    private static void dfs(int start, int current, int cost) {
        if (visited[current]) {
            if (current == start && cost > 0) {
                minCost = Math.min(minCost, cost);
                return;
            } else {
                return;
            }
        }

        visited[current] = true;

        for (Edge edge : graph[current]) {
            dfs(start, edge.to, cost + edge.cost);
        }

        visited[current] = false;
    }
}



