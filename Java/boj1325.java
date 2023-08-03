import java.util.*;
import java.io.*;

public class boj1325 {
    static int M;
    static int N;
    static List<Integer>[] info;
    static int [] ans;
    static int mv = Integer.MIN_VALUE;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);
        ans = new int[N+1];
        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            bfs(i);
        }
        print_ans();
        br.close();
    }

    public static void input  (BufferedReader br)throws IOException{
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new ArrayList [N+1];
        for(int i=0;i<=N;i++){
            info[i] = new ArrayList<>();
        }
        int first =0;
        int second=0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            first =Integer.parseInt(st.nextToken());
            second =Integer.parseInt(st.nextToken());
            info[first].add(second);
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] =true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int num : info[cur]){
                if(!visited[num]){
                    q.add(num);
                    ans[num] ++;
                    visited[num] = true;
                }
            }
        }
    }

    public static void print_ans(){

        mv = Arrays.stream(ans).max().getAsInt();

        StringBuilder sb =new StringBuilder();
        for(int idx =1; idx<=N;idx++){
            if (ans[idx]==mv){
                sb.append(idx).append(" ");
            }
        }
        System.out.println(sb);

    }

}
