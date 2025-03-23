import java.util.*;
import java.io.*;
public class Main_막대색칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [] dp;
    public static void main(String[] args) throws Exception{
        input();
        cal();
        System.out.println(dp[N]);
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());

        dp= new int[N+1];
        dp[0] = 1;
        dp[1] = 2;
    }
    static void cal(){
        for(int i=2;i<=N; i++){
            dp[i] =dp[i-1] *2 +dp[i-2];
        }
    }
}
