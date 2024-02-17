import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_숫자조각게임 {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int MAX_ROW = 40;
    private final static int MAX_COL = 30;

    private static int[][] mCells = new int[MAX_ROW][MAX_COL];
    private static int[][] mPuzzle = new int[3][3];

    private final static int CMD_INIT = 1;
    private final static int CMD_PUT = 2;
    private final static int CMD_CLR = 3;
    static class UserSolution {
        class Pos implements Comparable<Pos>{
            int x;
            int y;
            Pos (int x, int y){
                this.x =x;
                this.y =y;
            }
            @Override
            public int compareTo(Pos o ){
                if(this.y == o.y){
                    return Integer.compare(this.x,o.x);
                }
                return Integer.compare(this.y,o.y);
            }

        }
        HashMap <String,PriorityQueue<Pos>> info;
        HashMap <String,PriorityQueue<Pos>> cpy;
        boolean [][] visited;
        int row;
        int col;

        ArrayList<int []>[]block;

        public void init(int mRows, int mCols, int mCells[][])
        {
            info = new HashMap<>();
            cpy = new HashMap<>();
            row = mRows;
            col = mCols;
            visited = new boolean [row][col];
            setType();
            for(int y=0;y <row;y++){
                for(int x=0; x<col;x++){
                    for(int t=1; t<=5;t++){
                        boolean flag = true;
                        int min = mCells[y][x];
                        int dx = x;
                        int dy = y;
                        for (int[] cur : block[t]){
                            dx = dx +cur[1];
                            dy = dy +cur[0];
                            if(!in_range(dy,dx)) {
                                flag = false;
                                break;
                            }
                            min = Math.min(min,mCells[dy][dx]);
                        }
                        if(flag){
                            StringBuilder ky = new StringBuilder();
                            ky.append(-t);
                            ky.append(mCells[y][x] - min);
                            int cx = 0;
                            int cy = 0;
                            for(int [] cur : block[t]){
                                cx = cx +cur[1];
                                cy = cy +cur[0];
                                ky.append(mCells[y+cy][x+cx]- min);
                            }
                            String t_key = ky.toString();
                            if(info.containsKey(t_key)){
                                PriorityQueue <Pos > tmp = info.get(t_key);
                                tmp.add(new Pos(x,y));
                                info.put(t_key,tmp);
                            }else{
                                PriorityQueue<Pos> tmp = new PriorityQueue<>();
                                tmp.add(new Pos(x,y));
                                info.put(t_key,tmp);
                            }
                        }
                    }
                }
            }

            for (String key : info.keySet()) {
                PriorityQueue<Pos> originalQueue = info.get(key);
                PriorityQueue<Pos> copiedQueue = new PriorityQueue<>();
                for (Pos pos : originalQueue) {
                    // Pos 객체의 깊은 복사
                    Pos copiedPos = new Pos(pos.x, pos.y);
                    copiedQueue.add(copiedPos);
                }
                cpy.put(key, copiedQueue);
            }


        }
        boolean in_range(int y,int x){
            return 0<=x && x<col && 0<=y &&y<row;
        }
        void setType(){
            block = new ArrayList[6];
            for(int i=1;i<=5;i++){
                block [i] = new ArrayList<>();
            }
            block[1].add(new int [] {0,1}); //0번 타입  y,x


            block[2].add(new int [] {0,1}); // 1번 타입
            block[2].add(new int [] {0,1});

            block[3].add(new int [] {1,0}); // 2번 타입
            block[3].add(new int [] {1,0}); // 2번 타입

            block[4].add(new int [] {0,1}); // 3번 타입
            block[4].add(new int [] {1,0});
            block[4].add(new int [] {0,1});

            block[5].add(new int [] {1,0}); // 4번 타입
            block[5].add(new int [] {0,1});
            block[5].add(new int [] {0,1});
            block[5].add(new int [] {1,0});

        }

        public Result putPuzzle(int mPuzzle[][])
        {
            int tp = searchType(mPuzzle);
            int x=0; int y=0;
            int min = mPuzzle[y][x];
            for(int [] cur : block[tp]){
                y +=cur[0];
                x += cur[1];
                min =Math.min(min,mPuzzle[y][x]);
            }
            int dx=0; int dy=0;
            StringBuilder ky = new StringBuilder();
            ky.append(-tp);
            ky.append(mPuzzle[dy][dx] - min);
            for(int [] cur : block[tp]){
                dy += cur[0];
                dx += cur[1];
                ky.append(mPuzzle[dy][dx]-min);
            }
            Result ret = new Result(-1, -1);
            String t_key =ky.toString();
            if(!cpy.containsKey(t_key)) {
                return ret;
            }
            while(!cpy.get(t_key).isEmpty()){
                Pos cur = cpy.get(t_key).poll();
                Deque <Pos> visit = new ArrayDeque<>();
                int cx= cur.x; int cy= cur.y;
                visit.add(new Pos(cx,cy));
                if(!visited[cy][cx]){
                    boolean flag = true;
                    for(int [] next : block[tp]){
                        cy+= next[0];
                        cx+= next[1];
                        if(visited[cy][cx]){
                            flag= false;
                            break;
                        }
                        visit.add(new Pos(cx,cy));
                    }
                    if(flag){
                        ret = new Result(cur.y,cur.x);
                        while(!visit.isEmpty()){
                            Pos ch= visit.poll();
                            visited[ch.y][ch.x]=true;
                        }
                        break;
                    }
                }
            }
            return ret;
        }

        public void clearPuzzles()
        {
            cpy = new HashMap<>();
            for (String key : info.keySet()) {
                PriorityQueue<Pos> originalQueue = info.get(key);
                PriorityQueue<Pos> copiedQueue = new PriorityQueue<>();
                for (Pos pos : originalQueue) {
                    // Pos 객체의 깊은 복사
                    Pos copiedPos = new Pos(pos.x, pos.y);
                    copiedQueue.add(copiedPos);
                }
                cpy.put(key, copiedQueue);
            }

            visited = new boolean[row][col];
            return;
        }

        public int searchType(int mPuzzle[][]){
            if(mPuzzle[0][2] ==0 && mPuzzle[1][0] ==0){
                if(mPuzzle[1][1]==0){
                    return 1;
                }else{
                    return 4;
                }
            }
            if (mPuzzle[0][2] !=0){
                return 2;
            }
            if (mPuzzle[2][0] != 0){
                return 3;
            }
            return 5;


        }

    }
    static class Result {
        int row;
        int col;

        Result(int r, int c) {
            row = r;
            col = c;
        }
    }

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        int query_num = Integer.parseInt(stdin.nextToken());
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                int mRows = Integer.parseInt(stdin.nextToken());
                int mCols = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < mRows; i++) {
                    stdin = new StringTokenizer(br.readLine(), " ");
                    for (int j = 0; j < mCols; j++) {
                        mCells[i][j] = Integer.parseInt(stdin.nextToken());
                    }
                }
                usersolution.init(mRows, mCols, mCells);
                ok = true;
            } else if (query == CMD_PUT) {
                char strPuzzle[] = stdin.nextToken().toCharArray();
                int cnt = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mPuzzle[i][j] = strPuzzle[cnt] - '0';
                        cnt++;
                    }
                }
                int ans_row = Integer.parseInt(stdin.nextToken());
                int ans_col = Integer.parseInt(stdin.nextToken());
                Result ret = usersolution.putPuzzle(mPuzzle);
                if (ans_row != ret.row || ans_col != ret.col) {
                    ok = false;
                }
            } else if (query == CMD_CLR) {
                usersolution.clearPuzzles();
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }
        br.close();
    }
}