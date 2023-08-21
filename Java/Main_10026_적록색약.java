import java.util.*;
import java.io.*;

public class Main_10026_적록색약 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static char [][] data;
    static boolean [][] check;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,-1,0,1};
    static int n_ans;
    static int ans;
    static int N;
    public static void main(String[] args) throws Exception{
        input();
        check = new boolean[N][N];
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                if(!check[y][x]){
                    bfs(y,x);
                    n_ans++;
                }
            }
        }
        check = new boolean[N][N];
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                if(data[y][x] == 'R'){
                    data[y][x] = 'G';
                }
            }
        }
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                if(!check[y][x]){
                    bfs(y,x);
                    ans++;
                }
            }
        }
        sb.append(n_ans).append(" ").append(ans);
        System.out.println(sb);

    }
    static void input () throws Exception{
        N = Integer.parseInt(br.readLine());
        data = new char[N][N];
        for(int y=0; y<N; y++){
            String ip = br.readLine();
            for(int x=0; x<N; x++){
                data[y][x] = ip.charAt(x);
            }
        }
    }
    static boolean in_range(int y, int x){
        return 0<=x && x<N && 0<=y &&y<N;
    }
    static void bfs(int y, int x){
        Queue <Integer[] > q = new ArrayDeque<>();
        q.add(new Integer [] {y,x});
        check[y][x] =true;
        char color = data[y][x];
        while(!q.isEmpty()){
            Integer [] cur = q.poll();
            for(int dir=0;dir<4;dir++){
                int cx = cur[1] + dx[dir];
                int cy = cur[0] + dy[dir];
                if(in_range(cy,cx) &&!check[cy][cx] && data[cy][cx]==color){
                    q.add(new Integer[]{cy,cx});
                    check[cy][cx]= true;
                }
            }
        }
    }



}
