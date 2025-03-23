import java.util.*;
import java.io.*;

public class Solution_줄세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static List<Integer>[] graph;
    static int[] inDegree;
    static int N,M;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            TopologicalSort();
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree=new int [N+1];
        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=0; i< M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            inDegree[e]+=1;
        }
    }
    static void TopologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i < inDegree.length; i++) {
            if(inDegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");
            for(int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if(--inDegree[next] == 0) q.offer(next);
            }
        }

    }
}
