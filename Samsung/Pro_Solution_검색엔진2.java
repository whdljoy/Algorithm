import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
class Pro_Solution_검색엔진2
{
    private static final int MAX_LENGTH				= 8;
    private static final int CMD_INIT				= 100;
    private static final int CMD_SEARCH				= 200;
    private static final int CMD_RECOMMEND			= 300;
    private static final int CMD_RELATE				= 400;
    private static final int CMD_RANK				= 500;

    public static final class Result
    {
        int mOrder;
        int mRank;

        Result()
        {
            mOrder = 0;
            mRank = 0;
        }
    }

    private static void String2Char(String s, char[] b, int maxlen)
    {
        int n = s.length();
        for (int i = 0; i < n; ++i)
            b[i] = s.charAt(i);
        for (int i = n; i < maxlen; ++i)
            b[i] = '\0';
    }
    private static int strcmp(char a[], char b[], int n)
    {
        int i = 0;
        while(i < n - 1 && a[i] != 0 && a[i] == b[i]) i++;
        return a[i] - b[i];
    }

    private static UserSolution usersolution = new UserSolution();
    static class UserSolution
    {
        class Node implements Comparable<Node>{
            int cnt;
            String de;
            ArrayList <Integer> connect;
            Node (int cnt,String de){
                this.cnt = cnt;
                this.de = de;
                this.connect = new ArrayList<>();
            }
            @Override
            public int compareTo(Node o){
                if (this.cnt == o.cnt) {
                    return this.de.compareTo(o.de);
                }
                return Integer.compare(o.cnt ,this.cnt);
            }
        }
        HashMap <String , Integer> total;  // 전체 문장으로 번호 찾기;
        HashMap <Integer , Node > info;  // 번호로 조회수나 정보찾기
        HashMap <String, List<Integer>> pre;// prifix로 번호찾기
        int cnt;
        void init()
        {
            info = new HashMap<>();
            total = new HashMap<>();
            pre = new HashMap<>();
            cnt =0;
            return;
        }

        void search(char mStr[], int mCount)
        {
            String start = "";
            for(char sch : mStr){
                if(sch == '\0'){
                    break;
                }else{
                    start += sch;
                }
            }
            int id =-1;
            if(total.containsKey(start)){
                id = total.get(start);
                Node before = info.get(id);
                if(!before.connect.isEmpty()){
                    for(int num :before.connect){
                        Node tmp = info.get(num);
                        tmp.cnt+=mCount;
                        info.put(num,tmp);
                    }
                }else{
                    before.cnt +=mCount;
                    info.put(id,before);
                }
            }else{
                id = cnt;
                total.put(start,cnt++);
                info.put(id,new Node(mCount,start));
                String tmp = "";
                for(char sch : mStr){
                    if(sch == '\0'){
                        break;
                    }else{
                        tmp += sch;
                        ArrayList <Integer> temp =  new ArrayList<>();
                        if(pre.containsKey(tmp)){
                            temp =  (ArrayList <Integer>)pre.get(tmp);
                        }
                        temp.add(id);
                        pre.put(tmp,temp);
                    }
                }
            }

            return;
        }

        Pro_Solution_검색엔진2.Result recommend(char mStr[])
        {
            Pro_Solution_검색엔진2.Result res = new Pro_Solution_검색엔진2.Result();

            ///검색후 조회수 증가
            String start = "";
            for(char sch : mStr){
                if(sch == '\0'){
                    break;
                }else{
                    start += sch;
                }
            }

            boolean flag =false;
            int n =0; // 입력한 글자수
            //한글자도 안적었을때
            List <Node > first = new ArrayList<Node>();
            for(int i=0;i <cnt;i++){
                Node cur = info.get(i);
                ArrayList <Integer> temp =(ArrayList <Integer>) pre.get(cur.de);
                for(int num : temp){
                    Node current =info.get(num);
                    first.add(current);
                }
            }
            Collections.sort(first);
            int size1 = Math.min(first.size(),5);
            for(int i=0; i<size1;i++){
                if(first.get(i).de.equals(start)){
                    res.mOrder = n;
                    res.mRank = i+1;
                    flag = true;
                    break;
                }
            }
// 한글 자씩 찾아가며 찾기
            if(!flag){
                String tmp = "";
                outer :for(char sch : mStr){
                    if(sch == '\0'){
                        break;
                    }else{
                        n++;
                        tmp += sch;
                        ArrayList <Integer> temp =(ArrayList <Integer>) pre.get(tmp);
                        List <Node > bag = new ArrayList<Node>();
                        for(int num : temp){
                            Node current =info.get(num);
                            bag.add(current);
                        }
                        Collections.sort(bag);
                        int size = Math.min(bag.size(),5);
                        for(int i=0; i<size;i++){
                            if(bag.get(i).de.equals(start)){
                                res.mOrder = n;
                                res.mRank = i+1;
                                break outer;
                            }
                        }
                    }
                }
            }

            int id = total.get(start);
            Node before = info.get(id);

            if(!before.connect.isEmpty()){
                for(int num :before.connect){
                    Node current = info.get(num);
                    current.cnt+=1;
                    info.put(num,current);
                }
            }else{
                before.cnt +=1;
                info.put(id,before);

            }

            
            
            return res;
        }

