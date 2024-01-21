import java.util.*;
import java.io.*;

public class Solution_힙 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static PriorityQueue <Integer> pq ;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if(op == 1){
                int num = Integer.parseInt(st.nextToken());
                pq.add(num);
            }
            else if (op==2){
                if(pq.isEmpty()){
                    sb.append(-1);
                }else{
                    sb.append(pq.poll());
                }
                sb.append(" ");
            }
        }
        sb.append("\n");

    }
}
