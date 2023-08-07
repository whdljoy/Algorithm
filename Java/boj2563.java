import java.util.*;
import java.io.*;

public class boj2563 {
    static int N;
    static int [][] mp;
    static int ans ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        System.out.println(ans);
    }

    public static void input(BufferedReader br) throws IOException{
        N = Integer.parseInt(br.readLine());
        mp = new int[101][101];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int a=x; a<(x+10); a++){
                for(int b =y; b<(y+10); b++){
                    if(mp[a][b]==0){
                        mp[a][b] =1;
                        ans +=1;
                    }
                }
            }
        }
    }
}
