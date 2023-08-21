import java.util.*;
import java.io.*;
public class Main_1759_암호만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int L;
    static int C;
    static char [] alph;
    static boolean[] check;
    public static void main(String[] args) throws  Exception{
        input();
        dfs(0,-1,0,0);
        System.out.println(sb);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        alph = new char[C];
        check = new boolean[C];
        for(int i=0;i<C;i++){
            alph[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alph);
    }

    static void dfs(int num,int idx,int con, int vow){
        if(num == L){
            if(con >=2 && vow >=1){
                for(int i=0;i<C;i++) {
                    if(check[i]){
                        sb.append(alph[i]);
                    }
                }
                sb.append("\n");
            }
            return;
        }
        for(int i=idx+1; i<C;i++ ){
            if(!check[i]){
                check[i]=true;
                if(alph[i] =='a' ||alph[i] =='e'||alph[i] =='i'||alph[i] =='o'||alph[i] =='u'){
                    dfs(num+1,i,con,vow+1);
                }else{
                    dfs(num+1,i,con+1,vow);
                }
                check[i]=false;
            }
        }
    }
}
