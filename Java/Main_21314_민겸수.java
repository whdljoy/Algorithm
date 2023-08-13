import java.util.*;
import java.io.*;


public class Main_21314_민겸수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String number;
    public static void main(String[] args) throws Exception {
        input();
        cal_largest();
        cal_least();
        System.out.println(sb);
    }

    static void input() throws Exception{
        number =br.readLine();
    }


    static void cal_least(){
        int idx =0;
        int check=0;
        while(true){
            if(idx == number.length()){
                if(check!=0){
                    sb.append("1");
                    check-=1;
                    while(check!=0){
                        sb.append("0");
                        check-=1;
                    }
                }
                break;
            }
            if(number.charAt(idx)== 'M'){
                check +=1;
                idx+=1;

            }else{
                if(check!=0){
                    sb.append("1");
                    check-=1;
                    while(check!=0){
                         sb.append("0");
                         check-=1;
                    }
                }
                sb.append("5");
                idx+=1;
            }
        }
    }

    static void cal_largest(){
        int idx =0;
        int check=0;
        while(true){
            if(idx == number.length()){
                while(check!=0){
                    sb.append("1");
                    check-=1;
                }

                break;
            }
            if(number.charAt(idx)== 'M'){
                check +=1;
                idx+=1;

            }else{
                sb.append("5");
                while(check!=0) {
                    sb.append("0");
                    check -= 1;
                }
                idx+=1;
            }
        }
        sb.append("\n");
    }
}
