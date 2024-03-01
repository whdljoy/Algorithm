import java.util.*;

class Pro_Solution_신소재케이블2
{
    private static final int CMD_INIT				= 100;
    private static final int CMD_CONNECT			= 200;
    private static final int CMD_MEASURE			= 300;
    private static final int CMD_TEST				= 400;

    private static UserSolution usersolution = new UserSolution();
    static class UserSolution {
        class Node implements Comparable<Node> {
            int dest;
            int distance;

            Node(int dest, int distance) {
                this.dest = dest;
                this.distance = distance;
            }

            @Override
            public int compareTo(Node o) {
                return Integer.compare(o.distance, this.distance);
            }

        }

        HashMap<Integer, Integer> info;
        int cnt;
        ArrayList<ArrayList<Node>> road;
        int [] trace;
        boolean [] visited;
        PriorityQueue < Node> dis;

        public void init(int mDevice) {
            cnt=0;
            info = new HashMap<>();
            info.put(mDevice, cnt);
            road = new ArrayList<>();
            road.add(new ArrayList<>());
            cnt++;
            return;
        }


        public void connect(int mOldDevice, int mNewDevice, int mLatency) {
            int oldD = info.get(mOldDevice);
            int newD = cnt;
            info.put(mNewDevice, newD);
            cnt++;
            ArrayList<Node> newR = new ArrayList<>();
            newR.add(new Node(oldD, mLatency));
            road.add(newR);
            road.get(oldD).add(new Node(newD, mLatency));

            return;
        }

        public int measure(int mDevice1, int mDevice2) {
            boolean[] visited = new boolean[cnt];
            int result = -1;
            int start = info.get(mDevice1);
            int end = info.get(mDevice2);
            visited[start] = true;
            Deque<Integer[]> dq = new ArrayDeque<>();
            dq.add(new Integer[]{start, 0});
            outer:
            while (!dq.isEmpty()) {
                Integer[] cur = dq.poll();
                int point = cur[0];
                int dis = cur[1];
                for (Node nxt : road.get(point)) {
                    if (visited[nxt.dest]) continue;
                    if (nxt.dest == end) {
                        result = dis + nxt.distance;
                        break outer;
                    }
                    visited[nxt.dest] = true;
                    dq.add(new Integer[]{nxt.dest, dis + nxt.distance});
                }
            }

            return result;
        }

        public int test(int mDevice) {
            trace = new int [cnt];
            visited = new boolean[cnt];
            dis = new PriorityQueue<Node>();
            bfs(mDevice);
            Node far =dis.poll();
            int result = far.distance;
            int start =far.dest;

            visited = new boolean[cnt];
            while(true){
                if(start ==info.get(mDevice)){
                    break;
                }
                visited[start] =true;
                start = trace[start];
            }
            dis.clear();
            bfs(mDevice);
            result += dis.isEmpty() ? 0 : dis.poll().distance;
            return result;

        }

        void bfs(int mDevice){
            int start = info.get(mDevice);
            visited[start] = true;
            Deque<Integer[]> dq = new ArrayDeque<>();
            dq.add(new Integer[]{start, 0});
            while (!dq.isEmpty()) {
                Integer[] cur = dq.poll();
                int point = cur[0];
                int dist = cur[1];
                for (Node nxt : road.get(point)) {
                    if (visited[nxt.dest]) continue;
                    visited[nxt.dest] = true;
                    trace[nxt.dest] =point;
                    dis.add(new Node(nxt.dest,dist + nxt.distance));
                    dq.add(new Integer[]{nxt.dest, dist + nxt.distance});
                }
            }

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