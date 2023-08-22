import java.util.*;
import java.io.*;
public class Solution_1667_프로세서연결하기 {
    static int [] dx={-1,0,1,0};
    static int [] dy = {0,-1,0,1};
    static int tc;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] origin;
    static List <Integer[]> core;
    static boolean [] check;
    static int ans =Integer.MAX_VALUE;
    static int core_ans =Integer.MIN_VALUE;
    static List <Integer[] > select;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        tc=Integer.parseInt(br.readLine());
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
            for(int c_idx=1;c_idx<=core.size();c_idx++){
                select_core(0,-1, c_idx);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static boolean check_size(int y, int x){  //side 에 있으면 true
        for(int dir=0;dir<4;dir++){
            int cy=y + dy[dir];
            int cx = x+ dx[dir];
            if(!in_range(cy,cx)){
                return true;
            }
        }
        return false;  //없으면 false
    }
    static boolean in_range(int y,int x){
        return 0<=x && x<N && 0<=y && y<N;
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
                if(origin[y][x] ==1 && !check_size(y,x)){   // 코어 넣기
                    core.add(new Integer[] {y,x});
                }
            }
        }

        check = new boolean[core.size()];
        ans =Integer.MAX_VALUE;
        core_ans =Integer.MIN_VALUE;
   }
    static void select_core(int num, int idx,int target){
        if(num == target){
            cal_line();
            return;
        }
        for(int i =idx+1; i<core.size();i++){
            check[i]= true;
            select_core(num+1,i,target);
            check[i]= false;
        }
    }
    static void cal_line(){
        select = new ArrayList<>();
        for(int i=0; i< core.size();i++){
            if(check[i]){
                select.add(core.get(i));
            }
        }
        search(0,origin);
    }
    static void search(int idx, int [][] status){
        if(idx ==select.size()){
            get_ans(status);
            return;
        }
        for(int dir=0;dir<4; dir++) {
            search(idx+1,change_status(select.get(idx),dir,status));
        }
    }
    static int [][] change_status(Integer[] core_info, int direction,int [][] cp){
        int [][] status = new int[N][N];
        for(int y=0;y<N;y++){
            for(int x=0; x<N; x++){
                status[y][x] = cp[y][x];
            }
        }
        int cx = core_info[1];
        int cy = core_info[0];
        boolean flag =true;
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
        }
        if(flag){
            int change_x = core_info[1];
            int change_y = core_info[0];
            while(true){
                change_x+=dx[direction];
                change_y+=dy[direction];
                if(!in_range(change_y,change_x)){
                    break;
                }
                status[change_y][change_x] =2;
            }
        }



        return status;
    }
    static void get_ans(int [][] status){
        int cur=0;
        for(int y=0; y<N; y++){
            for(int x=0;x<N;x++){
                if(status[y][x] ==2){
                    cur+=1;
                }
            }
        }
        if(core_ans < select.size()){
            ans = cur;
            core_ans = select.size();
        }else if (core_ans == select.size() && ans >cur){
            ans =cur;
        }
    }

}
