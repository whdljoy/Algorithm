import java.util.*;
import java.io.*;
public class Solution_문자열교집합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N,M;
    static Set <String> info;
    static int ans=0;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        info = new HashSet<>();
        ans =0;
        for(int i=0;i<N;i++){
            info.add(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            if(info.contains(st.nextToken())){
                ans+=1;
            }
        }
    }
}
