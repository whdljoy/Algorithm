import java.util.*;
import java.io.*;

public class Solution_비밀 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC = 10; // 테스트 케이스 수 (문제에 따라 조정 가능)
    static int N; // 아이들의 수
    static int K; // 비밀의 수
    static ArrayList<HashSet<Integer>> pathList; // 경로 저장
    static int[] visited; // 방문 체크 배열
    static int maxDepth; // 최대 깊이

    public static void main(String[] args) throws Exception {
        run();
        System.out.print(sb.toString()); // 최종 결과 출력
    }

    static void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()); // 테스트 케이스 수 입력
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
            solve();
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // pathList 초기화
        pathList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            pathList.add(new HashSet<Integer>());
        }

        // K개의 비밀 경로 입력
        for (int i = 0; i < K; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 1; j < tmp.length - 1; j++) {
                int from = Integer.parseInt(tmp[j]);
                int to = Integer.parseInt(tmp[j + 1]);
                pathList.get(from).add(to);
            }
        }
    }

    static void solve() {
        maxDepth = Integer.MIN_VALUE;
        visited = new int[N + 1];

        // 각 노드에서 DFS로 최대 깊이 탐색
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, 0);
            visited[i] = 1;
            dfs(i, 1);
        }

        for (int i = 1; i <= N; i++) {
            sb.append(pathList.get(i).size()).append(" ");
        }
        // 최대 깊이 출력
        sb.append(maxDepth).append("\n");
    }

    static void dfs(int n, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        for (int next : pathList.get(n)) {
            if (visited[next] == 0) {
                visited[next] = 1;
                dfs(next, depth + 1);
                visited[next] = 0;
            }
        }
    }
}