import java.util.*;
import java.io.*;
public class swea9229 {
    static int tc;
    static int N;
    static int M;
    static List<Integer> info;
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for(int i=0;i<tc;i++){
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(i+1).append(" ");
            input(br);
            ans=-1;
            check(0,0,-1);
            sb.append(ans);
            System.out.println(sb);
        }

    }

    public static void input(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        info = new ArrayList<>();
        for(int i=0;i<N;i++){
            info.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void check(int num ,int sum,int idx){
        if(num ==2){
            if(sum <=M) {
                ans = Math.max(ans, sum);
            }
            return;
        }
        for(int i=idx+1;i<info.size();i++){
            sum +=info.get(i);
            num +=1;
            check(num,sum,i);
            num-=1;
            sum -= info.get(i);
        }
    }
}
