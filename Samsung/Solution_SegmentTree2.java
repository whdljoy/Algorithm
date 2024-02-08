import java.util.*;
import java.io.*;
public class Solution_SegmentTree2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N;
    static int Q;
    static long [] first;
    static long [] sum_seg;
    static long ans;
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
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        first = new long [N];
        sum_seg = new long [N*4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            if(i %2 ==0){
                first[i] = Integer.parseInt(st.nextToken());
            }else{
                first[i] = -1 * Integer.parseInt(st.nextToken());
            }
        }
        ans =0;
        init(1,0,N-1);;
        for(int q=0;q<Q;q++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if(query ==0){
                int i =Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if(i %2 ==1){
                    x = -1 * x;
                }
                change(i,0,N-1,1,x);
            }else if(query ==1){
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                ans = search(1,l,r-1,0,N-1);
                if(l % 2 ==1){
                    ans = -1 * ans;
                }
                sb.append(ans).append(" ");
            }
        }

    }
    static void init(int node, int s,int e){
        if (s ==e) {
            sum_seg[node] = first[s];
            return;
        }

        int center = (s+e) /2;
        init(node *2 , s,center);
        init(node *2+1, center+1,e);
        sum_seg[node] = sum_seg[node*2+1] +sum_seg[node*2];
    }
    static void change(int pos, int s, int e, int node, int x) {
        if(s > pos || pos > e)
            return;

        if(s == e) {
            sum_seg[node] = x;
            return;
        }

        int center = (s+e) /2;
        change(pos, s, center, node*2, x);
        change(pos, center+1, e, node*2+1, x);


        sum_seg[node] = sum_seg[node*2+1] +sum_seg[node*2];
    }
    static long search(int node,int start,int end, int s ,int e ){
        if(s > end || start > e) return 0;

        if(s >= start && e <= end) {
            return  sum_seg[node];
        }

        int center = (s+e) /2;
        long leftSum = search(node*2,start,end, s,center );
        long rightSum =search(node*2+1,start,end, center+1 ,e );
        return leftSum + rightSum;
    }

}
