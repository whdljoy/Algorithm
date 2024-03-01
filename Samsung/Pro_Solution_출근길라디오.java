import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Pro_Solution_출근길라디오 {
    private static UserSolution usersolution = new UserSolution();
    static final int CMD_INIT = 100;
    static final int CMD_DESTROY = 200;
    static final int CMD_UPDATE = 300;
    static final int CMD_UPDATE_TYPE = 400;
    static final int CMD_CALC = 500;
    static final int MAX_N = 100000;
    static int[] mType = new int [MAX_N];
    static int[] mTime = new int [MAX_N];
    static class UserSolution
    {
        int [] road_type;
        int [] base;
        int [] seg ;
        int N;
        int total;
        void init(int N, int M, int mType[], int mTime[])
        {
            // N 지점의 갯수 M 도로 종류 mType 각 구간의 도로의 종류  mTime[]
            road_type = mType;
            base = mTime;
            seg = new int [N*4];
            make_tree(1,0,N-1);
            this.N = N;
        }

        void destroy()
        {

        }
        void make_tree(int node, int s, int e){

            if(s == e){
                seg[node] =base[s];
                return;
            }
            int mid = (s+e)/2;
            make_tree(node*2,s,mid);
            make_tree(node*2+1,mid+1,e);
            seg[node] = seg[node*2] + seg[node*2+1];

        }
        void update(int mID, int mNewTime)
        {
            updateSum(1,0,N-1,mID,mNewTime);
            return;
        }
        void updateSum(int node, int s, int e, int pos, int val) {
            if(s ==e){
                if(e ==pos) {
                    seg[node] = val;
                }
                return;
            }
            int mid = (s + e) / 2;
            if (pos <= mid) {
                updateSum(node * 2, s, mid, pos, val);
            } else {
                updateSum(node * 2 + 1, mid + 1, e, pos, val);
            }
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        void updateEach(int node,int s,int e, int mType,int val){
            if(s ==e){
                if(road_type[s] == mType) {
                    seg[node] =seg[node]*val/256;
                    total+=seg[node];
                }
                return;
            }
            int mid = (s+e)/2;
            updateEach(node*2,s,mid,mType,val);
            updateEach(node*2+1,mid+1,e,mType,val);
            seg[node] = seg[node*2] + seg[node*2+1];
        }
        int updateByType(int mTypeID, int mRatio256)
        {
            total =0;
            updateEach(1,0,N-1,mTypeID,mRatio256);
            return total;
        }

        int calculate(int mA, int mB)
        {

            int start =0; int end=0;
            if(mA >mB){
                start =mB;
                end =mA-1;
            }else{
                start =mA;
                end = mB-1;
            }
            int result = search(1,start,end,0,N-1);
            return result;
        }

        int search(int node,int start,int end, int s ,int e ){
            int sum = 0;
            while (s <= e) {
                if (s >= start && e <= end) {
                    sum += seg[node];
                    break;
                }
                int mid = (s + e) / 2;
                if (start <= mid) {
                    sum += search(node * 2, start, end, s, mid);
                }
                if (end > mid) {
                    sum += search(node * 2 + 1, start, end, mid + 1, e);
                }
                break;
            }
            return sum;
        }
    }
    private static boolean run(BufferedReader br) throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        boolean isOK = false;
        int C = new Scanner(st.nextToken()).nextInt();
        int cmd, result, check;
        int N, M;
        int mID, mTypeID, mNewTime, mRatio256;
        int mA, mB;
        for (int c = 0; c < C; ++c) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd)
            {
                case CMD_INIT:
                    N = new Scanner(st.nextToken()).nextInt();
                    M = new Scanner(st.nextToken()).nextInt();
                    for (int i = 0; i < N - 1; i++) mType[i] = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < N - 1; i++) mTime[i] = Integer.parseInt(st.nextToken());
                    usersolution.init(N, M, mType, mTime);
                    isOK = true;
                    break;
                case CMD_UPDATE:
                    mID = Integer.parseInt(st.nextToken());
                    mNewTime = Integer.parseInt(st.nextToken());
                    usersolution.update(mID, mNewTime);
                    break;
                case CMD_UPDATE_TYPE:
                    mTypeID = new Scanner(st.nextToken()).nextInt();
                    mRatio256 = new Scanner(st.nextToken()).nextInt();
                    result = usersolution.updateByType(mTypeID, mRatio256);
                    check = new Scanner(st.nextToken()).nextInt();
                    //System.out.println(result+" update"+ check);
                    if (result != check)
                        isOK = false;
                    break;
                case CMD_CALC:
                    mA = Integer.parseInt(st.nextToken());
                    mB = Integer.parseInt(st.nextToken());
                    result = usersolution.calculate(mA, mB);
                    check = Integer.parseInt(st.nextToken());
                    //System.out.println(result+" cal"+ check);
                    if (result != check)
                        isOK = false;
                    break;
                default:
                    isOK = false;
                    break;
            }
        }
        usersolution.destroy();
        return isOK;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");
        int TC = Integer.parseInt(line.nextToken());
        int MARK = Integer.parseInt(line.nextToken());
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }
        br.close();
    }
}