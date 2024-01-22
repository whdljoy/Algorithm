
import java.util.*;
import java.io.*;
public class Solution_촛불이벤트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static long N;
    static long ans;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            binarySearch(0,(1l<<31) -1);
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        N = Long.parseLong(br.readLine());
    }
    static void binarySearch(long start, long end) {
        if(start > end) {
            ans =-1;
            return;
        }
        long mid = (start+end) /2;
        long num = mid * (mid+1) /2;
        if(num == N) {
            ans =mid;
            return;
        }else if(num > N) {
            binarySearch(start,mid-1);
        }else if(num < N){
            binarySearch(mid+1,end);
        }

    }

}
