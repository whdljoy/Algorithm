import java.util.*;
import java.io.*;
public class boj1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int alpha [] = new int[26];
        String ip = br.readLine();
        ip = ip.toUpperCase();
        for(int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            alpha[ch - 'A']++;
        }
        int max_idx =0;
        int val =0;
        char ch = '?';
        for(int j=0; j<alpha.length;j++){
            if(alpha[j]>val){
                val = alpha[j];
                ch = (char)(j +'A');
            }
            else if (alpha[j]==val){
                ch = '?';
            }
        }
        System.out.println(ch);

    }
}
