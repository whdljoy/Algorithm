import java.util.*;
import java.io.*;
public class Solution_수만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int K;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N;i++) {
            int num = Integer.parseInt(br.readLine());
        }
        K = Integer.parseInt(br.readLine());
    }
}
