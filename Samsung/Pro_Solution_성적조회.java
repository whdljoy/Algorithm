import java.io.*;
import java.util.*;
class Pro_Solution_성적조회 {
    private final static int CMD_INIT = 100;
    private final static int CMD_ADD = 200;
    private final static int CMD_REMOVE = 300;
    private final static int CMD_QUERY = 400;

    private final static UserSolution usersolution = new UserSolution();

    static class UserSolution {

        class Node implements Comparable<Node>{
            int mScore;
            int mGrade;
            int  mGender;
            int mId;

            Node (int mId,int mGrade,int mGender,int mScore){
                this.mId = mId;
                this.mGrade=mGrade;
                this.mGender = mGender;
                this.mScore = mScore;
            }

            @Override
            public int compareTo(Node o2){ // 오름차순
                if(o2.mScore == this.mScore){
                    return Integer.compare(this.mId ,o2.mId);
                }
                return Integer.compare(this.mScore,o2.mScore);
            }
        }

        TreeSet <Node> [][] student;
        HashMap <Integer,Node> info;
        public void init() {
            student = new TreeSet[2][3];
            for(int g=0;g<2;g++){
                for(int m=0;m<3;m++ ){
                    student[g][m] = new TreeSet<Node>();
                }
            }
            info = new HashMap<>();
            return;
        }

        public int add(int mId, int mGrade, char mGender[], int mScore) {
            int gender = mGender[0] == 'm'?  0: 1; // 남자면 0 여자면 1
            student[gender][mGrade-1].add(new Node(mId,mGrade,gender,mScore));
            info.put(mId,new Node(mId,mGrade,gender,mScore));
            return student[gender][mGrade-1].last().mId;

        }

        public int remove(int mId) {
            int result =0;
            if(!info.containsKey(mId)){
                return 0;
            }else{
                Node rm = info.get(mId);
                info.remove(mId);
                student[rm.mGender][rm.mGrade-1].remove(rm);
                if(student[rm.mGender][rm.mGrade-1].isEmpty()) result =0;
                else result = student[rm.mGender][rm.mGrade-1].first().mId;
            }
            return result;
        }

        public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
            int answer =0;
            int part = Integer.MAX_VALUE;
            for(int i=0; i<mGenderCnt;i++){
                int gender =mGender[i][0] =='m' ? 0:1;
                for(int j=0;j<mGradeCnt;j++){
                    int grade = mGrade[j]-1;
                    TreeSet <Node> cur = student[gender][grade];
                    Node high = cur.higher(new Node(0,grade,gender,mScore));
                    if(high != null){
                        if(high.mScore< part){
                            answer = high.mId;
                            part =high.mScore;
                        }else if (high.mScore == part){
                            if(high.mId <answer){
                                answer = high.mId;
                            }
                        }
                    }
                }
            }
            return answer;
        }
    }

    private static void String2Char(char[] buf, String str) {
        for (int k = 0; k < str.length(); ++k)
            buf[k] = str.charAt(k);
        buf[str.length()] = '\0';
    }
    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int id, grade, score;
        int cmd, ans, ret;
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
                    char[] gender = new char[7];
                    id = Integer.parseInt(st.nextToken());
                    grade = Integer.parseInt(st.nextToken());
                    String2Char(gender, st.nextToken());
                    score = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.add(id, grade, gender, score);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_REMOVE:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.remove(id);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_QUERY:
                    int gradeCnt, genderCnt;
                    int[] gradeArr = new int[3];
                    char[][] genderArr = new char[2][7];
                    gradeCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < gradeCnt; ++j) {
                        gradeArr[j] = Integer.parseInt(st.nextToken());
                    }
                    genderCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < genderCnt; ++j) {
                        String2Char(genderArr[j], st.nextToken());
                    }
                    score = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.query(gradeCnt, gradeArr, genderCnt, genderArr, score);
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