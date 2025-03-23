

import java.util.*;
import java.io.*;
public class Main_13164_행복유치원 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;
    static int [] user;
    static int ans;
    static Integer []minus;
    public static void main(String[] args) throws Exception {
        input();
        ans =0;
        cal();
        System.out.println(ans);
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        user=new int [N];
        for(int i=0; i<N;i++){
            user[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void cal(){
        minus=new Integer[N-1];
        for(int i=0; i<N-1;i++){
            minus[i]=user[i+1]-user[i];
        }
        Arrays.sort(minus);
        for(int i=0; i<minus.length-(K-1);i++){
            ans +=minus[i];
        }

    }
}
