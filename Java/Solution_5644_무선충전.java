import java.util.*;
import java.io.*;

/**
 * 테스트 케이스의 첫 번째 줄에는 총 이동 시간(M), BC의 개수(A)가 주어진다.
 * 그 다음 2개의 줄에는 각각 사용자 A와 B의 이동 정보가 주어진다.
 * 한 사용자의 이동 정보는 M개의 숫자로 구성되며, 각각의 숫자는 다음과 같이 매초마다 이동 방향을 의미한다.
 */
public class Solution_5644_무선충전 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int M; // 총이동시간
    static int A ;// BC의 갯수
    static int tc;
    static List <Integer> [][] origin;
    static int [][]root;
    static int ans;
    static int [][] bc;

    static int [][] user;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        tc = Integer.parseInt(br.readLine());
        for(int i=1; i<=tc; i++){
            sb.append("#").append(i).append(" ");
            ans =0;
            input();
            set_data();
        }
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        root = new int [2][M];
        bc = new int [A][4];  //좌표 , c ,p 정보

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M;i++){
            root[0][i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M;i++){
            root[1][i] = Integer.parseInt(st.nextToken());
        }

        for(int y=0; y<A; y++){
            st = new StringTokenizer(br.readLine());
            for(int x =0; x<4; x++){
                bc[y][x] = Integer.parseInt(st.nextToken());
            }
        }

    }
    static void set_data(){

        user = new int[2][2];
        user[0][0] =1;
        user[0][1] =1;
        user[1][0] =10;
        user[1][1] =10;

        origin= new ArrayList [11][11];;
        for(int y=1; y<11;y ++){
            for(int x=1; x<11;x++){
                origin[y][x] = new ArrayList<>();
            }
        }

        for(int i=0;i<A;i++){
            set_origin(i);
        }
    }

    static void set_origin(int idx){  //처음에 영역 표시
        int c = bc[idx][2] -1;
        int sx  =bc[idx][0] - c  ;
        int sy = bc[idx][1] - c;//거리
        int power = bc [idx][3];
        for(int y =sy; y<= bc[idx][1] +c; y++){
            for(int x=sx; x<= bc[idx][0] +c ; x++){
                if(in_range(y,x) && get_dis(x,y,bc[idx][0],bc[idx][1]) == bc[idx][2]){
                    origin[y][x].add(power);
                }
            }

        }

    }
    static int get_dis(int x,int y , int tx, int ty){
        return Math.abs(x-tx) +Math.abs(y-ty);
    }

    static boolean in_range(int x,int y){
        return 1<=x &&x<= 10 && 1<=y && y<=10;
    }

    static void check_cur(){
        int ax = user[0][0];
        int ay = user [0][1];
        int bx = user [1][0];
        int by = user [1][1];
    }
}
