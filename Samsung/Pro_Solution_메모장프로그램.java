import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_메모장프로그램
{
    private final static int CMD_INIT       = 100;
    private final static int CMD_INSERT     = 200;
    private final static int CMD_MOVECURSOR = 300;
    private final static int CMD_COUNT      = 400;

    private final static UserSolution usersolution = new UserSolution();

    static class UserSolution
    {
        List <Integer> [] info;
        int H;
        int W;
        int r_cursor;
        int c_cursor;

        HashMap <Character, Integer> [] cnt;
        void init(int H, int W, char mStr[])
        {
            this.H =H;
            this.W =W;
            info = new ArrayList[H];
            for(int i=0;i<H;i++){
                info[i] = new ArrayList<>();
            }
            cnt = new HashMap[H];
            for(int i=0; i<H;i++){
                cnt[i] = new HashMap<>();
            }
            for(int i=0; i<mStr.length;i++){
                if(mStr[i] != '\0'){
                    int high =i/W;
                    info[high].add((int)mStr[i]);
                    if(cnt[high].containsKey(mStr[i])){
                        cnt[high].put(mStr[i],cnt[high].get(mStr[i])+1);
                    }else{
                        cnt[high].put(mStr[i],1);
                    }
                }
            }
            c_cursor = 0;
            r_cursor = 0;
        }

        void insert(char mChar)
        {
            int row = r_cursor;
            if(cnt[row].containsKey(mChar)){
                cnt[row].put(mChar,cnt[row].get(mChar)+1);
            }else{
                cnt[row].put(mChar,1);
            }
            info[row].add(c_cursor,(int)mChar);
            c_cursor+=1;
            if(c_cursor == W){
                r_cursor+=1;
                c_cursor=0;
            }

            while (info[row].size() > W) {
                char c = (char)info[row].get(info[row].size()-1).intValue();
                info[row+1].add(0,info[row].get(info[row].size()-1));
                info[row].remove(info[row].size()-1);


                cnt[row].put(c,cnt[row].get(c)-1);

                if(cnt[row+1].containsKey(c)){
                    cnt[row+1].put(c,cnt[row+1].get(c)+1);
                }else{
                    cnt[row+1].put(c,1);
                }
                row++;
            }
        }


        char moveCursor(int mRow, int mCol)
        {
            int col = mCol-1;
            int row = mRow -1;
            if(info[row].isEmpty()){
                while(info[row].isEmpty()){
                    row--;
                }
                if(info[row].size() == W){
                    r_cursor = row+1;
                    c_cursor =0;
                }else{
                    r_cursor = row;
                    c_cursor = info[row].size();
                }
                return '$';
            }
            if(info[row].size()<mCol){
                r_cursor = row;
                c_cursor = info[row].size();
                return '$';
            }
            r_cursor = row;
            c_cursor = col;
            return (char)info[row].get(col).intValue();

        }

        int countCharacter(char mChar)
        {
            int result =0;
            ArrayList <Integer> cur = (ArrayList<Integer>) info[r_cursor];
            for(int i=c_cursor; i<cur.size();i++){
                char c =(char) cur.get(i).intValue();
                if(c == mChar){
                    result++;
                }
            }

            for(int i=r_cursor+1;i<H;i++){
                if(cnt[i].containsKey(mChar)){
                    result += cnt[i].get(mChar);
                }
            }
            return result;
        }
    }
    private static void String2Char(char[] buf, String str, int maxLen)
    {
        for (int k = 0; k < str.length(); k++)
            buf[k] = str.charAt(k);

        for (int k = str.length(); k <= maxLen; k++)
            buf[k] = '\0';
    }

    private static char[] mStr = new char[90001];

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int queryCnt = Integer.parseInt(st.nextToken());
        boolean correct = false;

        for (int q = 0; q < queryCnt; q++)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == CMD_INIT)
            {
                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());

                String2Char(mStr, st.nextToken(), 90000);

                usersolution.init(H, W, mStr);
                correct = true;
            }
            else if (cmd == CMD_INSERT)
            {
                char mChar = st.nextToken().charAt(0);

                usersolution.insert(mChar);
            }
            else if (cmd == CMD_MOVECURSOR)
            {
                int mRow = Integer.parseInt(st.nextToken());
                int mCol = Integer.parseInt(st.nextToken());

                char ret = usersolution.moveCursor(mRow, mCol);

                char ans = st.nextToken().charAt(0);
                if (ret != ans)
                {
                    correct = false;
                }
            }
            else if (cmd == CMD_COUNT)
            {
                char mChar = st.nextToken().charAt(0);

                int ret = usersolution.countCharacter(mChar);

                int ans = Integer.parseInt(st.nextToken());
                if (ret != ans)
                {
                    correct = false;
                }
            }
        }
        return correct;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;

            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}