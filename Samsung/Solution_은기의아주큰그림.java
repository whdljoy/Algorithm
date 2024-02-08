import java.io.*;
import java.util.*;
public class Solution_은기의아주큰그림 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int H,W,N,M;
    static char [][] original;
    static char [][] target;
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
            solution();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        st= new StringTokenizer( br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        target = new char[H][W];
        original = new char[N][M];
        for(int y=0; y<H;y++){
            String cur = br.readLine();
            for(int x =0; x<W; x++){
                target[y][x] = cur.charAt(x);
            }
        }
        for(int y=0; y<N;y++){
            String cur = br.readLine();
            for(int x =0; x<M; x++){
                original[y][x] = cur.charAt(x);
            }
        }

    }
    static void solution() {
        int targetHash = getHash(target, H, W);
        int originalHash = getHash(original, N, M);
        int cnt = 0;
        if (targetHash == originalHash) {
            cnt++;
        }
        for (int i = 1; i <= N - H; i++) {
            for (int j = 0; j < M; j++) {
                originalHash -= original[i - 1][j];
                originalHash += original[i + H - 1][j];
                if (targetHash == originalHash) {
                    cnt++;
                }
            }
        }
        sb.append(cnt).append("\n");
    }
    static int getHash(char[][] matrix, int height, int width) {
        int hash = 0;
        int power = 1;
        int prime = 26;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                hash = (hash * prime + matrix[i][j]) % 1000000007;
                power = (power * prime) % 1000000007;
            }
        }
        return hash;
    }
}
