import java.util.Scanner;
import java.util.*;

class Solution_물류허브 {
    private final static int MAX_N = 1400;
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_COST = 3;


    private final static UserSolution usersolution = new UserSolution();

    static  HashMap <Integer,Integer> idx;
    static List [] cityToHub;
    static List [] hubToCity;
    static class Node {
        int dest;
        int distance;
        Node (int dest, int distance){
            this.dest=dest;
            this.distance = distance;
        }
    }
    static class UserSolution {
        public int init(int N, int sCity[], int eCity[], int mCost[]) {
            int cnt=0;
            idx = new HashMap<Integer,Integer>();
            for(int i=0;i<N;i++){
                if (!idx.containsKey(sCity[i]))
                    idx.put(sCity[i], cnt++);
                if (!idx.containsKey(eCity[i]))
                    idx.put(eCity[i], cnt++);
            }
            cityToHub= new ArrayList[idx.size()];
            hubToCity = new ArrayList[idx.size()];
            for(int i=0;i<idx.size();i++){
                cityToHub[i] = new ArrayList<Node>();
                hubToCity[i] = new ArrayList<Node>();
            }
            for(int i=0; i<N;i++){
                int s = idx.get(sCity[i]);
                int e = idx.get(eCity[i]);
                int val = mCost[i];
                hubToCity[s].add(new Node(e,val));
                cityToHub[e].add(new Node(s,val)); //reverse
            }
            return idx.size();
        }

        public void add(int sCity, int eCity, int mCost) {
            hubToCity[idx.get(sCity)].add(new Node(idx.get(eCity),mCost));
            cityToHub[idx.get(eCity)].add(new Node(idx.get(sCity),mCost));
            return;
        }

        public int cost(int mHub) {
            int  [] first =shortest(hubToCity,mHub);
            int [] second = shortest(cityToHub,mHub);
            int total =0;
            for(int i=0;i<idx.size();i++){
                total += first[i] ;
                total += second[i];
            }

            return total;
        }
    }

    static int [] shortest(List <Node>[] cities, int num){
        int start = idx.get(num);
        int [] dist = new int [idx.size()];
        boolean visit[] = new boolean[idx.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[start] = 0;

        PriorityQueue <Node > pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (visit[cur.dest])
                continue;

            visit[cur.dest] = true;
            for(Node next : cities[cur.dest]){
                if(dist[next.dest] > dist[cur.dest] + next.distance ){
                    dist[next.dest ] = dist[cur.dest] + next.distance;
                    pq.add(new Node(next.dest,dist[next.dest]));
                }
            }

        }
        return dist;
    }

    private static boolean run(Scanner sc) {
        int q = sc.nextInt();

        int n;
        int[] sCityArr = new int[MAX_N];
        int[] eCityArr = new int[MAX_N];
        int[] mCostArr = new int[MAX_N];
        int sCity, eCity, mCost, mHub;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            cmd = sc.nextInt();
            switch (cmd) {
                case CMD_INIT:
                    okay = true;
                    n = sc.nextInt();
                    for (int j = 0; j < n; ++j) {
                        sCityArr[j] = sc.nextInt();
                        eCityArr[j] = sc.nextInt();
                        mCostArr[j] = sc.nextInt();
                    }
                    ans = sc.nextInt();
                    ret = usersolution.init(n, sCityArr, eCityArr, mCostArr);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_ADD:
                    sCity = sc.nextInt();
                    eCity = sc.nextInt();
                    mCost = sc.nextInt();
                    usersolution.add(sCity, eCity, mCost);
                    break;
                case CMD_COST:
                    mHub = sc.nextInt();
                    ans = sc.nextInt();
                    ret = usersolution.cost(mHub);
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

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);

        TC = sc.nextInt();
        MARK = sc.nextInt();

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(sc) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        sc.close();
    }
}