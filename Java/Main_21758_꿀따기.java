import java.util.*;
import java.io.*;

public class Main_21758_꿀따기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [] origin;
    static int [] total_num;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        input();
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        origin = new int [N];
        total_num = new int [N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            origin[i] = Integer.parseInt(st.nextToken());
        }
        total_num[0] = origin[0];
        for(int i=1;i<N;i++){
            total_num[i] = origin[i] +total_num[i-1];
        }
    }
    static void between(){ // 벌 통 벌 있을때

    }
}
13
3 1개
        7개
        3 4
4 8 17 18 27 31 35
14 + 17
17
9 18  22 23 27 36 45
45 36 27 23 22 18 9

94149
94149

13 14 18 27

13
18