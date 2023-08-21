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
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        for(int i=1;i<=tc;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
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
   }


}
