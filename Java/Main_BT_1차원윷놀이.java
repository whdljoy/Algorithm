import java.util.*;
import java.io.*;
public class Main_BT_1차원윷놀이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int K;
    static int [] turn;
    static int [] move;
    static int ans=0;
    public static void main(String[] args) throws Exception{
        input();
        bt(0);
        System.out.println(ans);
    }

    static void input() throws Exception{
       StringTokenizer st= new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
       K = Integer.parseInt(st.nextToken());
       st = new StringTokenizer( br.readLine());
       turn = new int[N];
       move = new int[N];
       for(int i=0; i<N;i++){
           turn[i]= Integer.parseInt(st.nextToken());
       }
    }
    static void bt(int num){
        if(num == N){
            cal();
            return;

        }
        for(int i=0; i<K;i++){
            move[num]=i;
            bt(num+1);
        }
    }
    static void cal(){
        int [] status = new int[K];
        for(int t=0;t<N;t++){
            int player = move[t];
            status[player] += turn[t];
        }
        int cnt= 0;
        for(int i=0; i<K;i++){
            if(status[i] >=M-1){
                cnt++;
            }
        }
        ans =Math.max(ans,cnt);
    }
}
