
import java.util.*;
import java.io.*;

public class Main_1149_RGB거리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [][] info;
    static int [][] dp;
    public static void main(String[] args) throws Exception{
        input();
        cal();
        print();
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        info = new int [N+1][3];
        dp = new int [N+1][3];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<3;x++ ){
                info[i][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void cal(){
        for(int y=1; y<=N; y++){
            dp[y][0] = Math.min(dp[y-1][1],dp[y-1][2]) +info[y][0];
            dp[y][1] = Math.min(dp[y-1][0],dp[y-1][2]) +info[y][1];
            dp[y][2] = Math.min(dp[y-1][1],dp[y-1][0]) +info[y][2];
        }
    }
    static void print(){
        int ans =Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            if(dp[N][i] <ans){
                ans =dp[N][i];
            }
        }
        System.out.println(ans);
    }
}
