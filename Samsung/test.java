import java.util.*;
import java.io.*;

public class test {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int TC = 10;
    static int N; // 배열의 길이
    static int Q; // 쿼리의 길이
    static int[] min_segT;
    static int[] max_segT;
    static int[] first;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        min_segT = new int[N * 4];
        max_segT = new int[N * 4];
        first = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            first[i] = Integer.parseInt(st.nextToken());
        }
        buildSegmentTree(1, 0, N - 1);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 0이면 수 바꾸기 1이면 구간합구하기
            if (q == 0) {
                int ai = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                // ai 를 x로 바꾼다
                updateSegmentTree(1, 0, N - 1, ai, x);
            } else if (q == 1) {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                // l 부터 r-1 max 값에서 min 값을 뺀다.
                int min = queryMin(1, l, r - 1, 0, N - 1);
                int max = queryMax(1, l, r - 1, 0, N - 1);
                sb.append(max - min).append(" ");
            }
        }
    }

    static void buildSegmentTree(int node, int start, int end) {
        if (start == end) {
            min_segT[node] = first[start];
            max_segT[node] = first[start];
        } else {
            int mid = (start + end) / 2;
            buildSegmentTree(node * 2, start, mid);
            buildSegmentTree(node * 2 + 1, mid + 1, end);
            min_segT[node] = Math.min(min_segT[node * 2], min_segT[node * 2 + 1]);
            max_segT[node] = Math.max(max_segT[node * 2], max_segT[node * 2 + 1]);
        }
    }

    static void updateSegmentTree(int node, int start, int end, int index, int value) {
        if (start == end) {
            min_segT[node] = value;
            max_segT[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (start <= index && index <= mid) {
                updateSegmentTree(node * 2, start, mid, index, value);
            } else {
                updateSegmentTree(node * 2 + 1, mid + 1, end, index, value);
            }
            min_segT[node] = Math.min(min_segT[node * 2], min_segT[node * 2 + 1]);
            max_segT[node] = Math.max(max_segT[node * 2], max_segT[node * 2 + 1]);
        }
    }

    static int queryMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return min_segT[node];
        }
        int mid = (start + end) / 2;
        int leftMin = queryMin(node * 2, start, mid, left, right);
        int rightMin = queryMin(node * 2 + 1, mid + 1, end, left, right);
        return Math.min(leftMin, rightMin);
    }

    static int queryMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MIN_VALUE;
        }
        if (left <= start && end <= right) {
            return max_segT[node];
        }
        int mid = (start + end) / 2;
        int leftMax = queryMax(node * 2, start, mid, left, right);
        int rightMax = queryMax(node * 2 + 1, mid + 1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
}
