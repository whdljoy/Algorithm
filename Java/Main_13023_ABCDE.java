import java.util.*;
import java.io.*;
public class Main_13023_ABCDE {
    static int N;
    static int M;
    static int ans =0;
    static List <Integer> [] graph;
    static boolean[] check;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        input();
        for(int i=0;i<N;i++){
            if(!check[i] && !graph[i].isEmpty()){
                ans =1;
                bfs(i);
            }
        }
        if(ans >=5){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0; i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
        }
        check = new boolean[N];
    }
    static void bfs(int num){
        check [num]= true;
        Queue <Integer> q = new ArrayDeque<>();
        q.add(num);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int node : graph[cur]){
                if(!check[node]){
                    q.add(node);
                    check[node]=true;
                    ans++;
                }
            }
        }
    }

}
