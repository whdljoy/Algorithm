import java.io.*;
import java.util.*;
public class Solution_공통조상 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int V,E;
    static int v1,v2;
    static Node [] vertex;
    static List <Integer> ancestor_v1;
    static List <Integer> ancestor_v2;
    static class Node{
        List < Integer > children;
        int parents;
        Node (){
            children = new ArrayList<>();
            parents =0;
        }
    }
    static int ans =0;
    static int total=1;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            simulate();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        ancestor_v1 = new ArrayList<>();
        ancestor_v2 = new ArrayList<>();
        vertex = new Node[V+1];
        for(int i=0;i<=V;i++){
            vertex[i] = new Node();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<E;i++){
            int pa = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            vertex[pa].children.add(child);
            vertex[child].parents = pa;
        }
        ans=0;
        total=1;
    }
    static void simulate(){
        set_ancestor(v1,ancestor_v1);
        set_ancestor(v2,ancestor_v2);
        search();
        dfs(ans);
        sb.append(ans).append(" ").append(total).append("\n");
    }
    static void search(){
        for (int i = 0; i < V; i++) {
            if (!ancestor_v1.get(i).equals(ancestor_v2.get(i)))
            {
                break;
            }
            ans = ancestor_v1.get(i);
        }

    }
    static void dfs(int vtx){
        for(int child : vertex[vtx].children){
            total++;
            dfs(child);
        }
    }
    static void set_ancestor(int idx,List <Integer> ancestor){
        int parents= vertex[idx].parents;
        if(parents !=0){
            set_ancestor(parents,ancestor);
        }
        ancestor.add(idx);

    }
}
