import java.io.*;
import java.util.*;

public class Main_1012_유기농배추 {
    static boolean [][] visited;
    static int [][] info;
    static int M;
    static int N;
    static int K;
    static int [] dx ={-1,0,1,0};
    static int [] dy ={0,-1,0,1};
    public static class Cur{
        int x;
        int y;
        Cur(int y,int x){
            this.x = x;
            this.y =y;
        }
    }
    static int ans;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(str.nextToken());
        for(int i=1; i<=tc; i++){
            str = new StringTokenizer(br.readLine());
            M = Integer.parseInt(str.nextToken());
            N = Integer.parseInt(str.nextToken());
            K = Integer.parseInt(str.nextToken());
            info = new int [N][M];
            visited = new boolean[N][M];
            for(int c=0;c<K;c++){
                str = new StringTokenizer(br.readLine());
                int x =Integer.parseInt(str.nextToken());
                int y =Integer.parseInt(str.nextToken());
                info[y][x]=1;
            }
            ans=0;
            for(int y=0;y<N;y++){
                for(int x=0;x<M;x++){
                    if(!visited[y][x] && info[y][x]==1){
                        bfs(y,x);
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static void bfs(int y,int x){
        visited[y][x] =true;
        Queue <Cur> q = new LinkedList<>();
        q.add(new Cur(y,x));
        while(!q.isEmpty()){
            Cur th = q.peek();
            q.poll();
            for(int i=0;i<4;i++){
                int cur_x = th.x +dx[i];
                int cur_y = th.y +dy[i];
                if(0<= cur_x && cur_x<M &&0<=cur_y && cur_y<N && info[cur_y][cur_x]==1&& !visited[cur_y][cur_x]){
                    visited[cur_y][cur_x] =true;
                    q.add(new Cur(cur_y,cur_x));
                }
            }

        }
    }
}