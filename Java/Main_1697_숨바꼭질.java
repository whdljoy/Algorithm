import java.util.*;
import java.io.*;
public class Main_1697_숨바꼭질 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean [] check;
    static final int K_MAX =100001;
    static int N;
    static int K;
    static int ans;
    public static void main(String[] args) throws  Exception {
        input();
        bfs();
        System.out.println(ans);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        check = new boolean [K_MAX];

    }

    static void bfs(){
        Queue <Integer []> q = new ArrayDeque<>();
        q.add(new Integer []{N,0});
        while(!q.isEmpty()){
            Integer [] current = q.poll();
            int score = current[1];
            int cur =current[0];
            if (cur == K){
                ans = score;
                return ;
            }
            if(cur+1 <K_MAX && !check[cur+1]){
                q.add(new Integer []{cur+1,score+1});
                check[cur+1] = true;
            }
            if(-1<cur-1 && cur-1 <K_MAX && !check[cur-1]){
                q.add(new Integer []{cur-1,score+1});
                check[cur-1] = true;
            }
            if(2*cur <K_MAX && !check[2*cur]){
                q.add(new Integer []{2*cur,score+1});
                check[2*cur] = true;
            }
        }
    }


}
