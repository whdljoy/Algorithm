import java.util.*;
import java.io.*;
public class Main_1027_고층건물 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int [] building;
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        run();
    }
    static void run() throws Exception{
        input();
        simulate();
        System.out.println(answer);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        building = new int [N];
        st = new StringTokenizer (br.readLine());
        for(int i=0; i<N;i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void simulate() {
        for(int i=0; i<N;i++) {
            int x_point = i +1;
            int height = building[i];
            int num=0;
            //		System.out.println();
            //	System.out.println("current" + i);
            for(int left =0; left<i;left ++) {
                double c_slope = (double)(height-building[left])/(x_point - (left+1));// 현재 점보다 왼쪽에 있는 빌딩의 기울기

                double val = height - c_slope * x_point; // 상수

                boolean flag =false;
                for(int be= left+1;be<i;be++) {
                    double target =c_slope * (be+1) + val;
                    if(target <= building[be]) {
                        //		System.out.println( " target" +  target+ "         be "+building[be]);
                        flag =true;
                        break;
                    }
                }
                if(!flag) {
                    num+=1;
                    //		System.out.println("left" + left);
                }
            }
            for(int right =N-1; right>i;right--) {
                double c_slope = (double)(height-building[right])/(x_point - (right+1));
                double val = height - (c_slope * x_point);
                boolean flag =false;
                for(int af = right-1; af>i;af--) {
                    double target =(c_slope * (af+1)) + val;
                    if(target <= building[af]) {
                        //		System.out.println( " target" +  target+ "         af "+building[af]);
                        flag =true;
                        break;
                    }
                }
                if(!flag) {
                    num+=1;
                    //		System.out.println("right" + right);
                }
            }
            answer = Math.max(answer, num);
        }
    }
}
