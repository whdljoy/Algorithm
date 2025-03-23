import java.util.*;
import java.io.*;


public class Solution_LCS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int[][] dp = new int[1001][1001]; // DP 배열

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb.toString());
    }

    static void input() throws Exception {

        String a = br.readLine();
        String b = br.readLine();

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        sb.append(dp[a.length()][b.length()]).append("\n"); // 결과 추가
    }
}