import java.util.*;
import java.io.*;
public class Main_Simulation_2차원바람 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int Q;
    static int [][] status;
    static int [][] wind;
    public static void main(String[] args) throws Exception{
        input();
    }
    static void run(){
        for(int i=0;i<Q;i++){
            change(i);
        }
    }
    static void change(int turn){
        int sy= wind[turn][0]; int sx= wind[turn][1]; int ey= wind[turn][2]; int ex= wind[turn][3];
        int [][] tmp = new int [N][M];
        int [] dx = {-1,0,1,0};
        int [] dy = {0,-1,0,1};
        for(int y = sy; y<=ey;y++){
            for(int x=sx; x<=ex;x++){
                int num = status[y][x];
                int cnt =1;
                for(int dir=0;dir<4;dir++){
                    int cy= y +dy[dir];
                    int cx =x +dx[dir];
                    if(!in_range(cy,cx)) continue;
                    num+=status[cy][cx];
                    cnt ++;
                }
                tmp[y][x] = num/cnt;
            }
        }

        rotate(turn,tmp);
    }

    static void rotate(int turn, int[][] tmp){
        
    }

    static boolean in_range(int y,int x){
        return 0<= x  && x<M && 0<=y && y<N;
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        status = new int[N][M];

        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<M;x++){
                status[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        wind = new int[Q][4];
        /**
         * /r1, c1, r2, c2)가 공백을 사이에 두고 주어집니다.
         * (r1, c1)은 바람에 영향을 받는 직사각형의 좌측 상단의 위치이며
         * (r2, c2)는 바람에 영향을 받는 직사각형의 우측 하단의 위치
         */
        for(int y=0; y<Q;y ++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<4;x++){
                wind[y][x] = Integer.parseInt(st.nextToken())-1;
            }
        }

    }
}
