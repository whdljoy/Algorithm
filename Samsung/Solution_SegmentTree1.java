import java.util.*;
import java.io.*;
public class Solution_SegmentTree1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N ; //배열의 길이
    static int Q ; //쿼리의 길이
    static int [] min_segT;
    static int [] max_segT;
    static int [] first;
    static int min =Integer.MAX_VALUE;
    static int max =Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N  = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        min_segT = new int [N*4];
        max_segT = new int [N*4];
        first = new int [N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            first[i] = Integer.parseInt(st.nextToken());
        }
        init(1,0,N-1); //구간합 트리만들기
        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 0이면 수 바꾸기 1이면 구간합구하기
            if(q ==0){
                int ai = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                change(ai, 0, N-1, 1, x);

            }else if (q==1){
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                // l 부터 r-1 max 값에서 min 값을 뺀다.
                min =Integer.MAX_VALUE;
                max =Integer.MIN_VALUE;
                search(1,l,r-1,0,N-1);
                sb.append(max - min).append(" ");
            }
        }
    }
    static void init(int node,int s,int e){
        if (s ==e) {
            min_segT[node] = first[s];
            max_segT[node] = first[s];
            return;
        }

        int center = (s+e) /2;
        init(node *2 , s,center);
        init(node *2+1, center+1,e);
        min_segT[node] = Math.min(min_segT[node*2], min_segT[node*2+1]);
        max_segT[node] = Math.max(max_segT[node*2], max_segT[node*2+1]);
    }
    static void change(int pos, int s, int e, int node, int x) {
        if(s > pos || pos > e)
            return;

        if(s == e) {
            min_segT[node] = x;
            max_segT[node] = x;
            return;
        }

        int center = (s+e) /2;
        change(pos, s, center, node*2, x);
        change(pos, center+1, e, node*2+1, x);

        min_segT[node] = Math.min(min_segT[node*2], min_segT[node*2+1]);
        max_segT[node] = Math.max(max_segT[node*2], max_segT[node*2+1]);
    }
    static void search(int node,int start,int end, int s ,int e ){
        if(s > end || start > e) return ;
        if(s==e) {
            min = Math.min(min_segT[node],min);
            max = Math.max(max_segT[node],max);
            return;
        }
        int center = (s+e) /2;
        search(node*2,start,end, s,center );
        search(node*2+1,start,end, center+1 ,e );
        min_segT[node] = Math.min(min_segT[node*2], min_segT[node*2+1]);
        max_segT[node] = Math.max(max_segT[node*2], max_segT[node*2+1]);
    }


}

