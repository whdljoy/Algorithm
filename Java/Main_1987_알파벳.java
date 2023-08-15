import java.util.*;
import java.io.*;
public class Main_1987_알파벳 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R; // 세로
    static int C;// 가로
    static char [][] origin;
    static Set <Character> data;
    static int ans;
    static int [] dx = {-1,0,1,0};
    static int  [] dy = {0,-1,0,1};
    public static void main(String[] args) throws Exception{
        input();
        ans =0;
        data = new HashSet<>();
        data.add(origin[0][0]);
        dfs(0,0,1);
        System.out.println(ans);
    }

    static void input() throws Exception{

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());

        origin = new char[R][C];

        for(int y=0; y<R; y++){
            String ip = br.readLine();
            for(int x=0; x<C;x++){
                origin[y][x] = ip.charAt(x);
            }

        }
    }

    static boolean in_range(int x,int y){
        return 0<= x && x<C && 0<=y && y<R;
    }
    static void dfs(int x , int y, int depth){
        ans = Math.max(ans,depth);
        for(int dir=0;dir<4;dir++){
            int cx = x+dx[dir];
            int cy = y +dy[dir];
            if(in_range(cx,cy) && !data.contains(origin[cy][cx])){
                data.add(origin[cy][cx]);
                dfs(cx,cy,depth+1);
                data.remove(origin[cy][cx]);
            }
        }
    }
}
