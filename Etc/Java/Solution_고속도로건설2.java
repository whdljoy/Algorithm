import java.util.*;
import java.io.*;

public class Solution_고속도로건설2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static List<Edge> mstPath;
    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge target) {
            return this.cost - target.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb.toString());
    }

    static void input() throws Exception {
        N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        M = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(from, to, cost));
        }

        mstPath = new ArrayList<>();
        MSTByKruskal();

        int costSum = 0;
        for (Edge edge : mstPath) {
            costSum += edge.cost;
        }
        sb.append(costSum).append("\n");
    }

    private static void MSTByKruskal() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int a = find(edge.from);
            int b = find(edge.to);


            if (a == b) continue;
            union(a, b);
            mstPath.add(edge);
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

}