import java.util.*;
import java.io.*;
public class Main_21610_마법사상어와비바라기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
    static int N;
    static int M;
    //←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int [] dx = {0,-1,-1,0,1,1,1,0,-1};
    static int [] dy = {0,0,-1,-1,-1,0,1,1,1};
    static int [][] origin;
    static boolean [][] cloud; //처음 구름
    static boolean [][] m_cloud; // 복사구름

    static int [][] move;
    public static void main(String[] args)  throws  Exception{
        run();
    }
    static void run() throws Exception{
        input();
        for(int i=0; i<M; i++){
            int d = move[i][0];
            int s = move[i][1];
            execute_move(d,s);
            increase();
            make_cloud();
        }
        print_ans();
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        origin = new int[N][N];
        cloud = new boolean [N][N];
        cloud[N-1][0] =true;  //초기 구름셋팅
        cloud[N-1][1] = true;
        cloud[N-2][0] = true;
        cloud[N-2][1] = true ;
        move = new int[M][2];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N; x++){
                origin[y][x] =Integer.parseInt(st.nextToken()); // 초기 바구니 상태;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<2;j++){
                move[i][j] = Integer.parseInt(st.nextToken());   //이동명령
            }
        }

    }

    static void execute_move(int d, int s){
        m_cloud = new boolean[N][N];
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                if(cloud[y][x]){
                    change_cloud(y,x,d,s);
                }
            }
        }

        // 구름 복사 과정;
        copy_cloud();
    }

    static void change_cloud(int y,int x,int d,int s){
        int cx = x + dx[d] * s % N;
        int cy = y + dy[d] * s % N;
        //←, ↖, ↑, ↗, →, ↘, ↓, ↙
        switch (d){
            case 1:
                if(cx < 0) cx = N + cx;
                break;
            case 2:
                if(cx < 0) cx = N + cx;
                if(cy < 0) cy = N + cy;
                break;
            case 3:
                if(cy < 0) cy = N + cy;
                break;
            case 4:
                if(cx > N-1) cx = cx -N;
                if(cy < 0) cy = N +  cy;
                break;
            case 5:
                if(cx > N-1) cx = cx -N;
                break;
            case 6:
                if(cx > N-1) cx = cx -N;
                if(cy > N-1) cy = cy -N;
                break;
            case 7:
                if(cy > N-1) cy =cy -N;
                break;
            case 8:
                if(cx < 0) cx = N + cx;
                if(cy > N-1) cy = cy -N;
                break;
        }
        //구름 이동
        m_cloud[cy][cx] =true;
    }

    static void increase(){
        for(int y=0; y<N;y++ ){
            for(int x=0;x<N; x++){
                if(cloud[y][x]){
                    origin[y][x] +=1;
                }
            }
        }
        for(int y=0; y<N; y++ ){
            for(int x=0;x<N; x++){
                if(cloud[y][x]){
                    cross_increase(y,x);
                }
            }
        }
    }
    static void cross_increase(int y,int x){
        int [] dir_x ={-1,1,-1,1};
        int [] dir_y ={-1,-1,1,1};
        int add =0;
        for(int dir=0;dir<4;dir++){
            int cx = x+dir_x[dir];
            int cy = y+dir_y[dir];
            if(in_range(cy,cx) && origin[cy][cx] > 0){
                add+=1;
            }
        }

        origin[y][x] += add;
    }
    static boolean in_range(int y,int x){
        return 0<=x && x<N && 0<=y && y < N;
    }

    static void copy_cloud(){
        for(int y=0;y<N;y++){
            for(int x=0; x<N; x++){
                cloud[y][x] =m_cloud[y][x];
            }
        }
    }


    static void make_cloud(){
        m_cloud = new boolean[N][N];
        for(int y=0; y<N;y++){
            for(int x=0;x<N; x++){
                if(origin[y][x]>=2 && !cloud[y][x]){
                    m_cloud[y][x] =true;
                    origin[y][x]-=2;
                }
            }
        }
        copy_cloud();

    }

    static void print_ans(){
        int ans=0;
        for(int y=0;y<N;y++) {
            for(int x=0;x<N;x++){
                ans +=origin[y][x];
            }
        }
        System.out.println(ans);
    }
}
/**
 * 모든 구름이 di 방향으로 si칸 이동한다.
 * 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 구름이 모두 사라진다.
 * 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 * 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 * 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다
 */
