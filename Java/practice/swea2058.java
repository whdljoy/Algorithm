import java.util.*;
public class swea2058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String T;
        T=sc.nextLine();
        int total =0;
        for (int i=0;i<T.length();i++){
            total += T.charAt(i) -'0';
        }
        System.out.println(total);
    }
}
