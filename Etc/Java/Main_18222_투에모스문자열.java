import java.io.*;
public class Main_18222_투에모스문자열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long K;
    static long [] dp;
    public static void main(String[] args) throws  Exception{
        input();
        cal(K,0);
    }

    static void input() throws Exception{
        K = Long.parseLong(br.readLine());
        dp = new long[63];
        dp [0] =1;
        for(int i=1; i<63;i++){
            dp[i]=dp[i-1] *2;
        }
    }

    static void cal(long K,long num) {
        if(K ==1){
            if(num %2==1){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
            return;
        }
        for(int i=62;i>-1;i--){
            if(dp[i] <K){
                cal(K-dp[i],num+1);
                return;
            }
        }
    }
}
