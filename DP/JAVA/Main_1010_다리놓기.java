import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class Main_1010_다리놓기 {
    static int N;
    static int M;
    static int TC;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        run();
    }

    static void run() throws Exception{
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            input();
            if(M-N < N){
                System.out.println(cal(M-N,M));
            }else{
                System.out.println(cal(N,M));
            }
        }
    }
    static void input() throws Exception{
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
    static BigInteger cal(int left, int right){
        if(left == right){
            return new BigInteger("1");
        }

        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        for (int i=2; i< left+1 ; i++){
            a = a.multiply(new BigInteger(Integer.toString(i)));
        }
        for (int j= right; j > (right-left); j--){
            b= b.multiply(new BigInteger(Integer.toString(j)));
        }
        return  b.divide(a);
    }
}
