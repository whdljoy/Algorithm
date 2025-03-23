import java.util.*;
import java.io.*;
public class Main_13023_ABCDE {
    static int N;
    static int M;
    static int ans =0;
    static List <Integer> [] graph;
    static boolean[] check;
    static boolean flag;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        input();
        for(int i=0;i<N;i++){
            check = new boolean[N];
            dfs(i,0);
            if(flag){
                ans =1;
                break;
            }
        }
        System.out.println(ans);
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
            graph[e].add(s);
        }
    }
    static void dfs(int idx,int num){
        if(num ==5){
            flag =true;
            return;
        }
        for(int node : graph[idx]){
            if(!check[node]){
                check[node] =true;
                dfs(node,num+1);
                check[node] = false;
            }
            if(flag){
                return;
            }
        }
    }

}
