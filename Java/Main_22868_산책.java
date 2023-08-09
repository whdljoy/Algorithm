import java.util.*;
import java.io.*;

public class Main_22868_산책 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;  //정점갯수
    static int M; //간선갯수
    static int start;
    static int end;
    static boolean []check;
    static ArrayList<ArrayList<Integer>> info;
    static int ans;
    static int []route;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws  Exception{
        run();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /**
     *  1 -> 2 -> 4 -> 3 -> 1
     *
     *  예시로 들면 bfs 처음 하면 route 1부터 4까지 배열을 본다면 route [2]에 1 이 route [4] 에 2가들어있고 종료된다 즉 1은 시작지점이기때문에 2개만포함됨
     *   get_route 에 의해   check  [2] 만 true 가 된다 1,4는 시작과 종료지점이고 이는 bfs 에서 !check[cur_node]에 들어가야 하기 때문에 포함해주지 않는다.
     *   그후 경로 초기화후
     *   다시 돌아오는 bfs 를 한다면  route[3] 에  4 route [1] 에 3이 들어오게 된다. 결국 에는 이문제는 구간 사이의 거리가 주어지는 것이 돌아오는 경로이기때문에 총 거리는 이런 0이아닌 route의 갯수가 된다.
     *   결국 우리는 저 화살표의 갯수를 찾는 것이다 bfs 예시에서 보듯이 2에는 1 4에는 2 3에는 4 1에는 3  이 흐름은 전부 화살표의 흐름을 보여주는 것이고 이것의 총 갯수는 정답이 된다.
     */
    public static void run()throws  Exception{
        ans =0;
        input();
        check = new boolean[N+1];
        route = new int[N+1];
        bfs(start,end);  //시작부터 끝점까지 s -> E 까지 경로구함
        check = new boolean[N+1];
        get_route(end);  // 시작과 끝점 사이의 경로 check 표시 및 구간 구하기
        route = new int[N+1];
        bfs(end,start); // 끝점부터 시작 까지 E-> S
        get_route(start);
        sb.append(ans);
    }
    public static void bfs(int num,int target){ //bfs
        Queue <Integer> q  = new ArrayDeque<>();
        q.add(num);
        check[num] = true;
        while(!q.isEmpty()){
            int cur =q.poll();
            ArrayList<Integer> total = info.get(cur);
            Collections.sort(total);  //사전순이기 때문에 정렬해줌
            for(int i=0; i<total.size();i++){
                int cur_node = total.get(i);
                if(!check[cur_node]){
                    check[cur_node]= true;
                    route[cur_node] = cur;  // 이전에 왔던 경로를 알기 위해 이전 값을 노드의 값을 저장해줌
                    q.add(cur_node);
                    if(cur_node == target){
                        return;
                    }
                }
            }
        }
    }
    public static void get_route( int target){  //경로 사이에 왔던 점을 찾아서 check 배열에 표시해주기
        int idx =target;
        while(true){
            if(route[idx] ==0){
                break;
            }
            if(route[idx] != end  && route[idx] !=start){  // 시작과 출발점의 경우 check 표시를 해주지 않음 어차피 시작점과 끝점은 bfs 처음에 true를 반환하기도 하고 도착점이 되기때문에 마지막에
                check[route[idx]] = true;
            }
            ans++;
            idx = route[idx];
        }
    }
    public static  void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new ArrayList<>();
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
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

}
