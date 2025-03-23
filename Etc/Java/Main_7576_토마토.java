import java.util.*;
import java.io.*;
public class Main_7576_토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M;
    static int N;
    static Deque <Integer []> tomato;
    static int [][] info;
    static int TC=10;
    static int answer =0;

    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        input();

        bfs();
        boolean flag = check();
        if(flag){
            System.out.println(answer-1);
        }else{
            System.out.println(-1);
        }





    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        info = new int [N+1][M+1];
        tomato = new ArrayDeque<>();
        for(int y=0;y<N;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<M;x++){
                info[y][x] = Integer.parseInt(st.nextToken());
                //정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
                if (info[y][x] == 1) tomato.add(new Integer[] {y,x});
            }
        }
    }
    static boolean check(){
        boolean flag = true;
        for(int y=0;y<N;y++){
            for(int x=0;x<M;x++){
                if(info[y][x] ==0) {
                    return false;
                }
            }
        }
        return flag;
    }
    static void bfs(){
        int [] dx={-1,0,1,0};
        int [] dy={0,-1,0,1};
        boolean [][] check = new boolean[N][M];
        if(!tomato.isEmpty()){
            Integer [] start = tomato.peekFirst();
            check[start[0]][start[1]]= true;
        }
        while(!tomato.isEmpty()){
            int num = tomato.size();
            for(int j=0; j<num; j++){
                Integer [] cur = tomato.pollFirst();
                for(int i=0; i<4;i++){
                    int cx = cur[1] +dx[i];
                    int cy = cur[0] +dy[i];
                    if(!in_range(cx,cy)) continue;
                    if(check[cy][cx]) continue;

                    if(info[cy][cx] == 0){
                        tomato.add(new Integer []{cy,cx});
                        check[cy][cx] = true;
                        info[cy][cx] =1;
                    }

                }
            }
            answer++;
        }
    }
    static boolean in_range(int cx,int cy){
        return 0<=cx && cx<M && 0<=cy && cy<N;
    }
}
