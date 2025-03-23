import java.util.*;
import java.io.*;

public class LeetCode_23_12_11 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;
        static StringBuilder sb = new StringBuilder();
        static int [] save;
        static List <Integer> ip;

        public static void main(String[] args) throws  Exception{
            run();
        }
        static  void run() throws Exception {
            input();

        }
        static void input() throws Exception{
            st= new StringTokenizer(br.readLine());
            ip = new ArrayList<>();
            while(st.hasMoreTokens()){
                ip.add(Integer.parseInt(st.nextToken()));
            };
            int [] temp = new int[ip.size()];
            for(int i=0;i<ip.size();i++){
                temp[i] = ip.get(i);
            }
            System.out.println(findSpecialInteger(temp));
        }

        static int findSpecialInteger(int [] arr){
            save = new int [arr[arr.length-1]+1];
            for(int i=0; i<arr.length; i++) {
                save[arr[i]] +=1;
            }
            int tar = (int) (arr.length * 0.25);
            for(int i=0; i<save.length; i++) {
                if(save[i] >tar){
                    return i;
                }
            }
            return save[0];
        }
}
