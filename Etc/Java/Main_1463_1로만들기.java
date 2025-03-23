import java.io.*;
public class Main_1463_1로만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int []dp;
    public static void main(String[] args) throws Exception{
        input();
        cal();
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        dp= new int [N+1];
        for(int i=1;i<=N;i++) {
            dp[i] = i-1;
        }
    }
    static void cal(){
        for(int i=1;i<N;i++){
            dp[i+1]= Math.min(dp[i]+1,dp[i+1]);
            if(i*2<=N){
                dp[i*2]=Math.min(dp[i]+1,dp[i*2]);
            }
            if(i*3<= N){
                dp[i*3]= Math.min(dp[i]+1,dp[i*3]);
            }
        }
        System.out.println(dp[N]);
    }

}
