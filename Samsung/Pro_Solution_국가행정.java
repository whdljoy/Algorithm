import java.util.Scanner;
import java.util.*;

/**
 * 세그먼트 트리로 구현해보기
 */
class Pro_Solution_국가행정
{
    private static final int CMD_INIT				= 100;
    private static final int CMD_EXPAND				= 200;
    private static final int CMD_CALCULATE			= 300;
    private static final int CMD_DIVIDE				= 400;
    private static final int MAX_N					= 10000;

    private static int[] population = new int[MAX_N];
    private static UserSolution usersolution = new UserSolution();
    private static Scanner sc;
    static class UserSolution
    {
        class Node implements Comparable<Node>{
            int start;
            int end;
            int time;
            Node (int start,int end, int time){
                this.start = start;
                this.end = end;
                this.time = time;
            }
            @Override
            public int compareTo(Node o){
                if(o.time == this.time){
                    return Integer.compare(this.start,o.start);
                }
                return Integer.compare(o.time , this.time);
            }
        }
        int N;
        int [] info;

        int [] seg;
        int [] line;
        PriorityQueue<Node> pq;
        void make_seg(int node,int start, int end){

            if(start == end){
                seg[node] =info[start];
                return;
            }
            int mid = (start+end)/2;
            make_seg(node*2,start,mid);
            make_seg(node*2+1,mid+1,end);
            seg[node] = seg[node*2]+ seg[node*2+1];

        }
        /**
         *  N : 도시의 수 ( 5 ≤ N ≤ 10,000 )
         *
         * mPopulation[] : 각 도시의 인구 수. ( 1 ≤ mPopulation[] ≤ 1,000 )
         * @param N
         * @param mPopulation
         */
        void init(int N, int mPopulation[])
        {
            this.N = N;
            line = new int [N-1];
            seg = new int [N*4];
            Arrays.fill(line,1);
            this.info = mPopulation;
            make_seg(1,0,N-1);
            pq = new PriorityQueue<>();
            for(int i=0;i<N-1;i++){
                int dis = (int) Math.floor((info[i+1]+info[i])/line[i]);
                pq.add(new Node(i,i+1,dis));
            }
            return;
        }

        /**
         * Parameters
         *
         *     M : 도로 확장의 횟수 ( 1 ≤ M ≤ 3 )
         * Return
         *     마지막으로 확장한 도로의 확장 후 이동 시간.
         * @param M
         * @return
         */
        int expand(int M) //5000회
        {
            int time=0;
            for(int i=0;i<M;i++){
                Node cur = pq.poll();
                line[cur.start] +=1;
                cur.time =(int) Math.floor((info[cur.end]+info[cur.start])/line[cur.start]);
                pq.add(cur);
                time = cur.time;
            }
            return time;
        }

        int calculate(int mFrom, int mTo)  //2500회
        {
            int time=0;
            int ma=0;
            int mb =0;
            if(mFrom > mTo){
                ma = mTo;
                mb = mFrom;
            }else{
                ma = mFrom;
                mb = mTo;
            }
            for(int i = ma;i<mb;i++){
                time+= (int) Math.floor((info[i+1]+info[i])/line[i]);
            }
            return time;
        }

        int divide(int mFrom, int mTo, int K)  //300회
        {
            int left = 1; int right = Integer.MAX_VALUE;

            while (left < right) {
                int mid = left + (right - left) / 2;

                int count = 0;
                int sum = 0;

                for (int i = mFrom; i <= mTo; i++) {
                    if (sum + info[i] > mid) {
                        count++;
                        sum = 0;
                    }
                    sum += info[i];
                }

                count++; // 마지막 구간 추가

                if (count <= K) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }
    }
    private static boolean run() throws Exception
    {
        boolean okay = false;
        int Q = sc.nextInt();

        for (int q = 0; q < Q; ++q)
        {
            int ret, ans, N, from, to, num;
            int cmd = sc.nextInt();

            switch(cmd)
            {
                case CMD_INIT:
                    N = sc.nextInt();
                    for (int i = 0; i < N; i++)
                    {
                        int in = sc.nextInt();
                        population[i] = in;
                    }
                    usersolution.init(N, population);
                    okay = true;
                    break;
                case CMD_EXPAND:
                    num = sc.nextInt();
                    ret = usersolution.expand(num);
                    ans = sc.nextInt();
//                    System.out.println("ret" + ret + " ans "+ ans);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_CALCULATE:
                    from = sc.nextInt();
                    to = sc.nextInt();
                    ret = usersolution.calculate(from, to);
                    ans = sc.nextInt();
//                    System.out.println("ret" + ret + " ans "+ ans);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_DIVIDE:
                    from = sc.nextInt();
                    to = sc.nextInt();
                    num = sc.nextInt();
                    ret = usersolution.divide(from, to, num);
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