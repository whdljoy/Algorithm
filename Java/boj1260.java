import java.util.*;
import java.io.*;
public class boj1260 {
    static ArrayList<Integer>[] info;
    static boolean [] check;
    static StringBuilder bdd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        info = new ArrayList[N+1];
        for(int i =0; i<N+1; i++){
            info[i] = new ArrayList<Integer>();
        }
        check = new boolean[N+1];
        Arrays.fill(check,false);
        for(int i=0; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            info[s].add(e);
            info[e].add(s);
        }
        for(int i=0; i<N+1; i++){
            info[i].sort(Comparator.naturalOrder());
        }
        bdd=new StringBuilder();
        bdd.append(V+" ");
        check[V]=true;
        DFS(V);
        System.out.println(bdd);
        Arrays.fill(check,false);
        BFS(V);
    }

    private static void DFS(int v) {
        for(int i : info[v]){
            if(!check[i]){
                bdd.append(i+" ");
                check[i]=true;
                DFS(i);
            }
        }
        return ;
    }

    private static void BFS(int v) {
        check[v] =true;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        StringBuilder bd =new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            bd.append(cur+" ");
            for (int i: info[cur]){
                if(!check[i]){
                    check[i] = true;
                    q.add(i);
                }
            }
        }
        System.out.println(bd);
    }
}
