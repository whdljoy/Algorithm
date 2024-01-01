import java.util.*;
import java.io.*;
public class Main_BT_특정조건에맞게1개를N번 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int K;
    static int [] ans;
    public static void main(String[] args)  throws  Exception{

        input();
        bt(0);
        System.out.println(sb);
    }


    static void input() throws  Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K= Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ans = new int[N];
    }

    static void bt(int num){
        if(num == N){
            int check =ans[0];
            int cnt =1;
            if(N >=3){
                for(int i=1;i<N;i++){
                    if(check == ans[i]){
                        cnt ++;
                    }else{
                        check = ans[i];
                        cnt =1;
                    }
                    if(cnt >=3) {
                        return;
                    }
                }
            }
            for(int i=0;i<N;i++){
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1;i<=K;i++){
            ans[num] = i;
            bt(num+1);
        }
    }
}
