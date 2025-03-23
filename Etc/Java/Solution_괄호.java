import java.util.*;
import java.io.*;

public class Solution_괄호 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int flipCnt;
    static List<int[]> result;
    static List<Integer> subSumArr;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb.toString());
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        // 초기화
        flipCnt = 0;
        result = new ArrayList<>();
        subSumArr = new ArrayList<>();

        solve(str, len);

        // 결과 추가
        sb.append(flipCnt).append("\n");
        for (int[] pair : result) {
            sb.append(pair[0]).append(" ").append(pair[1]).append("\n");
        }
    }

    static String rOperation(int left, int right, String str) {
        char[] arr = str.toCharArray();
        int s = left, e = right;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        for (int i = s; i <= e; i++) {
            if (arr[i] == '(') arr[i] = ')';
            else if (arr[i] == ')') arr[i] = '(';
        }

        return new String(arr);
    }

    static String flip(String str, int len) {
        int minValue = 0, pos = -1, sum = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(') sum++;
            else if (str.charAt(i) == ')') sum--;

            if (sum < minValue) {
                minValue = sum;
                pos = i;
            }
        }

        if (pos != -1) {
            flipCnt++;
            result.add(new int[]{0, pos});
            str = rOperation(0, pos, str);
        }

        return str;
    }

    static void solve(String str, int len) {
        if (len % 2 == 1) {
            flipCnt = -1;
            return;
        }

        str = flip(str, len);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(') sum++;
            else if (str.charAt(i) == ')') sum--;
            subSumArr.add(sum);
        }

        if (subSumArr.get(len - 1) == 0) return;


        int halfValue = subSumArr.get(len - 1) / 2;
        int pos = -1;
        for (int i = 0; i < len; i++) {
            if (halfValue == subSumArr.get(i)) {
                pos = i + 1;
            }
        }
        result.add(new int[]{pos, len - 1});
        flipCnt++;
    }
}