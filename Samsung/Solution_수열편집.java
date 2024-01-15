import java.util.*;
import java.io.*;
public class Solution_수열편집 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int N,M,L;
    static List <Integer> number;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        tc = Integer.parseInt(br.readLine());
        for(int i=1;i<=tc;i++) {
            sb.append("#").append(i).append(" ");
            input();
            simulate();
        }
        System.out.println(sb);

    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        number = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            number.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void simulate() throws Exception{
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String cur = st.nextToken();
            if(cur.equals("I")){
                insert();

            }else if(cur.equals("D")){
                delete();
            }
            else if(cur.equals("C")){
                change();
            }
        }
        if(number.size()<=L){
            sb.append(-1);
        }else{
            sb.append(number.get(L));
        }
        sb.append("\n");
    }
    static void insert() {
        int idx = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        number.add(idx,num);
    }
    static void delete(){
        int idx = Integer.parseInt(st.nextToken());
        number.remove(idx);
    }
    static void change(){
        int idx = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        number.set(idx,num);
    }
}
