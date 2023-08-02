import java.util.*;
import java.io.*;

public class swea2068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int mx = -1;
            for(int i=0; i<10;i++){
                int cur = sc.nextInt();
                mx = Math.max(mx,cur);
            }
            System.out.println("#"+test_case+" "+mx);

        }
    }
}
