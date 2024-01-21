import java.util.*;
import java.io.*;
public class Solution_중간값구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int A;
    static PriorityQueue <Integer> pq;
    static PriorityQueue <Integer> mpq;
    static long ans =0;
    static final int mod = 20171109;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC= Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(); //오름차순
        mpq = new PriorityQueue<>(Collections.reverseOrder());  //내림차순
        mpq.add(A);
        ans = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x,y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            cal(x);
            cal(y);
            ans= ans + (mpq.peek() % mod);
            ans = ans % mod;
        }
    }

    static void cal(int num){
        if(mpq.size() == pq.size()){
            mpq.add(num);
        }else{
            pq.add(num);
        }

        if(!pq.isEmpty() && mpq.peek() > pq.peek() ){
            int o1 = pq.poll();
            int o2 = mpq.poll();
            pq.add(o2);
            mpq.add(o1);
        }

    }

}
