
import java.util.*;
import java.io.*;
public class Solution_사탕가방 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N;
    static long M;
    static long [] can;
    static long ans=0;
    static long min;
    static long mod;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            binarySearch(1,can[N-1]);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        can = new long [N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            can[i] = Long.parseLong(st.nextToken());
        }
        ans =0;
        Arrays.sort(can);
    }

    static void binarySearch(long left, long right) {
        if(left >right) {
            return;
        }
        long mid = (left +right)/2;
        long cur=0;
        for(int i=0;i<N;i++) {
            cur += can[i]/ mid;
        }
        if (cur < M)
            binarySearch(left,mid-1);
        else {
            ans = mid;
            binarySearch(mid+1,right);
        }
    }


}