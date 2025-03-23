import java.util.*;
import java.io.*;

public class Main_21758_꿀따기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int ans;
    static int [] origin;
    static int [] total_num;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception{
        input();
        between();
        left();
        right();
        System.out.println(ans);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        origin = new int [N];
        total_num = new int [N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            origin[i] = Integer.parseInt(st.nextToken());
        }
        total_num[0] = origin[0];
        for(int i=1;i<N;i++){
            total_num[i] = origin[i] +total_num[i-1];
        }
        ans=0;
    }
    static void between(){ // 벌 통 벌 있을때  //출발점 끝점 해서 max
        int left_idx =0; //왼쪽벌
        int right_idx = N-2;  //오른쪽벌 //해당 벌 의 값도 빼서 계산해야되서 (N-1 - origin[N-1])
        for(int i=1;i< N-1;i++){ // 누적 옮기면서 벌통위치 옮기기
            int cur_ans=0;
            cur_ans = cur_ans +total_num[i]-total_num[left_idx] + total_num[right_idx]-total_num[i-1];
            ans =Math.max(ans,cur_ans);
        }
    }

    static void left(){  // 벌 벌 통  제일 왼쪽 벌 고정
        int house = N-1;  //벌통
        for(int i=1;i< N-1;i++){ // 누적 옮기면서 왼쪽벌 위치
            int cur_ans=total_num[N-1]-origin[i] -origin[0]; //제일 왼쪽벌 값은  이동 벌 위치 값만 제외
            cur_ans = cur_ans + total_num[house]-total_num[i];
            ans =Math.max(ans,cur_ans);
        }
    }

    static void right(){ // 통 벌 벌 제일 오른쪽 벌 고정
        for(int i=1;i< N-1;i++){ // 누적 옮기면서 오른쪽 벌 위치
            int cur_ans=total_num[N-1]-origin[i] -origin[N-1]; //제일 오른쪽 벌 값은  이동 벌 위치 값만 제외
            cur_ans = cur_ans + total_num[i]-origin[i];
            ans =Math.max(ans,cur_ans);
        }
    }
}
