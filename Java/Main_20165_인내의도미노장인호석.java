import java.util.*;
import java.io.*;
public class Main_20165_인내의도미노장인호석 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N,M,R;
    static int [][] status;
    static boolean [][] fallen;
    static int ans =0;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {

            input();
            simulate();
            sb.append(ans);

        System.out.print(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 라운드횟수
        status = new int [N+1][M+1];
        fallen = new boolean [N+1][M+1];
        for(int y=1;y<=N;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=1;x<M;x++){
                status[y][x] = Integer.parseInt(st.nextToken());
            }
        }

    }
    static void simulate() throws Exception{
        for(int i=0; i< R*2;i++){
            //공격
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            move(y,x,direction);

            // 수비
            st= new StringTokenizer(br.readLine());
            int de_y  = Integer.parseInt(st.nextToken());
            int de_x = Integer.parseInt(st.nextToken());
            if(!fallen[de_y][de_x]){
                fallen[de_y][de_x]= false;
            }
        }
    }
    static void move(int y,int x, char direction){
        int dir =-1;
        switch (direction){
            case 'E':
                dir=0;
                break;
            case 'S':
                dir=1;
                break;
            case 'N':
                dir=2;
                break;
            case 'W':
                dir=4;
                break;
        }
        int [] dx = {1,0,0,-1};
        int [] dy = {0,1,-1,0};
        int dis = status[y][x];
        int dis_x = x + dis * dx[dir];
        int dis_y = y + dis * dy[dir];
        int sy = y;
        while(true){
            if(dy[dir] ==0){
                break;
            }
            if(in_range(x,sy)){
                if(sy <= dis_y && !fallen[sy][x]){
                    fallen[sy][x] = true;
                    ans +=1;
                    if(dis_y < (sy +status[sy][x])){
                        dis_y = sy +status[sy][x];
                    }
                }
            }
            sy += dy[dir];
            if(sy > N|| sy< 1 || sy>dis_y ){
                break;
            }
        }
        int sx =x;
        while(true){
            if(dx[dir]==0){
                break;
            }
           System.out.println(y+ " " + x+ " " +sx+ "   " +dis_x + " " +dx[dir] + " " +dis);
            if(in_range(sx,y)){
                if(sx <= dis_x && !fallen[y][sx]){
                    fallen[y][sx] = true;
                    ans +=1;
                    if(dis_x < (sx +status[y][sx])){
                        dis_x = sx +status[y][sx];
                    }
                }
            }
            sx = sx + dx[dir];
            if(sx > M|| sx< 1 || sx > dis_x ){
                break;
            }
        }


    }
    static boolean in_range(int x,int y){
        return 1<= x&& x<=M && 1<=y && y<=N;
    }


}
