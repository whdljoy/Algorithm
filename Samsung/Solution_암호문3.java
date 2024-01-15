import java.util.*;
import java.io.*;
public class Solution_암호문3 {
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List <Integer> secret;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        for(int i=1;i<=10;i++) {
            sb.append("#").append(i).append(" ");
            input();
            simulate();
            result();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        secret = new ArrayList<>();
        setFirst();
        M = Integer.parseInt(br.readLine());
    }
    static void setFirst() throws Exception{
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            secret.add(Integer.parseInt(st.nextToken()));
        }
    }
    static void simulate() throws Exception{
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            String cur = st.nextToken();
            if(cur.equals("I")){
                insert();
            }else if (cur.equals("D")){
                delete();
            }else if (cur.equals("A")){
                add();
            }
        }
    }
    static void insert(){
        int x,y; // x번째 암호문 바로 뒤에 y개의 암호문 삽입;
        ArrayList ad = new ArrayList<Integer>();
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        for(int i=0;i<y;i++){
            ad.add(st.nextToken());
        }
        secret.addAll(x,ad);
    }

    static void delete(){
        //x번째 암호문 바로 다음부터 y개의 암호분 삭제
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for(int i=0;i<y;i++){
            secret.remove(x);
        }
    }
    static void add(){
        int y = Integer.parseInt(st.nextToken());
        for(int i=0;i<y;i++){
            secret.add(Integer.parseInt(st.nextToken()));
        }
    }
    static void result(){
        for(int i=0;i<10;i++){
            sb.append(secret.get(i)).append(" ");
        }
        sb.append("\n");
    }
}
