import java.util.*;
import java.io.*;
public class Main_1600_말이되고픈원숭이 {
    static BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    static int K;
    static int W;
    static int H;
    static int [][] ground;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0, -1, 0,1};
    static int [] night_dy ={ -1, -2, 1, 2, -1, -2, 1, 2};
    static int [] night_dx ={  2,  1, 2, 1, -2, -1, -2, -1};
    static boolean [][][] check;
    static int ans =-1;
    public static void main(String[] args) throws Exception{
        input();
        bfs();
        if (W ==1 && H==1){
            ans =0;
        }
        System.out.println(ans);
    }

    static void input() throws Exception{
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ground = new int [H][W];
        for(int y=0; y<H; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<W;x++) {
                ground[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        check = new boolean[H][W][K+1];
    }

    static void bfs(){
        Queue <Integer[] > q = new ArrayDeque<>();
        check[0][0][0]=true;
        q.add(new Integer[]{0,0,0,0});
        while(!q.isEmpty()){
            Integer [] current = q.poll();
            int tmp_K = current[3];
            int monkey =current[2];
            for(int dir=0;dir<4;dir++) {
                int cx=current[1]+dx[dir];
                int cy =current[0] +dy[dir];
                if(cx == W-1 && cy == H-1){
                    ans = monkey+1;
                    return;
                }
                if(in_range(cx,cy) &&!check[cy][cx][tmp_K] &&ground[cy][cx] != 1){
                    check[cy][cx][tmp_K]= true;
                    q.add(new Integer[]{cy,cx,monkey+1,tmp_K});
                }
            }
            if(tmp_K <K){
                for(int dir=0;dir<8;dir++) {
                    int cx=current[1] + night_dx[dir];
                    int cy =current[0] + night_dy[dir];
                    if(cx == W-1 && cy == H-1){
                        ans = monkey+1;
                        return;
                    }
                    if(in_range(cx,cy) &&!check[cy][cx][tmp_K+1] &&ground[cy][cx] != 1){
                        check[cy][cx][tmp_K+1]= true;
                        q.add(new Integer[]{cy,cx,monkey+1,tmp_K+1});
                    }
                }
            }
        }
    }

    static boolean in_range(int x, int y){
        return 0<= x&& x<W && 0<=y && y<H;
    }
}
