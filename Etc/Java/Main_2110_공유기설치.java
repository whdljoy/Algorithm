import java.util.*;
import java.io.*;
public class Main_2110_공유기설치 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb =new StringBuilder();
    static int N,C;
    static int [] num;
    static boolean [] check;
    static int ans =0;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        run();
    }

    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C =Integer.parseInt(st.nextToken());
        num= new int[N];

        for(int i=0; i<N;i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(num);

    }

    static void run() throws Exception{
        input();
        simulate();
    }
    static void simulate() throws Exception{
        binarySearch();
        System.out.println(ans);
    }
    static void binarySearch() {
        int low =1;
        int high = num[N-1]-num[0]+1;
        while(low<high) {
            int mid =(low +high)/2;

            int count =1;
            int current=num[0];
            for(int i=1;i<N;i++) {
                if(num[i]>=current+mid) {
                    count ++;
                    current=num[i];
                }
            }
            if(count <C) {
                high=mid;
            }else {
                low =mid+1;
            }
        }
        ans=low-1;
    }

}