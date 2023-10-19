import java.util.*;
import java.io.*;
public class Main_BFS_K번최댓값으로이동하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;
    static int [][] status;
    static int sx,sy;
    static boolean end;
    static boolean [][] visited;

    static class Pair  implements Comparable<Pair>{
        int y;
        int x;
        Pair(int y,int x){
            this.y=y;
            this.x=x;
        }
        @Override
        public int compareTo(Pair o1){
            if(this.y != o1.y)
                return Integer.compare(this.y,o1.y);
            else
                return Integer.compare(this.x,o1.x);


        }
    }
    public static void main(String[] args) throws Exception{

        input();
        for(int i=0;i<K;i++){
            bfs(sx,sy);
            if(end){
                break;
            }
        }
        sy+=1;
        sx+=1;
        System.out.println(sy+" "+sx);
    }

    static void input() throws  Exception{
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        status = new int [N][N];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N; x++){
                status[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken()) -1;
        sx = Integer.parseInt(st.nextToken()) -1;
        end=false;
    }
    static void bfs(int nx,int ny){
        int [] dx= {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        visited = new boolean[N][N];
        visited[ny][nx] = true;
        Deque <Integer[]> dq = new ArrayDeque<>();
        dq.add(new Integer[] {ny,nx});
        int num = status[ny][nx];
        int max_num=0;
        while(!dq.isEmpty()){
            Integer [] cur = dq.pollFirst();
            int x = cur[1]; int y= cur[0];
            for(int dir=0;dir<4;dir++){
                int cx = x + dx[dir];
                int cy =  y + dy[dir];
                if(!in_range(cy,cx)) continue;
                if(visited[cy][cx]) continue;
                int c_num = status[cy][cx];
                if(num> c_num){
                    dq.add(new Integer[]{cy,cx});
                    visited[cy][cx] =true;
                    if(max_num <c_num){
                        max_num = c_num;
                    }
                }
            }
        }
        if(max_num ==0){
            end=true;
        }else{
            set_pos(max_num);
        }

    }

    static void set_pos(int max_num){
        PriorityQueue<Pair> search = new PriorityQueue<>();
        for(int y=0;y<N;y++) {
            for(int x=0; x<N;x++){
                if(visited[y][x]&&status[y][x] == max_num){
                    search.add(new Pair(y,x));
                }
            }
        }

        sx = search.peek().x;
        sy = search.peek().y;
    }
    static boolean in_range(int y,int x){
        return 0<=x && x<N && 0<=y && y<N;
    }
}
