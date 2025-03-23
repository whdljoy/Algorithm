import java.util.*;
public class swea2063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        int [] val = new int [T];
        for (int i =0; i<T;i++){
            val[i] = sc.nextInt();
        }
        Arrays.sort(val);
        System.out.println(val[(T/2)]);
    }
}
