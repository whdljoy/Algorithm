import java.util.*;
import java.io.*;
public class Solution_1667_프로세서연결하기 {
    static int [] dx={-1,0,1,0};
    static int [] dy = {0,-1,0,1};
    static int tc;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] origin;
    static int MAX_CORE;
    static boolean flag;
    static List <Integer[]> core;
    static boolean [] check;
    static int ans;
    static int core_ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        tc=Integer.parseInt(br.readLine());
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            if(MAX_CORE ==0){
                ans =0;
            }else{
                check = new boolean[MAX_CORE];
                select_core(0,0,origin);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static boolean in_range(int y,int x){
        return 0<=x && x< N && 0<=y && y< N;
    }

   static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        origin = new int [N][N];
        core = new ArrayList<>();
        StringTokenizer st;
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N;x++){
                origin[y][x] = Integer.parseInt(st.nextToken());
                if(origin[y][x] ==1){
                    if(y !=0 && y!=N-1 && x!=0 && x!=N-1){
                        core.add(new Integer[] {y,x});
                    }
                }
            }
        }
        MAX_CORE = core.size();
        ans =Integer.MAX_VALUE;
        core_ans =Integer.MIN_VALUE;
   }
    static void select_core(int num, int idx, int [][] status){
        if(idx== core.size()){
            get_ans(status,num);
            return;
        }
        for(int dir=0;dir<4; dir++) {
            int [][] cp =change_status(idx,dir,status);
            if(flag){
                select_core(num+1,idx+1,cp);
            }
        }
        select_core(num,idx+1,status);
    }
    static int [][] change_status(int idx, int direction,int [][] cp){
        Integer [] core_info = core.get(idx);
        int [][] status = new int[N][N];
        int cx = core_info[1];
        int cy = core_info[0];
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                status[y][x] = cp[y][x];
            }
        }
        flag =true;
        while(true){
            cx+=dx[direction];
            cy+=dy[direction];
            if(!in_range(cx,cy)){
                break;
            }
            if(status[cy][cx] != 0){
                flag =false;
                break;
            }
            status[cy][cx] =2;
        }

        return status;
    }
    static void get_ans(int [][] status,int num){
        int cur=0;
        for(int y=0; y<N; y++){
            for(int x=0;x<N;x++){
                if(status[y][x] ==2){
                    cur+=1;
                }
            }
        }
        if(core_ans < num){
            ans = cur;
            core_ans = num;
        }else if (core_ans == num&& ans >cur){
            ans =cur;
        }

    }

}
