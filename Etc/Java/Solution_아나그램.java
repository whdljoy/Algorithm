import java.util.*;
import java.io.*;

public class Solution_아나그램 {
    static Scanner sc = new Scanner(System.in);
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[] first, second;
    static int TC=10;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(sc.next());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.println(sb);
    }
    static void input() throws Exception {
        first =  sc.next().toCharArray();
        second =  sc.next().toCharArray();

        int [] aArr = makeAlphabet(first, 0, first.length);

        int i = 0;
        int ans = 0;

        while(true) {
            if(i+first.length > second.length) break;

            int[] bArr = makeAlphabet(second, i, i+first.length);

            if(isAnagram(aArr, bArr)) ans++;
            i++;
        }
        sb.append(ans).append("\n");
    }

    public static int[] makeAlphabet (char[] arr, int start, int end){
        int[] alphabet = new int[26];
        for (int i = start; i < end; i++) {
            alphabet[arr[i] - 'a']++;
        }
        return alphabet;
    }

    private static boolean isAnagram(int[] aArr, int[] bArr) {
        for (int i = 0; i < aArr.length; i++) {
            if (aArr[i] != bArr[i]) return false;
        }
        return true;
    }
}
