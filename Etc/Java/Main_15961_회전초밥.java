import java.util.*;
import java.io.*;
public class Main_15961_회전초밥 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;  //접시수
    static int d;  //초밥 가짓수
    static int k; // 접시수
    static int c;  //쿠폰번호
    static int [] check;
    static int [] order;
    static int ans=0;
    public static void main(String[] args) throws Exception{
        input();
        slide();
        System.out.println(ans);
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        order = new int[N];
        check = new int[d+1];
        for(int i=0;i<N; i++){
            order[i] = Integer.parseInt(br.readLine());
        }
    }

    static void slide(){
        int start =0;
        int end =k-1;
        int cur =0;
        for(int idx =start; idx<=end;idx++){  //처음 갯수 셋팅
            if(check[order[idx]] == 0){
                cur +=1;
            }
            check[order[idx]]++;
        }
        while(true){
            if(check[c] ==0){ //
                ans = Math.max(cur+1,ans);
            }else{
                ans = Math.max(cur,ans);
            }
            //후작업 슬라이드 옮기기
            int before =start +1;
            int after = end +1;
            if (after == N){
                after =0;
            }
            if (before  ==N){
                before = 0;
            }
            check[order[start]]--;
            if(check[order[start]]==0){
                cur --;
            }
            start = before;
            if(check[order[after]]==0){
                cur ++;
            }
            check[order[after]]++;
            end=after;
            if(start == 0){
                break;
            }
        }
    }

}
