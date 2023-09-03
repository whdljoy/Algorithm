import java.util.*;
import java.io.*;
public class Main_10971_외판원순회2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int [][] distance;
    static boolean [] check;
    static int ans;
    public static void main(String[] args) throws Exception{
        input();
        check[0]=true;
        dfs(0,0,1);
        System.out.println(ans);
    }
    static void input() throws Exception{
        N  = Integer.parseInt(br.readLine());
        StringTokenizer st;
        distance = new int[N][N];
        for(int y=0; y<N;y++){
            st= new StringTokenizer(br.readLine());
            for(int x=0; x<N;x++){
                distance[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        ans =Integer.MAX_VALUE;
        check = new boolean[N];
    }

    static void dfs(int start,int sum,int num){
        if(num ==N ){
            if(distance[start][0]!=0){
                sum += distance[start][0];
                ans =Math.min(ans,sum);
            }
            return;
        }
        for(int x=0;x<N;x++){
            if(!check[x]&& distance[start][x] !=0){
                check[x] =true;
                dfs(x,sum+distance[start][x],num+1);
                check[x]=false;
            }
        }
    }
}
