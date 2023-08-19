import java.util.*;
import java.io.*;
public class Main_17135_캐슬디펜스 {
    static int N;// 행의 수  y
    static int M; // 열의 수 x
    static int D; // 공격거리
    static int [][] origin;
    static boolean [] archer;
    static final int M_ARCHER =3;
    static int ans;
    static int current_turn;
    static int [][] cur_castle;
    static int [][] check_enemy;
    static int [] dx = {-1,0,1,0};
    static int [] dy ={0,-1,0,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws  Exception{
        run();
    }

    static void run() throws Exception{
        input();
        get_position(0,-1);
        System.out.println(ans);
    }
    static void input() throws Exception{   //입력
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        origin = new int [N][M];
        for(int y=0; y<N; y++){
            st = new StringTokenizer( br.readLine());
            for(int x=0; x<M; x++){
                origin[y][x] = Integer.parseInt(st.nextToken());  //초기 정보 origin에 저장
            }
        }

        ans =0;
        archer = new boolean [M];  //궁수의 위치 값 표시할 배열
    }

    static void get_position(int num,int idx){
        if(num ==3){
            int [] position = new int [M_ARCHER];
            int cur=0;
            for(int i=0; i<M; i++ ){
                if(archer[i]){
                    position[cur++] =i;  //궁수의 위치 포지션 index 값 구하기
                }
            }
            cal_max(position);  //그 위치 일때 잡을 수 있는 적 계산
            return;
        }
        for(int i=idx+1; i<M;i++){   //재귀를 통해 얻을  수 있는 궁수의 위치 경우 모두 구하기
            if(!archer[i]){
                archer[i]=true;
                get_position(num+1,i);
                archer[i]= false;
            }
        }
    }
    static void cal_max(int [] position){
        copy_origin(); //그 궁수 위치일때 처음 상태 들고오기
        current_turn=0;
        for(int i=0; i<N;i++){
            check_enemy = new int [N][M];
            for(int a=0; a< M_ARCHER ; a++){
                bfs(position[a]);    // 각포지션에서 거리에서 잡을 수 있는 적확인
            }
            cal_turn();
            move_enemy();
        }
        ans =Math.max(current_turn,ans);
    }
    static void cal_turn(){  //각 턴에 동시에 화살을 쏘기 떄문에 턴마다 쏘는 곳을 저장한후 적이 있고 쏜 곳을 확인하여 죽인 적 계산
        for(int y=0; y<N;y++){
            for(int x=0; x<M; x++){
                if(cur_castle[y][x] ==1 && check_enemy[y][x] >0){
                    current_turn+=1;
                    cur_castle[y][x]=0;
                }
            }
        }
    }
    static void bfs(int x_position){
        Queue <Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{N,x_position}); // y x
        boolean [][] check = new boolean[N][M];
        while(!q.isEmpty()){
            Integer [] cur = q.poll();
            for(int dir=0;dir<4;dir++){
                int cx = cur[1] + dx[dir];
                int cy = cur[0] + dy[dir];
                if(in_range(cx,cy) && !check[cy][cx] && get_dis(cx,cy,x_position) <=D){
                    q.add(new Integer[]{cy,cx});
                    check[cy][cx] = true;
                    if(cur_castle[cy][cx] ==1){
                        check_enemy[cy][cx] +=1;
                        return;
                    }
                }
            }
        }
    }
    static boolean in_range(int x,int y){
        return 0<=x && x<M &&0<=y && y<N;
    }
    static int get_dis(int x,int y, int tx){
        return Math.abs(x-tx) + Math.abs(y-N);
    }
    static void move_enemy(){
        int [][] cp = new int[N][M];
        for(int y=0; y<N-1; y++){
            for(int x=0; x<M; x++){
                cp[y+1][x] = cur_castle[y][x];
            }
        }
        cur_castle = cp;
    }
    static void copy_origin(){
        int [][] cp = new int[N][M];
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                cp[y][x] = origin[y][x];
            }
        }
        cur_castle =cp;
    }
}
