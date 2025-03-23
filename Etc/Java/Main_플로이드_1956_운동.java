import java.util.*;
import java.io.*;
public class Main_플로이드_1956_운동 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int V,E;
    static int [][] dis;
    static  int answer =Integer.MAX_VALUE/2-1;
    static final int INF =Integer.MAX_VALUE/2-1;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        input();
        floid();
        System.out.println(answer);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());  //마을
        E = Integer.parseInt(st.nextToken());  // 도로 갯수
        dis = new int [V+1][V+1];
        for(int i=0;i<=V;i++){
            Arrays.fill(dis[i],INF);
        }
        for(int i=0; i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); int e = Integer.parseInt(st.nextToken()); int d = Integer.parseInt(st.nextToken());
            dis[s][e]= d;
        }
    }
    static void floid(){
        for(int x=1;x<=V;x++){
            for(int cent=1;cent<=V;cent++){
                for(int y=1;y<=V;y++){
                    dis[x][y]= Math.min(dis[x][cent] +dis[cent][y], dis[x][y]);
                }
            }
        }

        for(int x=1;x<=V;x++){
            for(int y=1;y<=V;y++){
                if(x != y) continue;
                answer = Math.min(answer,dis[x][y]);
            }
        }
        if(answer == INF) answer =-1;
    }
}
