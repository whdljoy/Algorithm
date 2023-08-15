import java.util.*;
import java.io.*;
public class Solution_1873_상호의배틀필드 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc;
    static int H;//높이
    static int W;/// 너비

    static int N;
    static StringBuilder sb = new StringBuilder();
    static char [][]origin;
    static int sx;
    static int sy;
    static char [] user;
    public static void main(String[] args) throws Exception{
        run();
    }

    static void run() throws Exception{
        tc = Integer.parseInt(br.readLine());

        for(int i=1; i<=tc; i++){
            sb.append("#").append(i).append(" ");
            input();
            change();
            print();
        }
        System.out.println(sb);
    }
    static void input()throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        origin= new char[H][W];
        for(int y=0; y<H;y++){
            String ip = br.readLine();
            for(int x=0; x<W;x++){
                origin[y][x] = ip.charAt(x);
                if(origin[y][x] == '<' ||origin[y][x] == '^'|| origin[y][x] == 'v' || origin[y][x] == '>' ){
                    sx=x;
                    sy=y;
                }
            }
        }
        N = Integer.parseInt(br.readLine());
        user = new char[N];
        String ip = br.readLine();
        for(int i=0; i<N; i++){
            user[i] = ip.charAt(i);
        }

    }
    static void print() throws Exception{
        for(int y=0; y<H;y++){
            for(int x=0; x<W; x++){
                sb.append(origin[y][x]);
            }
            sb.append("\n");
        }

    }
    /**
     * .	평지(전차가 들어갈 수 있다.)
     * *	벽돌로 만들어진 벽
     * #	강철로 만들어진 벽
     * -	물(전차는 들어갈 수 없다.)
     * ^	위쪽을 바라보는 전차(아래는 평지이다.)
     * v	아래쪽을 바라보는 전차(아래는 평지이다.)
     * <	왼쪽을 바라보는 전차(아래는 평지이다.)
     * >	오른쪽을 바라보는 전차(아래는 평지이다.)
     *
     *
     *
     문자	동작
     U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
     D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
     L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
     R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
     S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.


     전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 당연히 이동하지 않는다.
     전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
     만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
     강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
     게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.

     */
    static boolean in_range(int y,int x){
        return 0<=y && y<H && 0<=x && x<W;
    }
    static void change(){
        for(int i= 0; i<N; i++){
            switch (user[i]){
                case 'U':
                    origin[sy][sx] = '^';
                    if(in_range(sy-1,sx) && origin[sy-1][sx] =='.'){
                        origin[sy-1][sx] ='^';
                        origin[sy][sx] ='.';
                        sy-=1;
                    }
                    break;
                case 'D':
                    origin[sy][sx] = 'v';
                    if(in_range(sy+1,sx) && origin[sy+1][sx] =='.'){
                        origin[sy+1][sx] ='v';
                        origin[sy][sx] ='.';
                        sy+=1;
                    }
                    break;
                case 'L':
                    origin[sy][sx] = '<';
                    if(in_range(sy,sx-1) && origin[sy][sx-1] =='.'){
                        origin[sy][sx-1] ='<';
                        origin[sy][sx] ='.';
                        sx-=1;
                    }
                    break;
                case 'R':
                    origin[sy][sx] = '>';
                    if(in_range(sy,sx+1) && origin[sy][sx+1] =='.'){
                        origin[sy][sx+1] ='>';
                        origin[sy][sx] ='.';
                        sx+=1;
                    }
                    break;
                case 'S':
                    shoot();
                    break;
            }
        }
    }
    public static void shoot(){
        switch (origin[sy][sx]){
            case '>':
                int x = sx+1;
                while(x!=W){
                    if(origin[sy][x] =='#'){
                        break;
                    }
                    if(origin[sy][x] =='*'){
                        origin[sy][x] = '.';
                        break;
                    }
                    x+=1;
                }
                break;
            case '<':
                int cx = sx-1;
                while(cx!=-1){
                    if(origin[sy][cx] =='#'){
                        break;
                    }
                    if(origin[sy][cx] =='*'){
                        origin[sy][cx] = '.';
                        break;
                    }
                    cx-=1;
                }
                break;
            case '^':
                int cy = sy-1;
                while(cy!=-1){
                    if(origin[cy][sx] =='#'){
                        break;
                    }
                    if(origin[cy][sx] =='*'){
                        origin[cy][sx] = '.';
                        break;
                    }
                    cy-=1;
                }
                break;
            case 'v':
                int y = sy+1;
                while(y!=H){
                    if(origin[y][sx] =='#'){
                        break;
                    }
                    if(origin[y][sx] =='*'){
                        origin[y][sx] = '.';
                        break;
                    }
                    y+=1;
                }
                break;
        }
    }
}
