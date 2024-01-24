import java.util.*;
import java.io.*;

/**
 *
 *
 * 다익스트라를 통해 한점에서 가장 짧은 길을 청소한뒤
 *
 * 진행하면서 각 노드에서 존재하는 엣지 중 가장 값이 적은 값을 업데이트 한다
 * 어차피 모든 경로는 이어져 있기 떄문에 각 노드에서 가장 짧은 엣지들만 더한다면 모든 값중 가장 짧은길이다
 * -> 한번 갔던 길은 청소를 했기때문에 돌아오더라도 두번 더하지 않고 그렇기 때문에 가장 짧은 엣지들만 더하면된다.
 *
 *
 */
public class Solution_가장짧은길전부청소하기 {
    static class Node{
        int dest;
        long dist;
        Node (int dest,long dist){
            this.dest = dest;
            this.dist = dist;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N,M;
    static List [] info;
    static long [] dist;
    static long [] trace;
    static PriorityQueue<Node> pq;
    static long ans=0;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            dik();
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new ArrayList [N+1];
        for(int i=0; i<=N;i++) {
            info[i] = new ArrayList<Node>();
        }
        for(int i=0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s =Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long val = Long.parseLong(st.nextToken());
            info[s].add(new Node(e,val));
            info[e].add(new Node(s,val));
        }
        dist = new long [N+1];
        trace = new long [N+1];
        for (int i = 0; i <=N; i++) {
            dist[i] = Long.MAX_VALUE;
            trace[i] = Long.MAX_VALUE;
        }
        pq= new PriorityQueue<Node>((o1, o2) -> Long.compare(o1.dist, o2.dist));
        ans =0;
    }
    static void dik(){
        pq.offer(new Node(1, 0));
        dist[1] =0; //시작점
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.dest] < curNode.dist) {
                continue;
            }
            for (int i = 0; i < info[curNode.dest].size(); i++) {
                Node nxtNode = (Node) info[curNode.dest].get(i);
                if (dist[nxtNode.dest] > curNode.dist + nxtNode.dist) {
                    dist[nxtNode.dest] = curNode.dist + nxtNode.dist;
                    trace[nxtNode.dest] = nxtNode.dist;
                    pq.offer(new Node(nxtNode.dest, dist[nxtNode.dest]));
                }  else if (dist[nxtNode.dest]  == (curNode.dist + nxtNode.dist)) {
                    trace[nxtNode.dest] = Math.min(trace[nxtNode.dest],nxtNode.dist);
                }
            }
        }

        for(int i=2;i<=N;i++){
            ans+=trace[i];
        }
    }
}
