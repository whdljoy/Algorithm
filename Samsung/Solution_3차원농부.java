import java.util.*;
import java.io.*;
public class Solution_3차원농부 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int N,M;
    static int [] cows;
    static int [] horses;
    static int cow_x;
    static int horse_x;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC  =Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            simulate();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cows = new int [N];
        horses = new int [M];
        st = new StringTokenizer(br.readLine());
        cow_x = Integer.parseInt(st.nextToken());
        horse_x = Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++){
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M;i++){
            horses[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void simulate(){
        int answer =Integer.MAX_VALUE;
        int count=0;
        for(int i=0; i<M; i++){
            int leastRight=binarySearch(i);

            if(leastRight < N){
                int dist = Math.abs(cows[leastRight] - horses[i]);
                if (answer == dist){
                    count ++;
                }else if (answer >dist){
                    answer = dist;
                    count =1;
                }
            }

            if(leastRight >0){
                int dist = Math.abs(cows[leastRight-1] - horses[i]);
                if (answer == dist){
                    count ++;
                }else if (answer >dist){
                    answer = dist;
                    count =1;
                }
            }

        }
        sb.append(answer+Math.abs(horse_x-cow_x)).append(" ").append(count).append("\n");
    }

    static int binarySearch(int idx){
        int start = 0;
        int end = N-1;
        int lR = N-1;
        while(start<=end){
            int mid = (start+end) /2;

            if(cows[mid] >=horses[idx]){
                lR = mid;
                end = mid -1;
            }else if (cows[mid] < horses[idx]){
                start = mid+1;
            }
        }
        return lR;
    }
}
