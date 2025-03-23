import java.util.*;
import java.io.*;
public class Main_BFS_나이트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [][] status;
    static int sx,sy,ex,ey;
    static int ans=0;
    public static void main(String[] args) throws  Exception{
        input();
        bfs();
        if(ans ==0 && !(sy== ey && sx == ex)){
            ans =-1;
        }
        System.out.println(ans);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        status = new int [N][N];
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1 ;
        ey = Integer.parseInt(st.nextToken()) - 1 ;
        ex = Integer.parseInt(st.nextToken()) - 1;


    }
    static void bfs(){
        int [] dx ={-2,-2,2,2,1,-1,1,-1};
        int [] dy = {1,-1,1,-1,2,2,-2,-2};
        boolean [][] visited = new boolean[N][N];
        visited[sy][sx] = true;
        Deque <Integer[] > dq = new ArrayDeque<>();
        dq.add(new Integer[]{sy,sx,0});
        while(!dq.isEmpty()){
            Integer [] cur =dq.pollFirst();
            int y = cur[0]; int x = cur[1]; int dis = cur[2];
            for(int dir=0; dir<8;dir++){
                int cy = y + dy[dir];
                int cx = x + dx[dir];
                if(!in_range(cy,cx) || visited[cy][cx]) continue;

                if(cy == ey && cx == ex){
                    ans = dis+1;
                    return;
                }
                visited[cy][cx] =true;
                dq.add(new Integer[]{cy,cx,dis+1});
            }
        }
    }
    static boolean in_range(int cy, int cx){
        return 0<= cy && cy<N && 0<=cx && cx<N;
    }
}
