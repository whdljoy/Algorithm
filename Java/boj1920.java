import java.util.Arrays;
import java.util.Scanner;

public class boj1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] A = new int[n];
        for (int i= 0; i<n;i++){
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        int m = sc.nextInt();
        for (int i=0; i<m;i++){
            boolean find = false;
            int target = sc.nextInt();
            int start =0;
            int end = A.length -1;
            while( start <= end){
                int mid_idx = (start+end)/2;
                int mid_val = A[mid_idx];
                if(mid_val >target){
                    end = mid_idx-1;
                }else if(mid_val < target) {
                    start = mid_idx + 1;
                }else{
                    find=true;
                    break;
                }
            }
            if(find) System.out.println(1);
            else System.out.println(0);
        }

    }
}
