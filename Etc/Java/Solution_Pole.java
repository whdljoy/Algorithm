import java.io.*;
import java.util.*;
public class Solution_Pole {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int left;
    static int right;
    static long[][][] dp;

    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        dp = new long[21][21][21];
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        left =Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());
        simulate();
    }
    static void simulate() {
        dp[1][1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int l = 1; l <= N; l++) {
                for (int r = 1; r <= N; r++) {
                    dp[n][l][r] = dp[n - 1][l - 1][r] + dp[n - 1][l][r - 1] + dp[n - 1][l][r] * (n - 2);
                }
            }
        }
        sb.append(dp[N][left][right]).append("\n");
    }
}








