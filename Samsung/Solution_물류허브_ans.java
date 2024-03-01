import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

class Solution_물류허브_ans {
    private final static int MAX_N = 1400;
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_COST = 3;


    private final static UserSolution usersolution = new UserSolution();

    static class E implements Comparable<E> {
        int end, weight;

        public E(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(E o) {
            return this.weight - o.weight;
        }
    }

    static class UserSolution {
        HashMap<Integer, Integer> id2idx;
        List<E>[] list, revList;
        int Num;

        public int init(int N, int sCity[], int eCity[], int mCost[]) {

            id2idx = new HashMap<>();
            int idx = 0;

            // index 압축 과정
            for (int i = 0; i < N; i++) {
                if (!id2idx.containsKey(sCity[i]))
                    id2idx.put(sCity[i], idx++);
                if (!id2idx.containsKey(eCity[i]))
                    id2idx.put(eCity[i], idx++);
            }
            Num = id2idx.size();

            list = new List[Num];
            revList = new List[Num];

            for (int i = 0; i < Num; i++) {
                list[i] = new ArrayList<>();
                revList[i] = new ArrayList<>();
            }

            for (int i = 0; i < N; i++) {
                // 정방향 그래프 구성
                list[id2idx.get(sCity[i])].add(new E(id2idx.get(eCity[i]), mCost[i]));
                // 역방향 그래프 구성
                revList[id2idx.get(eCity[i])].add(new E(id2idx.get(sCity[i]), mCost[i]));
            }
            return Num;
        }

        public void add(int sCity, int eCity, int mCost) {
            // 정방향 그래프에 간선 추가하기
            list[id2idx.get(sCity)].add(new E(id2idx.get(eCity), mCost));

            // 역방향 그래프에 간선 추가하기
            revList[id2idx.get(eCity)].add(new E(id2idx.get(sCity), mCost));
        }


        public int cost(int mHub) {
            // mHub 에서 모든 정점까지의 최단 거리
            int[] distance = dijkstra(list, mHub);
            // 모든 정점에서 mHub 까지의 최단 거리
            int[] revdistance = dijkstra(revList, mHub);

            int sum = 0;
            for (int i = 0; i < Num; i++) {
                sum += distance[i];
                sum += revdistance[i];
            }

            return sum;
        }

        private int[] dijkstra(List<E>[] list, int mHub) {
            int X = id2idx.get(mHub);
            boolean visit[] = new boolean[Num];

            int[] distance = new int[Num];

            Arrays.fill(distance, Integer.MAX_VALUE);
            Queue<E> queue = new PriorityQueue<E>();

            queue.add(new E(X, 0));
            distance[X] = 0;

            while (!queue.isEmpty()) {
                E curNode = queue.poll();
                int cur = curNode.end;

                if (visit[cur]) continue;
                visit[cur] = true;

                for (E n : list[cur]) {
                    if (distance[n.end] > distance[cur] + n.weight) {
                        distance[n.end] = distance[cur] + n.weight;
                        queue.add(new E(n.end, distance[n.end]));
                    }
                }
            }
            return distance;
        }

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
