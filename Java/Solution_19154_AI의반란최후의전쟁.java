import java.util.*;
import java.io.*;
public class Solution_19154_AI의반란최후의전쟁 {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N;
    static int [][] save;
    static int ans=0;
    static int [] check;
    static int tmp;
    public static void main(String[] args) throws Exception {
        run();

    }
    static void run() throws Exception{
        TC = Integer.parseInt(br.readLine());
        for(int i=0; i<TC;i++){
            sb.append("#").append(i+1).append(" ");
            input();
            solve();
        }
        System.out.println(sb);
    }
    static void input () throws  Exception{
        N = Integer.parseInt(br.readLine());
        save = new int [N][3];
        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                save[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

    }
    static void dfs(int n,int [] row,int val){
        if(n ==3){
            if(tmp >val){
                check = row;
                tmp =val;
                return;
            }
        }
        for(int i=0;i<N;i++){

        }
    }
    static void solve(){
        if (N<3){
            sb.append(-1).append("\n");
            return;
        }
        ans =0;
        int [][] dp = new int[N][3];
        for(int i=1; i<=N;i++){
            dp[i-1][0] = save[i-1][1] + save[i-1][2];
            dp[i-1][1] = save[i][2] + save[i][0];
            dp[i-1][2] = save[i][0] + save[i][1];

            ans +=Math.min(Math.min(dp[i][0],dp[i][1]),dp[i][2]);
        }

        sb.append(ans).append("\n");
    }

}
