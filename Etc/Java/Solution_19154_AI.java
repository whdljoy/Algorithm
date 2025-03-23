import java.util.*;
import java.io.*;
public class Solution_19154_AI {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N;
    static int [][] save;
    static int [][] dp;
    static int check=0;
    static int ans=0;
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
    static void dfs(int [] record,int val,int depth){
        if(depth == 3){
            if(check >= val){

                int current=0;
                for(int i=0;i<N;i++){
                    if(i !=record[0] && i != record[1] && i != record[2]){
                        current +=Math.min(Math.min(dp[i][0],dp[i][1]),dp[i][2]);;
                    }
                }
                if (check >val){
                    ans = current+val;
                    check=val;
                }
                else {
                    if(ans > current+val){
                        ans = current+val;
                        check=val;
                    }
                }
            }
            return;
        }
        if(depth ==0){
            for(int i=0;i<N;i++){
                record[depth]=i;
                dfs(record,val+dp[i][depth],depth+1);
            }
        }

        if(depth ==1){
            for(int i=0;i<N;i++){
                if(i != record[0]){
                    record[depth]=i;
                    dfs(record,val+dp[i][depth],depth+1);
                }
            }
        }
        if(depth ==2){
            for(int i=0;i<N;i++){
                if(i != record[1] && i !=record[0]){
                    record[depth]=i;
                    dfs(record,val+dp[i][depth],depth+1);
                }
            }
        }

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
        dp = new int[N][3];
        for(int i=0; i<N;i++){
            dp[i][0] = save[i][1] + save[i][2];
            dp[i][1] = save[i][2] + save[i][0];
            dp[i][2] = save[i][0] + save[i][1];

        }

    }
    static void solve(){
        if (N<3){
            sb.append(-1).append("\n");
            return;
        }
        ans =Integer.MAX_VALUE;
        check = Integer.MAX_VALUE;
        int []record = new int[3];
        Arrays.fill(record,-1);
        dfs(record,0,0);

        sb.append(ans).append("\n");
    }

}
