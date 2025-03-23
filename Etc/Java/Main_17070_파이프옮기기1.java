import java.util.*;
import java.io.*;
public class Main_17070_파이프옮기기1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] map;
    static final int MAX_N =18;
    static int result;
    static int N;
    public static void main(String[] args) throws Exception{
        input();
        dfs(1,1,0);
        System.out.println(result);
    }

    static void input() throws Exception{
        N =Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int [MAX_N][MAX_N];
        for(int x=0; x<N; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<N; y++ ){
                map[y][x]  = Integer.parseInt(st.nextToken());
            }
        }
    }
    static void  dfs(int typ, int x, int y){
        // 유효성 확인
        if(map[x][y] == 1|| x > N-1 || y > N-1){
            return ;
        }
        if(typ==2 && (map[x][y-1]==1||map[x-1][y]==1)){
            return ;
        }
        // 도착 확인
        if(x==N-1 && y==N-1){
            result=result+1;
        }
        if(typ!=1) 	dfs(3,x,y+1); //세로
        dfs(2,x+1,y+1); //대각선
        if(typ!=3)	dfs(1,x+1,y); //가로

    }
}
