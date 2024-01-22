import java.util.*;
import java.io.*;
public class Solution_보급로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N;
    static int [][] info;
    static int ans;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            bfs();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        info = new int [N][N];
        for(int y=0; y<N;y++){
            String str = br.readLine();
            for(int x=0; x<N ;x++){
                info[y][x] = str.charAt(x)-'0';
            }
        }
        ans= 0;
    }

    static void bfs(){
        int [] dx  = {0,1,0,-1};
        int [] dy = {1,0,-1,0};
        int [][] visited = new int [N][N];
        for(int i=0;i<N;i++){
            for(int j=0; j<N;j++){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        Deque <Integer []> dq = new ArrayDeque<>();
        dq.add(new Integer [] {0,0,info[0][0]}); //y, x, val;
        while(!dq.isEmpty()){
            Integer [] cur = dq.pollFirst();
            int y = cur[0]; int x = cur[1]; int val = cur[2];

            for(int i=0; i<4;i++){
                int cx = x +  dx[i];
                int cy = y + dy[i];
                if(!in_range(cy,cx)) continue;
                if(visited[cy][cx] <= (info[cy][cx] +val)) continue;

                dq.add(new Integer[] {cy,cx,val+info[cy][cx]});
                visited[cy][cx] = info[cy][cx] + val;
            }
        }
        ans =visited[N-1][N-1];
    }
    static boolean in_range(int y, int x){
        return -1<x && x<N && -1<y && y<N;
    }
}
