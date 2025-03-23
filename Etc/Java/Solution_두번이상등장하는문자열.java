import java.util.*;
import java.io.*;

public class Solution_두번이상등장하는문자열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static class Suffix implements Comparable<Suffix> {
        int index; // 초기 인덱스
        int rank, nextRank;

        public Suffix(int index, int rank) {
            this.index = index;
            this.rank = rank;
        }

        public int compareTo(Suffix target) {
            if (this.rank != target.rank) {
                return Integer.compare(this.rank, target.rank);
            }
            return Integer.compare(this.nextRank, target.nextRank);
        }
    }
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
        int L = Integer.parseInt(br.readLine().trim());
        String str = br.readLine().trim();


        int[] suffixArray = findSuffixArray(str);
        int[] lcp = findLCP(str, suffixArray);

        int answer = 0;
        for (int i = 0; i < L; i++) {
            answer = Math.max(answer, lcp[i]);
        }
        sb.append(answer).append("\n");
    }

    public static int[] findSuffixArray(String str) {
        int N = str.length();
        Suffix[] sa = new Suffix[N];

        for (int i = 0; i < N; i++) {
            int rank = str.charAt(i) - 'a';
            sa[i] = new Suffix(i, rank);
        }

        for (int i = 0; i < N - 1; i++) {
            sa[i].nextRank = sa[i + 1].rank;
        }
        sa[N - 1].nextRank = -1;


        Arrays.sort(sa);

        int[] temp = new int[N];

        for (int length = 4; length < 2 * N; length <<= 1) {
            int rank = 0, prev = sa[0].rank;
            sa[0].rank = rank;
            temp[sa[0].index] = 0;


            for (int i = 1; i < N; i++) {
                if (sa[i].rank == prev && sa[i].nextRank == sa[i - 1].nextRank) {
                    prev = sa[i].rank;
                    sa[i].rank = rank;
                } else {
                    prev = sa[i].rank;
                    sa[i].rank = ++rank;
                }
                temp[sa[i].index] = i;
            }


            for (int i = 0; i < N; i++) {
                int nextIdx = sa[i].index + (length / 2);
                if (nextIdx >= N) {
                    sa[i].nextRank = -1;
                    continue;
                }
                sa[i].nextRank = sa[temp[nextIdx]].rank;
            }
            Arrays.sort(sa);
        }


        int[] suffixArray = new int[N];
        for (int i = 0; i < N; i++) {
            suffixArray[i] = sa[i].index;
        }
        return suffixArray;
    }

    private static int[] findLCP(String str, int[] suffixArray) {
        int N = suffixArray.length;
        int[] lcp = new int[N];
        int[] invSuff = new int[N];

        for (int i = 0; i < N; i++) {
            invSuff[suffixArray[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < N; i++) {
            if (invSuff[i] == N - 1) {
                k = 0;
                continue;
            }

            int j = suffixArray[invSuff[i] + 1];


            while (i + k < N && j + k < N) {
                if (str.charAt(i + k) != str.charAt(j + k)) {
                    break;
                }
                k++;
            }

            lcp[invSuff[i]] = k;

            if (k > 0) {
                k--;
            }
        }
        return lcp;
    }
}