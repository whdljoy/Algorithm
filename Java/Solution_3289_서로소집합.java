import java.io.*;
import java.util.*;

public class Solution_3289_서로소집합 {
    static int n; // 원소 개수
    static int[] parents; // 부모원소를 관리(트리처럼 사용)

    private static void make() {
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parents[a])
            return a; // 자신이 대표자
        return parents[a] = find(parents[a]); // 자신이 속한 집합의 대표자를 자신의 부모로
    }

    // 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)
            return false; // 이미 같은 집합으로 합치지 않음

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken()); // n이하의 자연수
            int m = Integer.parseInt(st.nextToken()); // m개의 연산

            parents = new int[n + 1];
            make();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (k == 0) {
                    union(a, b);
                } else if (k == 1) {
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else
                        sb.append(0);
                }
            }
            sb.append("\n");

        }

        System.out.println(sb);
        br.close();
    }

}