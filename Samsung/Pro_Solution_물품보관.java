import java.util.Scanner;
import java.util.*;
class Pro_Solution_물품보관
{
    private static final int CMD_INIT 			= 100;
    private static final int CMD_STOCK 			= 200;
    private static final int CMD_SHIP 			= 300;
    private static final int CMD_GET_HEIGHT		= 400;

    private static UserSolution usersolution = new UserSolution();
    static class UserSolution
    {
        int [] seg;
        int N;
        int [] info;
        public void init(int N)
        {
            this.N = N;
            seg = new int [N*4];
            info =  new int [N+1];
            return;
        }
        void update(int node,int s,int e, int pos, int val){

            if(pos <s || e<pos){
                return;
            }
            if(s == e){
                seg[node] +=val;
                return;
            }
            int mid = (s+e)/2;
            update(node *2,s,mid,pos,val);
            update(node*2+1,mid+1,e,pos,val);
            seg[node] = Math.max(seg[node*2],seg[node*2+1]);
        }
        int max_seg(int node, int s, int e){
            if (s == e) {
                return s;
            }
            int mid = (s + e) / 2;
            if (seg[node * 2] >= seg[node * 2 + 1]) {
                return max_seg(node * 2, s, mid);
            } else {
                return max_seg(node * 2 + 1, mid + 1, e);
            }
        }
        int getLowIdx(int idx, int node, int s, int e) {
            if (e < idx || seg[node] <= info[idx]) return N;
            if (s == e) {
                return s;
            }
            int mid = (s + e) / 2;
            int tmp = getLowIdx(idx, node * 2, s, mid);
            if (tmp != N) return tmp;
            return getLowIdx(idx, node * 2 + 1, mid + 1, e);
        }

        int getLargeIdx(int idx, int node, int s, int e) {
            if (idx < s || seg[node] <= info[idx]) return -1;
            if (s == e) {
                return s;
            }
            int mid = (s + e) / 2;
            int tmp = getLargeIdx(idx, node * 2 + 1, mid + 1, e);
            if (tmp != -1) return tmp;
            return getLargeIdx(idx, node * 2, s, mid);
        }
        int cal(){
            int result = 0;
            int mid = max_seg(1,0,N-1);
            for (int pos = 0; pos < mid;) {
                int nt = Math.min(getLowIdx(pos,1,0,N-1), mid) - 1;
                result += info[pos] * (nt - pos + 1);
                pos = nt + 1;
            }
            for (int pos = N-1 ; pos >= mid;) {
                int nt = Math.max(getLargeIdx(pos,1,0,N-1)+1, mid) ;
                result += info[pos] * (pos - nt + 1);
                pos = nt - 1;
            }
            return result;
        }

        public int stock(int mLoc, int mBox)
        {
            info[mLoc-1] += mBox;
            update(1,0,N-1,mLoc-1,mBox);
            int result = cal();
            return result;
        }

        public int ship(int mLoc, int mBox)
        {
            info[mLoc-1] -= mBox;
            update(1,0,N-1,mLoc-1,-mBox);
            int result =cal();
            return result;
        }

        public int getHeight(int mLoc)
        {
            return info[mLoc-1];
        }
    }
    private static boolean run(Scanner sc) throws Exception
    {
        int Q;
        int N, mLoc, mBox;

        int ret = -1, ans;

        Q = sc.nextInt();

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            int cmd;

            cmd = sc.nextInt();
            switch(cmd)
            {
                case CMD_INIT:
                    N = sc.nextInt();
                    usersolution.init(N);
                    okay = true;
                    break;
                case CMD_STOCK:
                    mLoc = sc.nextInt();
                    mBox = sc.nextInt();
                    ret = usersolution.stock(mLoc, mBox);
                    ans = sc.nextInt();
                    if (ans != ret)
                        okay = false;
                    break;
                case CMD_SHIP:
                    mLoc = sc.nextInt();
                    mBox = sc.nextInt();
                    ret = usersolution.ship(mLoc, mBox);
                    ans = sc.nextInt();
                    if (ans != ret)
                        okay = false;
                    break;
                case CMD_GET_HEIGHT:
                    mLoc = sc.nextInt();
                    ret = usersolution.getHeight(mLoc);
                    ans = sc.nextInt();
                    if (ans != ret)
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