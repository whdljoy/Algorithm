import java.util.*;
import java.io.*;

public class Solution_19152_삼각김밥월드 {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int start;
    static int end;
    static int [] save = new int [10001];

    public static void main(String[] args) throws Exception {
        run();

    }
    static void run() throws Exception{
        TC = Integer.parseInt(br.readLine());
        setting();
        for(int i=0; i<TC;i++){
            sb.append("#").append(i+1).append(" ");
            input();
            cal();

        }
        System.out.println(sb);
    }
    static void setting(){
        int current=1;
        int gap=2;
        int height=1;
        for(int i=1;i<=10000;i++){
            save[i]=height;
            if(i == current){
                current+=gap;
                gap ++;
                height++;
            }
        }
    }



    static void input () throws  Exception{
        stk = new StringTokenizer(br.readLine());
        start = Integer.parseInt(stk.nextToken());
        end = Integer.parseInt(stk.nextToken());

    }
    static void cal(){
        int s,e;
        if (start <=end){
            s =start;
            e = end;
        }else{
            s= end;
            e = start;
        }
        int target_h = save[e];
        int height =save[s];
        int ans=save[e]-save[s];
        int right_val=s;
        int left_val=s;
        while(true){
            if(target_h == height){
                break;
            }
            left_val+=height;
            height++;
            right_val+=height;
        }
        if(right_val < e){
            ans += (e-right_val);
        }
        if(left_val > e){
            ans+=(left_val-e);
        }
        sb.append(ans).append("\n");

    }
}
