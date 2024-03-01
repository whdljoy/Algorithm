import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pro_Solution_문자열관리프로그램
{
    private final static int CMD_INIT		= 1;
    private final static int CMD_APPEND		= 2;
    private final static int CMD_CUT		= 3;
    private final static int CMD_REVERSE	= 4;
    private final static int CMD_COUNT		= 5;

    private final static UserSolution usersolution = new UserSolution();

    private static void String2Char(char[] buf, String str) {
        for (int k = 0; k < str.length(); ++k)
            buf[k] = str.charAt(k);
        buf[str.length()] = '\0';
    }

    private static char[] mStr = new char[30001];
    private static char[] mWord = new char[5];
    static class UserSolution
    {
        final int HASH =3;
        StringBuilder sb =new StringBuilder();
        int direction =1;// 정방향
        void init(char mStr[])
        {
            for(char num : mStr){
                if(num == '\0'){
                    break;
                }else{
                    sb.append(num);
                }
            }
        }

        void appendWord(char mWord[])
        {
            if(direction ==1){
                for(int i=0;i<mWord.length;i++){
                    sb.append(mWord[i]);
                }
                for(char num : mStr){
                    if(num == '\0'){
                        break;
                    }else{
                        sb.append(num);
                    }
                }
            }else{
                for(char num : mStr){
                    if(num == '\0'){
                        break;
                    }else{
                        sb.insert(0,num);
                    }
                }
            }

        }

        void cut(int k)
        {
            StringBuilder nw = new StringBuilder();
            String tmp="";
            if(direction ==1){
                tmp =sb.substring(0,sb.length()-k);
            }else{
                tmp =sb.substring(k-1,sb.length());
            }
            nw.append(tmp);
            sb = nw;

        }

        void reverse()
        {
            direction = direction *-1;
        }

        int countOccurrence(char mWord[])
        {
            int cnt =0;
            for(char num : mWord){
                if(num == '\0'){
                    break;
                }else{
                    cnt++;
                }
            }
            int val =0;
            for(int i=cnt-1;i>-1;i--){
                val += (int) (Math.pow(HASH,i) * mWord[cnt-1-i] -'0');

            }
            System.out.println("dir" + direction + "default" + val);
            int result =0;
            if(direction == 1){
                String original =sb.toString();
                String start= sb.substring(0,cnt);
                int num = 0;
                for(int i=0;i<cnt;i++){
                    num = num +(int) (Math.pow(HASH,cnt-1-i) * (start.charAt(i) -'0'));
                }
                if(num == val) result+=1;

                // 기본 셋팅

                for(int i=cnt; i<original.length();i++){
                    int last = (original.charAt(i-cnt)-'0');
                    num = (int) (HASH * (num - last * Math.pow(HASH,cnt-1))) + (original.charAt(i)-'0');

                    if(num == val) result+=1;
                }

            }else{
                String original =sb.toString();
                String start= sb.substring(sb.length()-cnt-1,sb.length());
                int num = 0;
                for(int i=0;i<cnt;i++){
                    num += (int) (Math.pow(HASH,i) * (start.charAt(i) -'0'));
                }
                if(num == val) result+=1;

                for(int i=sb.length()-cnt-2; i>-1;i--){
                    num = HASH * (num -(int)((original.charAt(i+1)-'0') * Math.pow(HASH,cnt-1))) +(original.charAt(i)-'0');
                    if(num == val) result+=1;
                }
            }
            System.out.println(result);
            return result;
        }
    }
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
                String2Char(mStr, st.nextToken());
                usersolution.init(mStr);
                correct = true;
            }
            else if (cmd == CMD_APPEND)
            {
                String2Char(mWord, st.nextToken());

                if (correct)
                {
                    usersolution.appendWord(mWord);
                }
            }
            else if (cmd == CMD_CUT)
            {
                int k = Integer.parseInt(st.nextToken());

                if (correct)
                {
                    usersolution.cut(k);
                }
            }
            else if (cmd == CMD_REVERSE)
            {
                if (correct)
                {
                    usersolution.reverse();
                }
            }
            else if (cmd == CMD_COUNT)
            {
                String2Char(mWord, st.nextToken());

                int ret = -1;
                if (correct)
                {
                    ret = usersolution.countOccurrence(mWord);
                }

                int ans = Integer.parseInt(st.nextToken());
//                if (ret != ans)
//                {
//                    correct = false;
//                }
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