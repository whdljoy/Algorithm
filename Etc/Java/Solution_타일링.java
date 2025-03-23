import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution_타일링 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static BigInteger[] DP = new BigInteger[251]; // 1 ~ 250

    public static void main(String[] args) throws Exception {
        DP[1] = new BigInteger("1");
        DP[2] = new BigInteger("3");
        for (int i = 3; i <= 250; i++) {
            DP[i] = DP[i - 2].multiply(new BigInteger("2")).add(DP[i - 1]);
        }
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
        int N = Integer.parseInt(br.readLine().trim());
        sb.append(DP[N]).append("\n");
    }
}