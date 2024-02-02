import java.util.*;
import java.io.*;
public class Solution_영어공부 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int N;
    static int P;
    static int [] day;
    static ArrayList<Integer> interval;
    static int ans=0;
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
            binarySearch();
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P  =Integer.parseInt(st.nextToken());
        day = new int [N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++){
            day[i] = Integer.parseInt(st.nextToken());
        }
        interval = new ArrayList<>();
        interval.add(0);
        int total=0;
        for(int i=1;i<N;i++){
            total += day[i]-day[i-1]-1;
            interval.add(total);
        }
        ans=0;

    }

    static void binarySearch(){
        for(int i= 0;i<N;i++){
            int answer =0;
            int start =i;
            int end = N-1;
            while(start <=end){
                int mid = (start+end)/2;
                int notStudy =interval.get(mid)- interval.get(i);
                int remainder =P;
                remainder = Math.max(P - notStudy, 0);
                if(notStudy > P){
                    end = mid-1;
                }else{
                    answer = day[mid]-day[i] +1 +remainder;
                    start = mid+1;
                }

            }
            ans = Math.max(ans,answer);
        }
    }
}
