import java.util.*;
import java.io.*;
public class Solution_수만들기_복기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int K;

    static class Node implements Comparable<Node>{
        int num;
        int count;
        Node (int num, int count){
            this.num = num;
            this.count = count;
        }
        public int compareTo(Node cur){
            return Integer.compare(this.count,cur.count);
        }

    }
    static PriorityQueue <Node> pq;
    static int [] can;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());

        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            cal();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        can = new int [N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++) {
            int num = Integer.parseInt(st.nextToken());
            can[i] = num;
        }
        K = Integer.parseInt(br.readLine());
        pq.add(new Node(K,0));
    }

    static void cal(){
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.num ==0){
                sb.append(cur.count).append("\n");
                break;
            }
            pq.add(new Node(0, cur.count + cur.num));

            for (int i = 0; i < N; i++){
                pq.add(new Node( cur.num / can[i], cur.count + cur.num % can[i] ));
            }
        }
    }
}
