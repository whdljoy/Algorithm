import java.io.*;
import java.util.*;

public class Solution_InversionCounting {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int[] arr = new int[100002];
    static int[] tmp = new int[100002];
    static long ans;

    public static void main(String[] args) throws Exception {
        run();
        System.out.print(sb);
    }

    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
            sb.append(ans).append("\n");
        }
    }

    static void input() throws Exception {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        mergeSort(0, n - 1);
    }

    static void mergeSort(int start, int end) {
        if(start < end) {
            int mid = (start + end) >>> 1;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);

            int left = start;
            int right = mid + 1;
            int idx = start;

            while(left <= mid || right <= end) {
                if(right > end || (left <= mid && arr[left] < arr[right])) {
                    tmp[idx++] = arr[left++];
                } else {
                    ans += (mid - left + 1);
                    tmp[idx++] = arr[right++];
                }
            }

            for(int i = start; i <= end; i++) {
                arr[i] = tmp[i];
            }
        }
    }
}