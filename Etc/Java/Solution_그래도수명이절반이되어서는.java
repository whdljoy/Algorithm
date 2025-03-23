import java.util.*;
import java.io.*;
public class Solution_그래도수명이절반이되어서는 {
    static final int MAX_VALUE = 200000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int [] W = new int[MAX_VALUE + 1];
    static int [] S = new int[MAX_VALUE + 1];
    static int N,K;
    static int TC=10;
    static int answer;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        st= new StringTokenizer(br.readLine());
        TC = Integer.parseInt(st.nextToken());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            parametric();
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        answer = MAX_VALUE;

    }

    private static void parametric() {
        int left = 1;
        int right = MAX_VALUE;

        while (left < right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                right = mid;
                answer = right;
            } else {
                left = mid + 1;
            }
        }
    }

    private static boolean isPossible(int mid) {
        int data = 1; // 첫번째 데이터부터 확인
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (W[i] <= mid) {
                cnt++;
            }else {
                cnt = 0;
            }

            // 데이터 덩어리 크기를 만족하는지 확인
            if (cnt == S[data]) {
                data++;
                cnt = 0;
                // 모든 데이터 덩어리를 만족하였는지 확인
                if (data > K) {
                    return true;
                }
            }
        }
        return false;
    }


}


