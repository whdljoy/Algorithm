import java.util.*;
import java.io.*;

public class Main_1174_줄어드는수 {
    static int N;
    static List <Long> ans;
    static int [] num;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        input();
        num[10]=-1;
        dfs(10);
        Collections.sort(ans);
        if(ans.size()< N){
            System.out.println(-1);
        }else{
            System.out.println(ans.get(N-1));
        }

    }
    //최대수 987654321
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        num =new int [11];
        ans = new ArrayList<>();
    }
    static void dfs(int idx){
        if(idx ==-1){
            return ;
        }
        for(int i=0; i<10;i++){
            if(num[idx] < i){
                num[idx-1]=i;
                get_num();
                dfs(idx-1);
                num[idx-1]=0;
            }
        }
    }

    static void get_num(){
        long cur=0;
        for(int i=0;i<10;i++){
            cur = cur+ (long)Math.pow(10,9-i) * num[i];
        }
        ans.add(cur);
    }

}
