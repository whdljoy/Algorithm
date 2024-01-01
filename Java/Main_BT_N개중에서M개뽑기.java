import java.util.*;
import java.io.*;
public class Main_BT_N개중에서M개뽑기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static int N;
    static int M;
    static int [] status;
    public static void main(String[] args) throws  Exception{
        input();
        select(0,1);
        System.out.println(sb);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        status = new int[M];

    }

    static void select(int num,int idx){
        if(num ==M){
            for(int i=0;i<M;i++){
                sb.append(status[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=idx;i<=N;i++){
            status[num] = i;
            select(num+1,i+1);
        }
    }
}
