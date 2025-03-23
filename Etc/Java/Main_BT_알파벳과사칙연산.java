import java.util.*;
import java.io.*;
public class Main_BT_알파벳과사칙연산 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int alpha_N;
    static int [] status;
    static List <Integer> num;
    static List<Character> op;
    static long ans=Long.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        input();
        bt(0);
        if(ans<Integer.MIN_VALUE){
            ans=Integer.MIN_VALUE;
        }else if(ans>Integer.MAX_VALUE){
            ans=Integer.MAX_VALUE-1;
        }
        System.out.println(ans);
    }

    static void input() throws Exception{
        String ip = br.readLine();
        alpha_N=0;
        op = new ArrayList<>();
        num = new ArrayList<>();
        for(int i=0; i<ip.length();i++){
            char cur =ip.charAt(i);
            if('a'<=cur && cur<='f'){
                num.add(cur-'a');
                alpha_N++;
            }else{
                op.add(cur);
            }
        }
        status = new int [6];
    }
    static void bt(int num){
        if(num == 6){
            cal();
            return;
        }
        for(int i=1;i<=4;i++){
            status[num]=i;
            bt(num+1);
        }
    }
    static void cal(){
        long current=status[num.get(0)];
        int op_cnt=0;
        for(int i=1;i<alpha_N;i++){
            char cur = op.get(op_cnt++);
            if(cur=='+'){
                current +=status[num.get(i)];
            }else if (cur =='-'){
                current -=status[num.get(i)];
            }else if(cur == '*'){
                current *=status[num.get(i)];
            }
        }
        ans=Math.max(ans,current);

    }

}

/**
 * b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b+b-a*b-c+b
 */