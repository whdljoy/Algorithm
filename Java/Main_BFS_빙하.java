

import java.util.*;
import java.io.*;
public class Main_BFS_빙하 {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int [][] status;
    static boolean [][] visited;
    static int ans=0;
    static List< Integer[]> ice;
    static int time;
    public static void main(String[] args) throws Exception{
        input();
        time=0;
        while(true){
            time ++;
            get_region();
            if(ans !=0){
                break;
            }
        }
        System.out.println(time-1 + " "+ans);
    }

    static void input() throws  Exception{
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        status =new int[N][M];
        for(int y=0; y<N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M;x++){
                status[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static void get_region(){
        visited = new boolean[N][M];
        int cnt=0;
        int ice_cnt=0;
        for(int y=0;y<N;y++){
            for(int x=0;x<M;x++){
                if(!visited[y][x] && status[y][x] ==0){
                    bfs(y,x);
                    cnt++;
                }
            }
        }
        for(int y=0;y<N;y++){
            for(int x=0;x<M;x++){
                if(status[y][x] ==1 ){
                    ice_cnt+=1;
                }
            }
        }
        if(cnt ==1 && ice_cnt == 0){
            ans = ice.size();
        }else{
            ice=new ArrayList<>();
            search(0,0);
            for(Integer [] cur : ice){
                int x = cur[1]; int y= cur[0];
                status[y][x] =0;
            }
        }
    }
    static void search(int y,int x){
        int [] dx= {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        boolean [][] checked = new boolean[N][M];
        checked [y][x] =true;
        Deque <Integer[]> dq = new ArrayDeque<>();
        dq.add(new Integer[] {y,x});
        while(!dq.isEmpty()){
            Integer [] cur =dq.pollFirst();
            int sx = cur[1]; int sy =cur[0];
            for(int dir=0;dir<4;dir++){
                int cx = sx +dx[dir];
                int cy = sy +dy[dir];
                if(!in_range(cy,cx)) continue;
                if(checked[cy][cx]) continue;
                if(status[cy][cx] == 1) {
                    ice.add(new Integer[] {cy,cx});
                    checked[cy][cx]=true;
                    continue;
                }
                checked[cy][cx] =true;
                dq.add(new Integer[] {cy,cx});
            }
        }
    }
    static void bfs(int y,int x){
        int [] dx= {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        visited[y][x] =true;
        Deque <Integer[]> dq = new ArrayDeque<>();
        dq.add(new Integer[] {y,x});
        while(!dq.isEmpty()){
            Integer [] cur =dq.pollFirst();
            int sx = cur[1]; int sy =cur[0];
            for(int dir=0;dir<4;dir++){
                int cx = sx +dx[dir];
                int cy = sy +dy[dir];
                if(!in_range(cy,cx)) continue;
                if(visited[cy][cx]) continue;
                if(status[cy][cx] == 1) continue;
                visited[cy][cx] =true;
                dq.add(new Integer[] {cy,cx});
            }
        }
    }
    static boolean in_range(int y,int x){
        return 0<=y && y< N && 0<=x && x<M;
    }
}
