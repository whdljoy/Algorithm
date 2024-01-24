import java.util.*;
import java.io.*;


/**
 * 다익스트라 두번
 *
 * [i] -> x x-> [j]
 * x 에서 [j]의 경우 다익스트라를 경우 구하고
 * [i] x 의 경우 기존의 그래프의 엣지를 반대로 뒤집어 다익스트라를 하는 경우 [i]에서 x로 가는 최단경로가 나오게 된다.
 */
public class Solution_간담회참석 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int TC = 10;
    static int N, M, X;
    static List[] graph;

    static List[] graph_reverse;
    static int[] dist;
    static int[] dist2;
    static int[] total;
    static PriorityQueue<Node> pq;

    static class Node {
        int point;
        int distance;

        Node(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
            simulate();
        }
        System.out.print(sb);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        graph_reverse = new ArrayList[N + 1];
        for (int y = 0; y <= N; y++) {
            graph[y] = new ArrayList<>();
            graph_reverse[y] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, val));
            graph_reverse[e].add(new Node(s,val));
        }
        total = new int[N + 1];
        pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.distance, o2.distance));

    }

    static void simulate() {
        dik(X);
        dik2(X);
        cal();
    }

    static void dik(int start) {
        dist = new int [N+1];
        for (int i = 0; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq.offer(new Node(start, 0));
        dist[start] = 0; //시작점
        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.point] < curNode.distance) continue;
            if (visited[curNode.point]) continue;

            visited[curNode.point] = true;
            for (Object current : graph[curNode.point]) {
                Node nxtNode = (Node) current;
                if (dist[nxtNode.point] > curNode.distance + nxtNode.distance) {
                    dist[nxtNode.point] = curNode.distance + nxtNode.distance;
                    pq.offer(new Node(nxtNode.point, dist[nxtNode.point]));
                }
            }
        }

    }
    static void dik2(int start) {
        dist2 = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dist2[i] = Integer.MAX_VALUE;
        }
        pq.offer(new Node(start, 0));
        dist2[start] = 0; //시작점
        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist2[curNode.point] < curNode.distance) continue;
            if (visited[curNode.point]) continue;

            visited[curNode.point] = true;
            for (Object current : graph_reverse[curNode.point]) {
                Node nxtNode = (Node) current;
                if (dist2[nxtNode.point] > curNode.distance + nxtNode.distance) {
                    dist2[nxtNode.point] = curNode.distance + nxtNode.distance;
                    pq.offer(new Node(nxtNode.point, dist2[nxtNode.point]));
                }
            }
        }
    }
    static void cal () {

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] != Integer.MAX_VALUE && dist2[i] != Integer.MAX_VALUE) {
                ans = Math.max(dist[i] +dist2[i],ans);
            }
        }
        sb.append(ans).append("\n");
    }
}

