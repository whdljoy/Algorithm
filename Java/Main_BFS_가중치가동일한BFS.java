import java.util.*;
import java.io.*;
public class Main_BFS_가중치가동일한BFS {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int [][] status;
    static int ans=0;
    public static void main(String[] args) throws  Exception{
        input();
        bfs();
        if(ans ==0){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        status = new int[N][M];
        for(int y=0;y<N; y++ ){
            st= new StringTokenizer(br.readLine());
            for(int x=0; x<M;x++){
                status[y][x] = Integer.parseInt(st.nextToken());
            }
        }

    }
    static void bfs(){
        int [] dx= {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        boolean [][] visited = new boolean[N][M];
        visited[0][0] = true;
        Deque <Integer[] > dq = new ArrayDeque<>();

        dq.add(new Integer[]{0,0,0});
        while(!dq.isEmpty()){
            Integer [] cur = dq.pollFirst();
            int y = cur[0]; int x= cur[1]; int dis = cur[2];
            for(int dir=0;dir<4;dir++){
                int cy = y +dy[dir];
                int cx = x + dx[dir];
                if(!in_range(cy,cx) || status[cy][cx] ==0 ||visited[cy][cx] ) continue;
                if(cy+1 == N && cx +1 ==M){
                    ans = dis+1;
                    return;
                }
                visited[cy][cx] = true;
                dq.add(new Integer[]{cy,cx,dis+1});
            }
        }
    }
    static boolean in_range(int y, int x){
        return 0<= y && y<N && 0<= x && x<M;
    }
}
