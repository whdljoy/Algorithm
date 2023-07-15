import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class problem1707 {
    static ArrayList<Integer>[]  A;
    static int[] check;
    static boolean[] visited;
    static  boolean IsEven;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i=0; i<tc;i++){
            String [] s =br.readLine().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            A = new ArrayList[V+1];
            visited = new boolean[V+1];
            check = new int[V+1];
            IsEven = true;
            for (int b=1; b <=V; b++){
                A[b] = new ArrayList<Integer>();
            }
            for (int j=0; j<E; j++){
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                A[start].add(end);
                A[end].add(start);
            }
            //모든 노드에서 DFS 실행
            for (int a=1; a<=V; a++){
                if(IsEven){
                    DFS(a);
                }else{
                    break;
                }
            }
            if(IsEven){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    private static void DFS(int node) {
        visited[node] = true;
        for(int i: A[node]){
            if(!visited[i]){
                check[i] = (check[node] +1) % 2;
                DFS(i);
            }else{
                if(check[node] == check[i]){
                    IsEven =false;
                }
            }
        }
    }
}
