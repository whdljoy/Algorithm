import java.util.*;
import java.io.*;
public class boj2606 {
    static ArrayList<Integer> [] net;
    static boolean [] check;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computer = Integer.parseInt(br.readLine()); //컴퓨터수
        int num = Integer.parseInt(br.readLine()); //컴퓨터 쌍수
        net = new ArrayList[computer+1];
        StringTokenizer st;
        check = new boolean[computer+1];
        Arrays.fill(check,false);
        answer =0;
        for (int i = 0; i < computer + 1; i++) {
            net[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<num; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            net[s].add(e);
            net[e].add(s);
        }
        BFS(1);
        System.out.println(answer);
    }

    private static void BFS(int i) {
        check[i] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int item:net[cur]){
                if (!check[item]){
                    q.add(item);
                    check[item] = true;
                    answer+=1;
                }
            }
        }
    }
}
