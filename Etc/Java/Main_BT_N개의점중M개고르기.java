import java.util.*;
import java.io.*;
public class Main_BT_N개의점중M개고르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int [][] status;
    static int [] select;
    static double ans=Double.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        input();
        bt(0,0);
        System.out.println(Math.round(Math.pow(ans,2)));
    }
    static void input() throws Exception{
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        status = new int[N][2];
        select = new int[M];
        for(int i=0;i<N;i++){
            st =new StringTokenizer(br.readLine());
            status[i][0] = Integer.parseInt(st.nextToken()); //x
            status[i][1] = Integer.parseInt(st.nextToken()); //y
        }
    }

    static void bt(int num,int idx){
        if(num ==M){
            cal();
            return;
        }

        for(int i =idx; i<N;i++){
            select[num] = i;
            bt(num+1,i+1);
        }
    }
    static void cal(){
        double cur =0;
        for(int i=0;i<M;i++){
            int current = select[i];
            int cx = status[current][1]; int cy= status[current][0];
            for(int j=i+1;j<M;j++){
                int se = select[j];
                int sx = status[se][1]; int sy= status[se][0];
                cur = Math.max(cur,Math.sqrt(Math.pow(cx-sx,2)+ Math.pow(cy-sy,2)));
            }
        }
        ans=Math.min(ans,cur);
    }
}
