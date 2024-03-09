import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_단어검색 {
    private final static int MAX_LEN = 30;
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_REMOVE = 3;
    private final static int CMD_SEARCH = 4;

    private final static UserSolution usersolution = new UserSolution();


    static class UserSolution {
        static class Node{
            Node [] childNode = new Node [26];
            int cnt;
        }

        Node current;
        boolean isRemove;
        int result;
        void init() {
            current  =new Node();
            return;
        }
        int insert(String word){
            Node thisNode = current;
            for(int i=0; i< word.length();i++){
                int cur = word.charAt(i) - 'a';
                if(thisNode.childNode[ cur] == null){
                    thisNode.childNode[ cur ] = new Node();
                }
                thisNode = thisNode.childNode[cur];
            }
            thisNode.cnt++;
            return thisNode.cnt;
        }

        void delete(char[] mStr, boolean removed){
            result = 0;
            isRemove = removed;
            char cur = mStr[0];
            if(cur == '?'){
                for(int i=0; i<26;i++){
                    delete_recu(current.childNode[i], 0, mStr);
                }
            }else{
                delete_recu(current.childNode[cur - 'a'], 0, mStr);
            }
        }
        void delete_recu(Node start, int idx, char[] mStr){
            if(start != null){
                char nxt = mStr[idx+1];
                if(nxt == '?'){
                    for(int i=0; i<26;i++){
                        delete_recu(start.childNode[i], idx+1, mStr);
                    }
                }
                else if(nxt == '\0'){
                    result += start.cnt;
                    if(isRemove){
                        start.cnt = 0;
                    }
                }else{
                    delete_recu(start.childNode[nxt -'a'], idx+1, mStr);
                }
            }
        }



        int add(char str[]) {
            String tmp ="";
            for(char cur : str){
                if(cur =='\0'){
                    break;
                }
                tmp += cur;
            }

            return insert(tmp);
        }

        int remove(char str[]) {
            delete(str,true);
            return result;
        }

        int search(char str[]) {
              delete(str,false);
            return result;
        }
    }
    private static void String2Char(char[] buf, String str) {
        for (int i = 0; i < str.length(); ++i)
            buf[i] = str.charAt(i);
        buf[str.length()] = '\0';
    }

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        char[] str = new char[MAX_LEN + 1];
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    okay = true;
                    break;
                case CMD_ADD:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.add(str);
                    if (ret != ans){
                    System.out.println("add   " + "res  " +ret + "ans   " +ans );
                        okay = false;
                    }

                    break;
                case CMD_REMOVE:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.remove(str);
                    if (ret != ans){
              System.out.println("remove   " + "res  " +ret + "ans   " +ans );
                        okay = false;
                    }
                    break;
                case CMD_SEARCH:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.search(str);
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