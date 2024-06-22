import java.util.*;
import java.io.*;

public class Main_7569_토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M,N,H;
    static int [][][] tomato;
    static Deque <Integer[]> ripe;
    static int ripe_n;
    static int not_ripe;
    static int TC=10;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  //가로
        N = Integer.parseInt(st.nextToken());  //세로
        H = Integer.parseInt(st.nextToken()); // 높이

        tomato = new int [H][N][M];
        ripe  = new ArrayDeque<>();
        for(int z=0;z<H;z++){
            for(int y=0;y<N;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<M;x++){
                    tomato[z][y][x] = Integer.parseInt(st.nextToken());
                    if(tomato[z][y][x] == 1){
                        ripe_n += 1;
                        ripe.add(new Integer [] {x,y,z});
                    }else if(tomato[z][y][x] == -1){
                        not_ripe += 1;
                    }
                }
            }
        }
    }
    static void simulate(){
        while(!ripe.isEmpty()){
            int num = ripe.size();
            for(int i=0; i<num;i++){
                bfs(ripe.pollFirst());
            }
        }
    }

    static void bfs(Integer [] current){
        boolean [][][] check = new boolean[H][N][M];
        check[current[0]][current[1]][current[2]] = true;
        Deque <Integer[]> dq = new ArrayDeque<>();
        dq.add(current);
        Integer [] dx = {-1,0,1,0,0,0};
        Integer [] dy = {0,-1,0,1,-1,1};

    }
}
