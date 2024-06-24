import java.util.*;
import java.io.*;
public class Main_9663_NQueen {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int ans =0;
    static int [] yp;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        N = Integer.parseInt(br.readLine());
        simulate();
        System.out.println(ans);
    }
    static void simulate() {

        yp = new int [N+1];
        dfs(1);

    }

    static void dfs(int row) {
        if(row== (N+1)) {
            ans+=1;
            return;
        }

        for(int x=1; x<=N ; x++) {
            yp [row] = x;
            if(isOkay(row)) {
                dfs(row+1);
            }

        }
    }
    static boolean isOkay(int row) {

        for(int s=1; s<row;s++) {
            if(yp[s] == yp[row] || (row-s == Math.abs(yp[row] - yp [s]))) {
                return false;
            }
        }
        return true;
    }


}
