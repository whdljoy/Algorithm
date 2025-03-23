import java.util.*;
import java.io.*;

public class Main_3040_백설공주와일곱난쟁이 {
    static int[] info;
    static boolean [] checked;
    static List<Integer> ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = new int[9];
        checked = new boolean[9];
        ans = new ArrayList<>();
        for(int i=0;i<9;i++){
            info[i] = Integer.parseInt(br.readLine());
        }
        cal(0,0,-1);
        for(int a:ans){
            System.out.println(a);
        }

    }
    static void cal(int num,int sum,int idx){
        if(num ==7){
            if(sum ==100){
                for(int i=0;i<9;i++){
                    if(checked[i]) ans.add(info[i]);
                }
            }
            return;
        }
        for(int i = idx+1; i<9;i++){
            sum =sum+info[i];
            num ++;
            checked[i] = true;
            cal(num,sum,i);
            sum =sum-info[i];
            num --;
            checked[i] = false;
        }
    }
}
