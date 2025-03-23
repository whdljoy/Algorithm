import java.util.*;
import java.io.*;
public class Solution_1247_최적경로 {
    static int tc;
    static StringBuilder sb = new StringBuilder();
    static int [][] info;
    static int N;
    static int ans;
    static boolean []visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        run();
    }
    static void run() throws Exception{
        tc = Integer.parseInt(br.readLine());
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            ans =Integer.MAX_VALUE;
            dfs(0,0,0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    // 회사 집 나머지 N개 좌표  회사에서 출발해서 모두 방문하고 집으로
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        info = new int[N+2][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int y=0; y<N+2; y++){
            for(int x=0;x<2;x++){
                info[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N+2];
    }
    static int get_distance(int x,int y ,int tx, int ty){
        return Math.abs(x-tx) + Math.abs(y-ty);
    }

    static void dfs(int num,int cur,int distance){
        if(num == N){
            distance+=get_distance(info[cur][0],info[cur][1],info[1][0],info[1][1]);
            ans =Math.min(ans,distance);
            return ;
        }
        for(int idx=2; idx<N+2; idx++){
            if(!visited[idx]){
                visited[idx]= true;
                int dis =get_distance(info[cur][0],info[cur][1],info[idx][0],info[idx][1]);
                distance += dis;
                dfs(num+1,idx,distance);
                visited[idx]=false;
                distance -=dis;
            }
        }
    }
}
