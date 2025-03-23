import java.util.*;
import java.io.*;

public class Solution_프리랜서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static class Node implements Comparable<Node> {
        int start, end, cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
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
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] info = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            info[i] = new Node(start, end, cost);
        }


        Arrays.sort(info);


        int[] dp = new int[n];
        dp[0] = info[0].cost;

        for (int i = 1; i < n; i++) {
            int idx = -1;
            for (int j = 0; j < i; j++) {
                if (info[j].end >= info[i].start) {

                    break;
                }
                idx = j;
            }

            int bCost = idx >= 0 ? dp[idx] : 0;
            dp[i] = Math.max(bCost + info[i].cost, dp[i - 1]);
        }

        int res = dp[n - 1];
        sb.append(res).append("\n"); // 결과 추가
    }

}