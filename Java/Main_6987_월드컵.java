import java.util.*;
import java.io.*;
public class Main_6987_월드컵 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int [][] origin;
    static int [][] check;
    static int [][] cs = {{0,1},{0,2},{0,3},{0,4},{0,5},{1,2},{1,3},{1,4},{1,5},{2,3},{2,4},{2,5},{3,4},{3,5},{4,5}};
    static int ans;
    static int [] sum_check;
    public static void main(String[] args) throws Exception{
        run();

    }
    static void run() throws Exception{
       for(int i=0;i <4;i++){
            ans =0;
            input();
            if(checking_sum()){
                dfs(0);
            }
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }

    static boolean checking_sum(){
        for(int i=0;i<6;i++){
            if(sum_check[i] !=5){
                return false;
            }
        }
        return true;
    }

    static void input () throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        sum_check =new int[6];
        origin = new int [6][3];

        for(int y=0; y<6; y++){
            for(int x=0; x<3; x++){
                origin[y][x] = Integer.parseInt(st.nextToken());
                sum_check[y] += origin[y][x];
            }
        }
        check = new int [6][3];

    }
    static void dfs(int num) {
        if(num ==15){
            ans = 1;
            return;
        }
        int f =cs[num][0];
        int s = cs[num][1];
        //이기고
        if(check[f][0]< origin[f][0] && check[s][2] <origin[s][2] ) {
            check[f][0]++;
            check[s][2]++;
            dfs(num+1);
            check[f][0]--;
            check[s][2]--;
        }
        //비긴다.
        if(check[f][1] <origin[f][1] && check[s][1] <origin[s][1]){
            check[f][1]++;
            check[s][1]++;
            dfs(num+1);
            check[f][1]--;
            check[s][1]--;
        }
        //진다
        if(check[f][2] <origin[f][2] && check[s][0] < origin[s][0]){
            check[f][2]++;
            check[s][0]++;
            dfs(num+1);
            check[f][2]--;
            check[s][0]--;
        }
    }
}
