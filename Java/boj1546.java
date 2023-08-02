import java.util.Scanner;

public class problem1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int A[] = new int[num];
        long sum = 0;
        long max = 0;
        for( int i =0 ; i<num; i ++){
            A[i]= sc.nextInt();
        }
        for (int j =0; j<num; j++){
            if(A[j] > max) max = A[j];
            sum = sum + A[j];
        }
        System.out.print(sum*100.0/max/num);
    }
}
