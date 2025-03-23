import java.util.*;
import java.io.*;

/**
 * 테스트 케이스의 첫 번째 줄에는 총 이동 시간(M), BC의 개수(A)가 주어진다.
 * 그 다음 2개의 줄에는 각각 사용자 A와 B의 이동 정보가 주어진다.
 * 한 사용자의 이동 정보는 M개의 숫자로 구성되며, 각각의 숫자는 다음과 같이 매초마다 이동 방향을 의미한다.
 */
public class Solution_5644_무선충전 {
    static class Point implements Comparable<Point>{

        int power;
        int bc_idx;
        Point(int power,int bc_idx){
            this.power =power;
            this.bc_idx =bc_idx;
        }

        @Override
        public int compareTo(Point o1) {
            if(o1.power >this.power){
                return 1;
            }
            else if (o1.power == this.power){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int M; // 총이동시간
    static int A ;// BC의 갯수
    static int tc;
    static List <Point> [][] origin;
    static int [][]root;  //사용자이동정보
    static int [][]ans;
    static int [][] bc;
    static int [] dx ={ 0,0,1,0,-1};
    static int [] dy = {0,-1,0,1,0};
    static int t_ans;

    static int [][] user;  //사용자 좌표정보
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        tc = Integer.parseInt(br.readLine());
        for(int i=1; i<=tc; i++){
            sb.append("#").append(i).append(" ");
            input(); // input 받기
            set_data();  //첫 데이터 설정 좌표마다 point 로 넣어주기 Arraylist에 sorting 되어있음
            move();
            t_ans=0;
            cal_ans();
            sb.append(t_ans).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        root = new int [2][M+1];
        bc = new int [A][4];  //좌표 , c ,p 정보

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M;i++){
            root[0][i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M;i++){
            root[1][i] = Integer.parseInt(st.nextToken());
        }

        for(int y=0; y<A; y++){
            st = new StringTokenizer(br.readLine());
            for(int x =0; x<4; x++){

                bc[y][x] = Integer.parseInt(st.nextToken());

            }
        }
    }
    static void set_data(){

        user = new int[2][2];
        user[0][0] =1;
        user[0][1] =1;
        user[1][0] =10;
        user[1][1] =10;
        ans = new int [2][M+1];
        origin= new ArrayList [11][11];;
        for(int y=1; y<11;y ++){
            for(int x=1; x<11;x++){
                origin[y][x] = new ArrayList<>();
            }
        }

        for(int i=0;i<A;i++){
            set_origin(i);
        }

        for(int y=1; y<11;y ++){
            for(int x=1; x<11;x++){
                Collections.sort(origin[y][x]);   ///각 파워에서 파워가 큰순으로 정렬
            }
        }
    }

    static void set_origin(int idx){
        //처음에 영역 표시
        int d = bc[idx][2]; //d;
        int sx  =bc[idx][0];// i  ;
        int sy = bc[idx][1];//j//거리 0
        int power = bc [idx][3]; //c
        int cnt = d;
        while (cnt != 0) {
            int ui = sy - cnt;
            int bi = sy + cnt;
            for (int dj = sx - (d - cnt); dj <= sx + (d - cnt); dj++) {
                if (dj > 0 && dj < 11) {
                    if (ui > 0 && ui < 11)
                        origin[ui][dj].add(new Point(power,idx));
                    if (bi > 0 && bi < 11)
                        origin[bi][dj].add(new Point(power,idx));
                }
            }
            cnt--;
        }

        for (int dj = sx - d; dj <= sx + d; dj++) {
            if (dj > 0 && dj < 11) {
                origin[sy][dj].add(new Point(power,idx));
            }

        }
    }
    static void cal_point(int time){

        List <Point> aPoint = origin[user[0][1]][user[0][0]];
        List <Point> bPoint = origin[user[1][1]][user[1][0]];
        if(aPoint.size() ==0 && bPoint.size() ==0){
            ans[0][time] = 0;
            ans[1][time] = 0;
        }else if(aPoint.size() ==0 || bPoint.size() ==0) {
            if(aPoint.size() ==0){
                Point B =bPoint.get(0);
                ans[1][time] =B.power;
            }else{
                Point A =aPoint.get(0);
                ans[0][time] =A.power;
            }
        }

        else if(aPoint.size() ==1 && bPoint.size() ==1){  //둘다 1인경우
            Point A =aPoint.get(0);
            Point B =bPoint.get(0);
            if (A.bc_idx == B.bc_idx ){
                ans[0][time] = A.power/2;
                ans[1][time] = B.power/2;
            }else{
                ans[0][time] = A.power;
                ans[1][time] = B.power;
            }
        }
        else if(aPoint.size() ==1 || bPoint.size() ==1){   //둘중 한개의 size가 1인경우
            if(aPoint.size() ==1){
                Point A =aPoint.get(0);
                Point B = bPoint.get(0);
                ans[0][time] =A.power;
                if(B.bc_idx ==A.bc_idx){
                    Point B1 = bPoint.get(1);
                    ans[1][time] = B1.power;
                }else{
                    ans[1][time] = B.power;
                }
            }
            if(bPoint.size() ==1){
                Point A =aPoint.get(0);
                Point B = bPoint.get(0);
                ans[1][time] =B.power;
                if(B.bc_idx ==A.bc_idx) {
                    Point A1 = aPoint.get(1);
                    ans[0][time] = A1.power;
                }else{
                    ans[0][time] = A.power;
                }
            }
        }
        else if(aPoint.size() >1 && bPoint.size() >1){ //둘다 1개보다 많은경우
            Point A = aPoint.get(0);
            Point B = bPoint.get(0);
            if(A.bc_idx == B.bc_idx){
                Point A1 = aPoint.get(1);
                Point B1 = bPoint.get(1);
                if(A.power+ B1.power < A1.power+B.power) {
                    ans[0][time] = A1.power;
                    ans[1][time] =B.power;
                }
                else {
                    ans[0][time] = A.power;
                    ans[1][time] =B1.power;
                }

            }else{
                ans[0][time]= A.power;
                ans[1][time] =B.power;
            }
        }
    }

    static void move(){
        for(int time =0; time <=M; time++){
            for(int u_idx =0; u_idx<2; u_idx++){  //이동하지 않음
                int dir = root[u_idx][time];
                user[u_idx][0] += dx[dir];
                user[u_idx][1] += dy[dir];
            }
            //실제로 움직여서 그부분에서 파워계산해서 더해주기
            cal_point(time);
        }
    }

    static void cal_ans(){
        for(int y=0; y<2;y++){
            for(int i=0;i<=M ;i++){
                t_ans +=ans[y][i];
            }

        }
    }
}
