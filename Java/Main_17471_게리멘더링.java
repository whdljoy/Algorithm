
import java.util.*;
import java.io.*;

public class Main_17471_게리멘더링 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int [] people;
    static boolean[] check;
    static int ans =Integer.MAX_VALUE;
    static boolean [] visited;

    static List <Integer> [] graph;
    public static void main(String[] args) throws Exception{
        input();
        for(int idx =1;idx<=N; idx++){
            check =new boolean[N+1];
            dfs(1,idx);
        }
        if(ans==Integer.MAX_VALUE){
            ans=-1;
        }
        System.out.println(ans);

    }
    static void input () throws Exception{
        N = Integer.parseInt(br.readLine());
        people = new int [N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[N+1];
        for(int i=0;i<= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=1; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int idx =0; idx<num;idx++){
                int e = Integer.parseInt(st.nextToken());
                graph[i].add(e);
                graph[e].add(i);
            }
        }

    }

    static void dfs(int idx,int num){
        if(num == idx){
            if(can_region()){
                cal_min();
            }
        }
        for(int i=idx; i<N ;i++){
            if(!check[i]){
                check[i]=true;
                dfs(i+1,num+1);
                check[i]=false;
            }
        }
    }

    static boolean can_region(){
        int region=0;
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                bfs(i);
                region++;
            }
        }
        if( region ==2) return true;
        return false;
    }

    static void bfs(int start){
        Queue <Integer> q = new ArrayDeque<>();
        q.add(start);
        visited [start] =true;
        boolean flag = check[start];
        while(!q.isEmpty()){
            int cur = q.poll();
            for( int node :graph[cur]){
                if(!visited[node] && check[node] == flag){
                    q.add(node);
                    visited[node]=true;
                }
            }
        }

    }
    static void cal_min(){
        int pa= 0;
        int pb= 0;
        for(int i=1;i<=N; i++){
            if(check[i]){
                pa+=people[i];
            }else{
                pb+=people[i];
            }
        }
        ans =Math.min(ans,Math.abs(pa-pb));
    }

}
