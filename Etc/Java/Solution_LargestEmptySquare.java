import java.util.*;
import java.io.*;

public class Solution_LargestEmptySquare {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC; // 테스트 케이스 수

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb.toString()); // 최종 결과 출력
    }

    static void input() throws Exception {
        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine().trim();
            for (int j = 1; j <= N; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
                if (map[i][j] == 0) map[i][j] = 1;
                else map[i][j] = 0;
            }
        }

        int[][] DP = new int[N + 1][N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) {
                    DP[i][j] = min(DP[i + 1][j], DP[i][j + 1], DP[i - 1][j - 1]) + 1;
                    answer = Math.max(answer, DP[i][j]);
                }
            }
        }

        sb.append(answer).append("\n"); // 결과 추가
    }

    private static int min(int A, int B, int C) {
        return Math.min(Math.min(A, B), C);
    }
}