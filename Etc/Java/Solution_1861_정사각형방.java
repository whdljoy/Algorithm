import java.util.*;
import java.io.*;
public class Solution_1861_정사각형방 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb =new StringBuilder();
    static int tc;
    static int N;
    static int [][] info;
    static int [] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int ans;
    static int room;
    public static void main(String[] args) throws  Exception{
        run();

    }

    public static void run() throws  Exception{
        tc= Integer.parseInt(br.readLine());
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            ans =0;
            room = Integer.MAX_VALUE;
            cal();
            sb.append(room).append(" ").append(ans).append("\n");
        }
        print();
    }
    public static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        info = new int[N][N];
        for(int y=0; y<N;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N;x++){
                info[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void cal(){
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                bfs(y,x);
            }
        }
    }
    public static boolean in_range(int y, int x){
        return 0<=y && y<N && 0<= x && x<N;
    }

    public static void bfs(int y, int x){
        boolean [][] check = new boolean[N][N];
        Queue<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{y,x});
        check[y][x] = true;
        int count =1;
        while(!q.isEmpty()){
            Integer[] cur = q.poll();
            int num = info[cur[0]][cur[1]];
            for(int dir=0; dir<4; dir++){
                int cx = cur[1] + dx[dir];
                int cy = cur[0] + dy[dir];
                if(in_range(cy,cx) && !check[cy][cx] && info[cy][cx]==(num+1)){
                    q.add(new Integer[]{cy,cx});
                    check[cy][cx] = true;
                    count++;
                }
            }
        }
        if(ans < count){
            ans = count;
            room = info[y][x];
        }
        else if (ans ==count){
            if(room >info[y][x]){
                room = info[y][x];
            }
        }
    }
    static void print()throws Exception{
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
