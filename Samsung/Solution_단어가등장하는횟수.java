import java.util.*;
import java.io.*;

/**
 * https://yoongrammer.tistory.com/92
 * KMP 알고리즘
 */
public class Solution_단어가등장하는횟수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static String provide;
    static String want;
    static int [] pi ;// 접두사 접미어;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            getPi();
            KMP();
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        provide = br.readLine();
        want = br.readLine();
        pi = new int [want.length()];

    }

    static void  getPi(){
        int st =1, match =0;
        while(st + match < want.length()){
            if(want.charAt(st+match) == want.charAt(match)){
                match++;
                pi[st + match - 1] = match;
            }
            else{
                if(match == 0)
                    st++;
                else{
                    st += match - pi[match - 1];
                    match = pi[match - 1];
                }
            }
        }
    }
    static void KMP(){
        int n = provide.length(), m = want.length();
        int ans =0;
        int st = 0, match = 0;
        while(st <= n - m){
            if (match < m && provide.charAt(st + match) == want.charAt(match)){

                ++match;

                if (match == m)
                    ans++;
            }
            else{

                if(match == 0)
                    ++st;
                else{
                    st+= match - pi[match - 1];
                    match = pi[match - 1];
                }
            }
        }
        sb.append(ans).append("\n");
    }
}
