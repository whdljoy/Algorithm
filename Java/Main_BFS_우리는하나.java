import java.util.*;
import java.io.*;
public class Main_BFS_우리는하나 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,K,U,D;
    static int [][] status;
    static int [] city;
    static boolean [] visited;
    static int ans=0;
    public static void main(String[] args) throws Exception{
        input();
        search_k(0,0);
        System.out.println(ans);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        status=new int[N][N];
        city = new int[N*N];
        visited=new boolean[N*N];
        int idx=0;
        for (int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N; x++ ){
                status[y][x] =Integer.parseInt(st.nextToken());
                city[idx++] = status[y][x];
            }
        }

    }

    static void search_k(int num,int idx){
        if(num ==K){
            get_city();
            return;
        }
        for(int i=idx;i<N*N;i++){
            if(!visited[i]){
                visited[i]=true;
                search_k(num+1,i+1);
                visited[i]=false;
            }
        }
    }

    static void get_city(){
        int [] dx= {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        boolean [][] checked = new boolean[N][N];
        Deque <Integer [] > dq = new ArrayDeque<>();
        for(int i=0;i<N*N;i++){
            if(visited[i]){
                int y = i /N;
                int x=  i%N;
                checked[y][x] = true;
                dq.add(new Integer[]{y,x});
            }
        }
        while(!dq.isEmpty()){
            Integer [] cur = dq.pollFirst();
            int x = cur[1]; int y = cur[0];
            for(int dir=0; dir<4;dir++){
                int cx = x +dx[dir];
                int cy = y+ dy[dir];
                if(!in_range(cy,cx)) continue;
                if (checked[cy][cx]) continue;
                int num = Math.abs(status[cy][cx] - status[y][x]);
                if(U<= num && num <=D){
                    checked[cy][cx] = true;
                    dq.add(new Integer[] {cy,cx});
                }
            }
        }
        int cnt=0;
        for(int y=0; y<N;y++){
            for(int x=0; x<N; x++){
                if(checked[y][x]) cnt++;
            }
        }
        ans =Math.max(ans,cnt);
    }
    static boolean in_range(int y, int x){
        return 0<= x&& x<N && 0<=y && y<N;
    }
}
