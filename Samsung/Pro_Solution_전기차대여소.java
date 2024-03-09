import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_전기차대여소
{
    private static final int CMD_INIT				= 0;
    private static final int CMD_ADD				= 1;
    private static final int CMD_DISTANCE			= 2;
    private static final int MAX_N					= 350;

    private static int[][] region = new int[MAX_N][MAX_N];
    private static UserSolution usersolution = new UserSolution();
    private static Scanner sc;
    static class UserSolution
    {
        class Node implements Comparable<Node>{
            int point;
            int dis;
            Node(int point,int dis){
                this.point  = point;
                this.dis = dis;
            }
            @Override
            public int compareTo(Node o){
                return Integer.compare(this.dis,o.dis);
            }

        }
        int range;
        int N;
        int [][] status;
        int [][] id;
        ArrayList<Node> [] info;
        void init(int N, int mRange, int mMap[][])
        {
            this.range = mRange;
            this.N = N;
            this.status = mMap;
            info =  new ArrayList[201];
            id = new int[N][N];
            for(int i=0;i<=200;i++){
                info[i] = new ArrayList<Node>();
            }
            return;
        }

        void add(int mID, int mRow, int mCol)
        {
            int [] dx= {-1,0,1,0};
            int [] dy = {0,-1,0,1};
            status [mRow][mCol] = 2;
            id [mRow][mCol] = mID;
            boolean [][] visited = new boolean[N][N];
            visited [mRow][mCol] =true;
            Deque <Integer [] > q = new ArrayDeque<>();
            q.add(new Integer[] {mRow,mCol,0});
            while(!q.isEmpty()){
                Integer [] cur = q.poll();
                int y = cur[0]; int x =cur[1]; int dis = cur[2];
                for(int i=0; i<4;i++){
                    int cx = x +dx[i];
                    int cy = y + dy[i];
                    if(!in_range(cy,cx) || visited[cy][cx]) continue;
                    if(status[cy][cx] == 1) continue;
                    if(dis+1 >range) continue;

                    q.add(new Integer[] {cy,cx,dis+1});
                    visited[cy][cx] = true;

                    if(status[cy][cx] ==2){
                        info[mID].add(new Node(id[cy][cx],dis+1));
                        info[id[cy][cx]].add(new Node(mID,dis+1));
                    }
                }
            }
            return;
        }
        boolean in_range (int y, int x){
            return 0<=x && x<N&&0<=y && y<N;
        }

        int distance(int mFrom, int mTo)
        {
            PriorityQueue <Node> pq = new PriorityQueue<>();
            int [] distance = new int[201];
            Arrays.fill(distance,Integer.MAX_VALUE);
            distance[mFrom] =0;
            boolean [] visited =new boolean[201];
            pq.add(new Node(mFrom,0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                if(visited[cur.point]) continue;
                visited[cur.point]=true;
                for(Node nxt : info[cur.point]){

                    if(distance[nxt.point] > cur.dis+nxt.dis){
                        distance[nxt.point] = cur.dis +nxt.dis;
                        pq.add(new Node(nxt.point,distance[nxt.point]));
                    }
                }
            }
            if(distance[mTo] == Integer.MAX_VALUE){
                return -1;
            }
            return distance[mTo];
        }
    }
    private static boolean run() throws Exception
    {
        int Q = sc.nextInt();

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            int cmd = sc.nextInt();
            int ret, ans, N, range, id, r, c, id2;

            switch(cmd)
            {
                case CMD_INIT:
                    N = sc.nextInt();
                    range = sc.nextInt();
                    for (int i = 0; i < N; i++)
                    {
                        for (int j = 0; j < N; j++)
                        {
                            int in = sc.nextInt();
                            region[i][j] = in;
                        }
                    }
                    usersolution.init(N, range, region);
                    okay = true;
                    break;
                case CMD_ADD:
                    id = sc.nextInt();
                    r = sc.nextInt();
                    c = sc.nextInt();

                    usersolution.add(id, r, c);
                    break;
                case CMD_DISTANCE:
                    id = sc.nextInt();
                    id2 = sc.nextInt();
                    ret = usersolution.distance(id, id2);
                    ans = sc.nextInt();
                    if (ret != ans)
                        okay = false;
                    break;
                default:
                    okay = false;
                    break;
            }

        }

        return okay;
    }

    public static void main(String[] args) throws Exception
    {
         System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        sc = new Scanner(System.in);

        int TC = sc.nextInt();
        int MARK = sc.nextInt();

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run() ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        sc.close();
    }
}