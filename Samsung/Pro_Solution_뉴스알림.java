import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
class Pro_Solution_뉴스알림 {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int INIT = 0;
    private final static int REGI = 1;
    private final static int OFFER = 2;
    private final static int CANCEL = 3;
    private final static int CHECK = 4;

    private static int gids[] = new int[30];
    private static int ansids[] = new int[3];
    private static int retids[] = new int[3];
    static class UserSolution {
        int N;
        int K;
        int beforeTime =0;
        HashMap<Integer,Integer> userInfo; // muid 로 채널을 구독한 사람의 id를 구할수있다.
        HashMap<Integer,ArrayList<Integer>> channelInfo;   //channel 명과 채널을 구독한 사람의 id
        HashMap<Integer,ArrayList<Integer>>  news;
        int [] userAlert;
        HashMap <Integer,Node> newsInfo;  // news ID로 채널 ID 유추
        List <Integer> [] user; //user 가 받은 뉴스
        int cnt;
        class Node {
            int channel;
            boolean cancel;
            int sendTime;

            Node (int channel,boolean cancel,int sendTime){
                this.channel = channel;
                this.cancel = cancel;
                this.sendTime =sendTime;
            }

        }
        class Vertex implements Comparable<Vertex>{
            int newsId;
            int time;
            Vertex(int newsId,int time){
                this.newsId = newsId;
                this.time = time;
            }
            @Override
            public int compareTo(Vertex o){
                if(o.time == this.time){
                    return Integer.compare(o.newsId,this.newsId);
                }
                return Integer.compare(o.time,this.time);
            }

        }
        void init(int N, int K)
        {
            this.N = N;
            this.K =K;
            cnt =0;
            beforeTime =0;
            userInfo = new HashMap<>();
            channelInfo = new HashMap<>();
            newsInfo = new HashMap<>();
            news = new HashMap<>();
            user = new ArrayList[N+1];
            userAlert = new int[N+1];
            for(int i=0;i<=N;i++){
                user[i] = new ArrayList<Integer>();
            }
        }


        /**
         *
         *  mTime 시각에 mUID 유저는 뉴스 알림을 받기 위해 mNum 개의 뉴스 채널 mChannelIDs[] 에 각각 등록한다.
         *  mTime 시각에 유저에게 보내지는 뉴스 알림이 있는 경우 먼저 알림을 보낸 후, mUID 유저를 뉴스 채널에 등록한다.
         *  mChannelIDs[] 뉴스 채널들은 서로 중복되지 않는다.
         *  mUID 는 중복으로 주어질 수 있지만, 동일 유저에게 등록되는 뉴스 채널은 서로 중복되지 않는다.
         *
         */

        void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
        {
            /// 알림 보내는 것 해야됨
            send(mTime);



            int id=0;
            if(userInfo.containsKey(mUID)){
                id = userInfo.get(mUID);
            }else{
                userInfo.put(mUID,cnt);
                id = cnt;
                cnt++;
            }
            for(int i=0; i<mNum;i++){
                ArrayList <Integer > tmp = new ArrayList<>();
                if(channelInfo.containsKey(mChannelIDs[i])){
                    tmp = channelInfo.get(mChannelIDs[i]);
                }
                tmp.add(id);
                channelInfo.put(mChannelIDs[i],tmp);
            }

            beforeTime =mTime;
        }


        void send(int mTime){
            for(int t = beforeTime+1; t<=mTime;t++){
                if(news.containsKey(t)){
                    ArrayList<Integer> current = news.get(t); //newsID를 가지고있음
                    for(int newsID : current){
                        if(!newsInfo.get(newsID).cancel){
                            int channelID = newsInfo.get(newsID).channel;
                            for(int id : channelInfo.get(channelID)){
                                userAlert[id] +=1;
                                user[id].add(newsID);
                            }
                        }
                    }
                }
            }
        }
        int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
        {
            /// 알림 보내는 것 해야됨
            send(mTime);



            int sendTime = mTime +mDelay;
            ArrayList <Integer > tmp = new ArrayList<>();
            if(news.containsKey(sendTime)){
                tmp = news.get(sendTime);
            }
            tmp.add(mNewsID);
            news.put(sendTime,tmp);
            newsInfo.put(mNewsID,new Node(mChannelID,false,sendTime));
            beforeTime = mTime;
            return channelInfo.get(mChannelID).size();
        }

        void cancelNews(int mTime, int mNewsID)
        {
            /// 알림 보내는 것 해야됨
            send(mTime);
            /**
             * mTime 시각에 mNewsID 뉴스가 취소되어 삭제된다.
             * mNewsID 뉴스가 유저들에게 뉴스 알림이 보내 졌으면 유저에게 있는 mNewsID 뉴스 알림도 삭제되어야 한다.
             * mNewsID 뉴스는 offerNews() 에서 제공된 뉴스이다.
             * mNewsID 뉴스는 이미 취소되어 삭제된 뉴스일 수도 있다.
             *
             *
             */

            Node nw = newsInfo.get(mNewsID);
            if(!nw.cancel){
                //삭제 진행
                newsInfo.put(mNewsID,new Node(nw.channel,true,nw.sendTime));
            }

            beforeTime = mTime;
        }

        int checkUser(int mTime, int mUID, int mRetIDs[])
        {
            /// 알림 보내는 것 해야됨
            send(mTime);

            int result =0;
            beforeTime=mTime;
            int userId = userInfo.get(mUID);
            if(user[userId].isEmpty()){
                return result;
            }else{
                PriorityQueue <Vertex> pq = new PriorityQueue<>();
                for(int newID : user[userId]){
                    if(!newsInfo.get(newID).cancel){
                        pq.add(new Vertex(newID,newsInfo.get(newID).sendTime));
                    }else{
                        userAlert[userId]-=1;
                    }
                }

                int size = pq.size() >2 ? 3: pq.size();
                for(int i=0;i<size;i++){
                    mRetIDs[i] = pq.poll().newsId;
//                    System.out.println(mRetIDs[i]);
                }
                result = userAlert[userId];
                user[userId] = new ArrayList<>();
                userAlert[userId]=0;
                return result;
            }

        }
    }
    private static boolean run() throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N, K, cmd, ans, ret;
        int time, num, uid, gid, nid, delay;

        int Q = Integer.parseInt(st.nextToken());
        boolean ok = false;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());

            if (cmd == INIT) {
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                usersolution.init(N, K);
                ok = true;
            } else if (cmd == REGI) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());
                num = Integer.parseInt(st.nextToken());
                for (int m = 0; m < num; m++) {
                    gids[m] = Integer.parseInt(st.nextToken());
                }

                usersolution.registerUser(time, uid, num, gids);
            } else if (cmd == OFFER) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());
                delay = Integer.parseInt(st.nextToken());
                gid = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.offerNews(time, nid, delay, gid);
//                System.out.println("offer " + ans + " " + ret);
                if (ans != ret) {
                    ok = false;
                }
            }
            else if (cmd == CANCEL) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());

                usersolution.cancelNews(time, nid);
            }
            else if (cmd == CHECK) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());

                ret = usersolution.checkUser(time, uid, retids);

                ans = Integer.parseInt(st.nextToken());
                num = ans;
                if (num > 3) num = 3;
                for (int m = 0; m < num; m++) {
                    ansids[m] = Integer.parseInt(st.nextToken());
                }
//                System.out.println("check "+ ans + " " + ret);
                if (ans != ret) {
                    ok = false;
                }
                else {
                    for (int m = 0; m < num; m++) {
                        if (ansids[m] != retids[m]) {
                            ok = false;
                        }
                    }
                }
            }
            else ok = false;
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