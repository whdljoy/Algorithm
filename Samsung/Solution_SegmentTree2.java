import java.util.*;
import java.io.*;
public class Solution_SegmentTree2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N;
    static int Q;
    static int [] first;
    static int [] sum_seg;
    static int front;
    static int back;
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
        first = new int [N];
        sum_seg = new int [N*4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            first[i] = Integer.parseInt(st.nextToken());
        }
        init(1,0,N-1);
        print();
//        for(int q=0;q<Q;q++){
//            st = new StringTokenizer(br.readLine());
//            int query = Integer.parseInt(st.nextToken());
//            if(query ==0){
//                int i =Integer.parseInt(st.nextToken());
//                int x = Integer.parseInt(st.nextToken());
//                change(i,0,N-1,1,x);
//            }else if(query ==1){
//                front=0;
//                back =0;
//                int l = Integer.parseInt(st.nextToken());
//                int r = Integer.parseInt(st.nextToken());
//                search(1,1,l,r-1,0,N-1);
//                search(2,1,l+1,r-1,0,N-1);
//                sb.append(front-back).append(" ");
//            }
//        }

    }
    static void init(int node, int s,int e){
        if (s ==e) {
            sum_seg[node] = first[s];
            return;
        }

        int center = (s+e) /2;
        init(node *2 , s,center);
        init(node *2+1, center+1,e);
        if(s % 2 == e % 2 ){
            sum_seg[node] = sum_seg[node*2+1] +sum_seg[node*2];
        }else{
            sum_seg[node] =sum_seg[node*2];
        }
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

        if(s % 2 == e % 2 ){
            sum_seg[node] = sum_seg[node*2+1] +sum_seg[node*2];
        }else{
            sum_seg[node] =sum_seg[node*2];
        }
    }
    static void search(int flag,int node,int start,int end, int s ,int e ){
        if(s > end || start > e) return;

        if(s >= start && e <= end) {

            if(flag == 1){
                front = sum_seg[node];
                System.out.println(front + "front");
            }else if (flag ==2){
                back = sum_seg[node];
                System.out.println(back + "back");
            }
            return;
        }

        int center = (s+e) /2;
        search(flag,node*2,start,end, s,center );
        search(flag,node*2+1,start,end, center+1 ,e );
    }

    static void print(){
        for(int i=1;i<26;i++){
            System.out.println( "index" + i +" val" +sum_seg[i]);
        }
    }
}
