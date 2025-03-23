import java.util.*;
import java.io.*;
public class Main_1992_쿼드트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [][] origin;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        input();
        cal(N,1,1);
        System.out.println(sb);
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        origin = new int [N+1][N+1];
        for(int y=1;y<=N; y++){
            String ip = br.readLine();
            for(int x=0;x<N;x++){
                origin[y][x+1] = ip.charAt(x) - '0';
            }
        }

    }
    static void cal(int size, int sx,int sy){
        if (possible(size,sx,sy)){
            sb.append(origin[sy][sx]);
        }else{
            sb.append("(");
            cal(size/2, sx,sy);//1사분면
            cal(size/2, sx+size/2 ,sy);//2사분면
            cal(size/2, sx,sy+size/2); //3사분면
            cal(size/2,sx+size/2,sy+size/2); //4사분면
            sb.append(")");
        }
    }

    static boolean possible(int size, int sx, int sy){
        for(int y=sy; y<sy+size ; y++ ){
            for(int x=sx; x<sx+size; x++){
                if(origin[sy][sx] != origin[y][x]){
                    return false;
                }
            }
        }
        return true;
    }
}
