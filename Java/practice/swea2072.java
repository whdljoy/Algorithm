import java.util.*;
import java.io.*;
public class swea2072 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int total =0;
            for(int i=0; i<10; i++){
                int cur = sc.nextInt();
                if (cur % 2==1){
                    total += cur;
                }
            }
            System.out.println("#"+test_case+" "+total);

        }
    }
}


