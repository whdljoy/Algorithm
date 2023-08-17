import java.util.*;
import java.io.*;
public class Main_17135_캐슬디펜스 {
    static int N;// 행의 수  y
    static int M; // 열의 수 x
    static int D; // 공격거리
    static int [][] origin;
    static boolean [] archer;
    static int ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws  Exception{
        run();
    }

    static void run() throws Exception{
        input();
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        origin = new int [N][M];
        for(int y=0; y<N; y++){
            st = new StringTokenizer( br.readLine());
            for(int x=0; x<M; x++){
                origin[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        ans =0;
        archer = new boolean [M];
    }

//    static void get_position(int num,int idx){
//        if(num ==3){
//            int [] position = new int [3];
//            int cur=0;
//            for(int i=0; i<M; i++ ){
//                if(archer[i]){
//                    position[cur++] =i;
//                }
//            }
//            cal_max(position);
//            return;
//        }
//        for(int i=idx+1; i<M;i++){
//            if(!archer[i]){
//                archer[i]=true;
//                get_position(num+1,i);
//                archer[i]= false;
//            }
//        }
//    }
    static void cal_max(int [] position){
        int [][] cur = copy_origin();
        for(int i=0; i<N;i++){

            cur =move_enemy(cur);
        }
    }

    static int [][] move_enemy(int [][] cur){
        int [][] cp = new int[N][M];
        for(int y=0; y<N-1; y++){
            for(int x=0; x<M; x++){
                cp[y+1][x] = cur[y][x];
            }
        }
        return cp;
    }
    static int[][] copy_origin(){
        int [][] cp = new int[N][M];
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                cp[y][x] = origin[y][x];
            }
        }
        return cp;
    }
}
