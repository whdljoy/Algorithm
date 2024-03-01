import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_상품권배분 {
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_REMOVE = 3;
    private final static int CMD_DISTRIBUTE = 4;

    private final static UserSolution usersolution = new UserSolution();
    static class UserSolution {
        int count=0;
        int N;

        HashMap <Integer,Integer> info;
        HashMap <Integer, Node> group;
        class Node{
            int people;
            int child;
            int parent;
            boolean dead;
            Node(int people,int child,int parent){
                this.people = people;
                this.child= child;
                this.parent= parent;
                this.dead = false;
            }
        }
        public void init(int N, int mId[], int mNum[]) {
            this.N= N;
            count=0;
            info = new HashMap<>();
            group = new HashMap<>();
            for(int i=0;i<mId.length;i++){
                info.put(mId[i],count);
                group.put(count,new Node(mNum[i],0,-1));
                count++;
            }
            return;
        }

        public int add(int mId, int mNum, int mParent) {
            int number = info.get(mParent);
            Node current = group.get(number);
            if(current.child >=3) return -1;

            int cur_id = count++;
            info.put(mId,cur_id);
            group.put(cur_id,new Node(0,0,number));
            current.child++;
            group.put(number,current);

            while(cur_id != -1){
                group.get(cur_id).people += mNum;
                cur_id = group.get(cur_id).parent;
            }

            return group.get(number).people;
        }

        public int remove(int mId) {
            if(!info.containsKey(mId)){
                return -1;
            }
            int cur_id = info.get(mId);
            while(cur_id != -1){
                if(group.get(cur_id).dead){
                    return -1;
                }
                cur_id = group.get(cur_id).parent;
            }
            int renew_id = info.get(mId);
            group.get(renew_id).dead = true;
            group.get(group.get(renew_id).parent).child -=1;

            int mNum = group.get(renew_id).people;
            while(renew_id != -1){
                group.get(renew_id).people -=mNum;
                renew_id= group.get(renew_id).parent;
            }

            return mNum;
        }

        public int distribute(int K) {
            int [] number = new int [N];
            int total=0;
            for(int i=0;i<N;i++){
                number [i] = group.get(i).people;
                total+= number[i];
            }
            Arrays.sort(number);
            if(total <= K){
                return number[N-1];
            }
            for (int i = N - 1; i >= 0; i--) {
                total -= number[i];
                int L = (K - total) / (N - i);
                if ((i - 1 >= 0 ? number[i - 1] : 0) <= L) {
                    return L;
                }
            }
            return 0;
        }
    }
    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int[] midArr = new int[1000];
        int[] mnumArr = new int[1000];
        int mid, mnum, mparent, n, k;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    n = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < n; ++j) {
                        StringTokenizer dep = new StringTokenizer(br.readLine(), " ");
                        midArr[j] = Integer.parseInt(dep.nextToken());
                        mnumArr[j] = Integer.parseInt(dep.nextToken());
                    }
                    usersolution.init(n, midArr, mnumArr);
                    okay = true;
                    break;
                case CMD_ADD:
                    mid = Integer.parseInt(st.nextToken());
                    mnum = Integer.parseInt(st.nextToken());
                    mparent = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.add(mid, mnum, mparent);
               //     System.out.println("add" + ret+" "+ans);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_REMOVE:
                    mid = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.remove(mid);
             //       System.out.println("remove" + ret+" "+ans);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_DISTRIBUTE:
                    k = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.distribute(k);
                //    System.out.println("distri" + ret+" "+ans);
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

        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}