        int relate(char mStr1[], char mStr2[])

        {
            String str1 = "";
            for(char sch : mStr1){
                if(sch == '\0'){
                    break;
                }else{
                    str1 += sch;
                }
            }
            int id1 =total.get(str1);

            String str2 = "";
            for(char sch : mStr2){
                if(sch == '\0'){
                    break;
                }else{
                    str2 += sch;
                }
            }
            int id2 =total.get(str2);

            Node n1 = info.get(id1);
            Node n2 = info.get(id2);
            boolean [] check = new boolean[cnt];
            check[id1] = true;
            check[id2] =true;
            for( int num : n1.connect){
                check[num] = true;
            }
            for( int num : n2.connect){
                check[num] = true;
            }
            int val = n1.cnt + n2.cnt;
            ArrayList <Integer > tmp =  new ArrayList<>();
            for(int i=0;i<cnt;i++){
                if(check[i]) tmp.add(i);
            }
            for(int id : tmp){
                Node current = info.get(id);
                current.cnt = val;
                current.connect = tmp;
                info.put(id,current);
            }
            n1.connect = tmp;
            n2.connect = tmp;
            info.put(id1,n1);
            info.put(id2,n2);
            return val;
        }

        void rank(char mPrefix[], int mRank, char mReturnStr[])
        {
            String start = "";
            for(char sch : mPrefix){
                if(sch == '\0'){
                    break;
                }else{
                    start += sch;
                }
            }
            List <Node > first = new ArrayList<Node>();
            ArrayList <Integer> temp =(ArrayList <Integer>) pre.get(start);
            for(int num : temp){
                Node current =info.get(num);
                first.add(current);
            }
            Collections.sort(first);
            String ans = first.get(mRank-1).de;
            for(int i=0; i <ans.length();i++){
                mReturnStr[i] =ans.charAt(i);
            }

            return;
        }
    }
    private static boolean run(Scanner sc) throws Exception
    {
        int Q;

        Q = sc.nextInt();

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            int cmd = sc.nextInt();
            int ret, ans, ans2, mCount, mRank;
            char mStr[] = new char[MAX_LENGTH], mStr2[] = new char[MAX_LENGTH], mReturnStr[] = new char[MAX_LENGTH];
            Result res;

            switch(cmd)
            {
                case CMD_INIT:
                    usersolution.init();
                    okay = true;
                    break;
                case CMD_SEARCH:
                    String2Char(sc.next(), mStr, MAX_LENGTH);
                    mCount = sc.nextInt();
                    usersolution.search(mStr, mCount);
                    break;
                case CMD_RECOMMEND:
                    String2Char(sc.next(), mStr, MAX_LENGTH);
                    res = usersolution.recommend(mStr);
                    ans = sc.nextInt();
                    ans2 = sc.nextInt();
//                    System.out.println("recommend "+ res.mOrder +" " + ans +"        "+res.mRank + " "+ ans2);
                    if (res.mOrder != ans || res.mRank != ans2)
                        okay = false;
                    break;
                case CMD_RELATE:
                    String2Char(sc.next(), mStr, MAX_LENGTH);
                    String2Char(sc.next(), mStr2, MAX_LENGTH);
                    ret = usersolution.relate(mStr, mStr2);
                    ans = sc.nextInt();
//                    System.out.println("relate " + ans + "  " +ret);
                    if (ans != ret)
                        okay = false;
                    break;
                case CMD_RANK:
                    String2Char(sc.next(), mStr, MAX_LENGTH);
                    mRank = sc.nextInt();
                    usersolution.rank(mStr, mRank, mReturnStr);
                    String2Char(sc.next(), mStr2, MAX_LENGTH);
//                    String start = "";
//                    for(char sch : mStr2){
//                        if(sch == '\0'){
//                            break;
//                        }else{
//                            start += sch;
//                        }
//                    }
//                    String start2 = "";
//                    for(char sch : mReturnStr){
//                        if(sch == '\0'){
//                            break;
//                        }else{
//                            start2 += sch;
//                        }
//                    }
//                    System.out.println("compare ans " + start+ " my "+ start2);
                    if (strcmp(mStr2, mReturnStr, MAX_LENGTH) != 0)
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