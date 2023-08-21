import java.util.*;
import java.io.*;
public class Main_15683_감시 {
    static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int [][] origin;
    public static void main(String[] args) throws Exception{
        input();
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new int [N][M];

        for(int y=0; y<N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                origin[y][x] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
