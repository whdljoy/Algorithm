import java.util.*;
import java.io.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class boj2868 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;  //정점갯수
    static int M; //간선갯수
    static int start;
    static int end;
    static boolean []check;
    static boolean [] save;
    static ArrayList<Integer> take;
    static ArrayList<ArrayList<Integer>> info;
    static ArrayList<ArrayList<Integer>> ans;
    static ArrayList<Integer> s_route;
    static ArrayList<Integer> e_route;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws  Exception{
        run();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static void run()throws  Exception{
        input();
        save = new boolean[N+1];
        dfs(start);
        get_st();
        check = new boolean[N+1];
        for(int i=0; i<s_route.size();i++){
            check[s_route.get(i)] =true;
        }
        ans = new ArrayList<>();
        take = new ArrayList<>();
        e_dfs(end);
        int t_len =e_route.size()+s_route.size();
        sb.append(t_len);
    }
    public static void e_dfs(int num){
        ArrayList <Integer> cur = info.get(num);
        if(num == start|| cur.isEmpty()){
            if(num ==start){
                e_route=(ArrayList<Integer>) take.clone();
            }
            return;
        }
        for(int v :cur){
            if(!check[v]){
                check[v] = true;
                take.add(v);
                e_dfs(v);
                take.remove(Integer.valueOf(v));
                check[v] = false;
            }
        }
    }
    public static  void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new ArrayList<>();
        ans = new ArrayList<>();
        take = new ArrayList<>();
        for(int i=0;i<=N;i++){
            info.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            info.get(s).add(e);
            info.get(e).add(s);
        }
        st=new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    public static void dfs(int num){
        ArrayList <Integer> cur = info.get(num);
        if(num == end || cur.isEmpty()){
            if(num ==end){
                ans.add((ArrayList<Integer>) take.clone());
            }
            return;
        }
        for(int v :cur){
            if(!save[v]){
                save[v] = true;
                take.add(v);
                dfs(v);
                take.remove(Integer.valueOf(v));
                save[v] = false;
            }
        }
    }
    public static void get_st(){
        s_route = new ArrayList<>();
        int m_len=Integer.MAX_VALUE;
        for (int i=0; i<ans.size(); i++){
            if (ans.get(i).size() <m_len){
                m_len = ans.get(i).size();
            }
        }
        for (int i=0; i<ans.size(); i++){
            if (ans.get(i).size() == m_len){
               if(s_route.isEmpty()){
                   s_route = (ArrayList<Integer>) ans.get(i).clone();
               }else{
                   for(int idx =0; idx<s_route.size();idx++){
                       if(s_route.get(idx) >ans.get(i).get(idx)){
                           s_route = (ArrayList<Integer>) ans.get(i).clone();
                           break;
                       }
                   }
               }
            }
        }
    }
}
