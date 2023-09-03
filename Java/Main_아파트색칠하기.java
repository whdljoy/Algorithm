import java.util.*;
import java.io.*;
public class Main_아파트색칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] color;
    static int N;
    public static void main(String[] args) throws Exception{
        input();
        cal();
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        color = new int[N+1][2];
        color[1][0] =1;
        color[1][1] =1;
    }
    static void cal(){
        for(int i=2; i<=N;i++) {
            color[i][0] = color[i-1][0] +color[i-1][1];
            color[i][1] = color[i-1][0];
        }
        System.out.println(color[N][0]+color[N][1]);
    }
}
