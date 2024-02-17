import java.util.*;

class Pro_Solution_신소재케이블2
{
    private static final int CMD_INIT				= 100;
    private static final int CMD_CONNECT			= 200;
    private static final int CMD_MEASURE			= 300;
    private static final int CMD_TEST				= 400;

    private static UserSolution usersolution = new UserSolution();
    static class UserSolution
    {
        class Node implements Comparable<Node>{
            int dest;
            int distance;

            Node (int dest, int distance){
                this.dest = dest;
                this.distance = distance;
            }
            @Override
            public int compareTo(Node o){
                return Integer.compare(o.distance,this.distance);
            }

        }
        HashMap <Integer, Integer> info;
        int cnt;
        ArrayList<ArrayList <Node>> road;
        public void init(int mDevice)
        {
            info = new HashMap<>();
            info.put(mDevice,cnt);
            road = new ArrayList<>();
            road.add(new ArrayList<>());
            cnt ++;
            return;
        }


        public void connect(int mOldDevice, int mNewDevice, int mLatency)
        {
            int oldD = info.get(mOldDevice);
            int newD = cnt;
            info.put(mNewDevice,cnt);
            cnt ++;
            ArrayList <Node> newR = new ArrayList<>();
            newR.add(new Node(oldD,mLatency));
            road.add(newR);
            road.get(oldD).add(new Node(newD,mLatency));

            return;
        }

        public int measure(int mDevice1, int mDevice2)
        {
            boolean [] visited = new boolean[cnt];
            int result =-1;
            int start = info.get(mDevice1);
            int end = info.get(mDevice2);
            visited[start] = true;
            Deque <Integer [] > dq = new ArrayDeque<>();
            dq.add(new Integer[] {start ,0});
            outer : while(!dq.isEmpty()){
                Integer [] cur = dq.poll();
                int point = cur[0]; int dis = cur[1];
                for(Node nxt : road.get(point)){
                    if(visited[nxt.dest]) continue;
                    if(nxt.dest == end){
                        result = dis + nxt.distance;
                        break outer;
                    }
                    visited[nxt.dest] = true;
                    dq.add(new Integer[] { nxt.dest, dis+ nxt.distance});
                }
            }
           // System.out.println("result"+result);
            return result;
        }

        public int test(int mDevice)
        {
            int [] dist = new int [cnt];
            boolean [] visited = new boolean [cnt];
            ArrayList [] root = new ArrayList[cnt];
            for(int i=0; i<cnt;i++){
                root[i] = new ArrayList<Integer>();
            }
            Arrays.fill(dist,Integer.MAX_VALUE);
            PriorityQueue <Node> pq = new PriorityQueue<>();
            int start = info.get(mDevice);
            root[start] = new ArrayList<Integer>();
            pq.add(new Node(start,0));
            dist [start] = 0;
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                if(visited[cur.dest]) continue;

                visited[cur.dest]= true;

                for(Node nxt : road.get(cur.dest)){
                    if(dist[nxt.dest] > dist[cur.dest] + nxt.distance){
                        dist[nxt.dest] = dist[cur.dest] + nxt.distance;
                        ArrayList <Integer> cpy = (ArrayList<Integer>) root[cur.dest];
                        cpy.add(nxt.dest);
                        root[nxt.dest] = cpy;
                        pq.add(new Node(nxt.dest,dist[nxt.dest]));
                    }
                }
            }
            int idx =-1;
            int max = Integer.MIN_VALUE;
            for(int i=0; i<cnt;i++){
                if(max < dist[i] && dist[i] != Integer.MAX_VALUE){
                    max = dist[i];
                    idx=i;
                }
            }
            System.out.println("first"+ max);
            boolean []  checked = new boolean[cnt];
            for(int i=0;i<root[idx].size();i++){
                System.out.println((int) root[idx].get(i));
                checked[(int) root[idx].get(i)]= true;
            }
            max += another(mDevice,checked);
            System.out.println(max);
            return max;
        }
        public int another(int mDevice, boolean [] checked){
            int [] dist = new int [cnt];
            Arrays.fill(dist,Integer.MAX_VALUE);
            PriorityQueue <Node> pq = new PriorityQueue<>();
            int start = info.get(mDevice);
            dist [start] = 0;
            pq.add(new Node(start,0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                if(checked[cur.dest]) continue;

                checked[cur.dest]= true;

                for(Node nxt : road.get(cur.dest)){
                    if(dist[nxt.dest] > dist[cur.dest] + nxt.distance){
                        dist[nxt.dest] = dist[cur.dest] + nxt.distance;
                        pq.add(new Node(nxt.dest,dist[nxt.dest]));
                    }
                }
            }
            int max = Integer.MIN_VALUE;
            for(int i=0; i<cnt;i++){
                if(max < dist[i] && dist[i] != Integer.MAX_VALUE){
                    max = dist[i];
                }
            }
            return max;
        }
    }

    private static boolean run(Scanner sc) throws Exception
    {
        int Q;
        int mDevice, mOldDevice, mNewDevice, mDevice1, mDevice2;
        int mLatency;

        int ret = -1, ans;

        Q = sc.nextInt();

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            int cmd = sc.nextInt();

            switch(cmd)
            {
                case CMD_INIT:
                    mDevice = sc.nextInt();
                    usersolution.init(mDevice);
                    okay = true;
                    break;
                case CMD_CONNECT:
                    mOldDevice = sc.nextInt();
                    mNewDevice = sc.nextInt();
                    mLatency = sc.nextInt();
                    usersolution.connect(mOldDevice, mNewDevice, mLatency);
                    break;
                case CMD_MEASURE:
                    mDevice1 = sc.nextInt();
                    mDevice2 = sc.nextInt();
                    ret = usersolution.measure(mDevice1, mDevice2);
                    ans = sc.nextInt();
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_TEST:
                    mDevice = sc.nextInt();
                    ret = usersolution.test(mDevice);
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

        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        int MARK = sc.nextInt();

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(sc) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        sc.close();
    }
}