import java.util.*;
import java.io.*;
public class Main_17143_낚시왕 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R;
    static int C;
    static int M;
    static int king_x;
    static int ans;
    static Shark [][] moving;
    static int [] dx = {0,0,0,1,-1};
    static int [] dy ={0,-1,1,0,0};
    static Shark [][] origin;

    static class Shark {
        boolean check = false;
        int s; // 속력
        int d; //방향
        int z; // 크기

        Shark(boolean check,int s,int d, int z){
            this.check = check;
            this.s =s;
            this.d=d;
            this.z =z;
        }

    }

    public static void main(String[] args) throws Exception {
        run();
    }
    static void run () throws Exception{
        input();
        while(true){
            move_king();  //낚시왕 옮기기
            print();
            if(king_x == C+1){
                break;
            }
            kill_shark();  //해당 열 상어 삭제
            move_shark(); // 상어 이동
        }
        System.out.println(ans);
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        king_x =0;
        R =Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new Shark[R+1][C+1];
        for(int y=0; y<=R;y++){
            for(int x=0;x<=C;x++){
                origin[y][x] = new Shark(false,0,0,0);
            }
        }
        for(int i=0; i< M ; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            origin[y][x] =new Shark(true,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

    }
    static void move_king(){
        king_x+=1;
    }
    static void kill_shark(){
        for(int y=1;y<=R;y++){   //king_x 열에서 삭제
            //System.out.println(origin[y][king_x].check);
            if(origin[y][king_x].check){
                ans+= origin[y][king_x].z;
                origin[y][king_x] = new Shark(false,0,0,0);
                return ;
            }
        }
    }

    static void move_shark(){
        moving = new Shark[R+1][C+1];
        for(int y=0; y<=R;y++){
            for(int x=0;x<=C;x++){
                moving[y][x] = new Shark(false,0,0,0);
            }
        }
        for(int y=1;y<=R;y++){
            for(int x=1;x<=C;x++){
                if(origin[y][x].check){
                    change_position(origin[y][x],y,x);
                }
            }
        }
        origin = new Shark[R+1][C+1];
        for(int y=0; y<=R;y++){
            for(int x=0;x<=C;x++){
                origin[y][x] = moving[y][x];
            }
        }

    }
    static void change_position(Shark current,int y, int x){  //위, 아래 오른쪽 왼쪽
        int direction = current.d;
        int distance = current.s;
        int cx =x;
        int cy =y;
        if(direction == 1 ){  //위
            //구간나누기
            distance = distance % (2*R-2);  // 제자리
            while(true){
                if(distance ==0){
                    break;
                }
                if(cy == 1){
                    direction = 2;
                }else if(cy == R){
                    direction = 1;
                }
                distance --;
                cy += dy[direction];
            }

        }else if (direction ==2){ //아래
            distance = distance % (2*R -2);
            while(true) {
                if (distance == 0) {
                    break;
                }
                if (cy == 1) {
                    direction = 2;
                } else if (cy == R) {
                    direction = 1;
                }
                distance--;
                cy += dy[direction];
            }
        }else if(direction ==3){ //오른쪽
            distance = distance % (2*C -2);
            while(true) {
                if (distance == 0) {
                    break;
                }
                if (cx == 1) {
                    direction = 3;
                } else if (cx == C) {
                    direction = 4;
                }
                distance--;
                cx += dx[direction];
            }
        }else{//왼쪽
            distance = distance % (2*C -2);
            while(true) {
                if (distance == 0) {
                    break;
                }
                if (cx == 1) {
                    direction = 3;
                } else if (cx == C) {
                    direction = 4;
                }
                distance--;
                cx += dx[direction];
            }
        }
        current.d = direction;
        eat_shark(cx,cy,current);

    }
    static void eat_shark(int x,int y,Shark mv){  //해당 칸에 상어가 두마리면 삭제
        if(moving[y][x].check){
            if(moving[y][x].z <mv.z){
                moving[y][x] = mv;
            }
        }else{
            moving[y][x] = mv;
        }
    }

    private static void print() {
        for(int y=1; y<=R;y++){
            for(int x=1;x<=C;x++){
                if(origin[y][x].check){
                    System.out.println(y + "" +x +"" +origin[y][x].s+" "+origin[y][x].d + " "+origin[y][x].z);
                }
            }
        }
        System.out.println();
    }
}
