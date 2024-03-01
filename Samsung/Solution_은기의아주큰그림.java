import java.io.*;
import java.util.*;
public class Solution_은기의아주큰그림 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int H,W,N,M;
    static int [][] original;
    static int [][] target;
    final static int HASH_SIZE = (int)Math.pow(2, 30) - 1;
    final static int ROW_HASH =4;
    final static int COL_HASH=5;
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
        target = new int[H][W];
        original = new int[N][M];
        for(int y=0; y<H;y++){
            String cur = br.readLine();
            for(int x =0; x<W; x++){
                target[y][x] = cur.charAt(x) == 'o' ? 1 : 0;
            }
        }
        for(int y=0; y<N;y++){
            String cur = br.readLine();
            for(int x =0; x<M; x++){
                original[y][x] = cur.charAt(x) == 'o' ? 1 : 0;
            }
        }

    }
    static void solution() {
        int hashVal = getHashArr(target, H, W)[0][0];
        int[][] hashArr = getHashArr(original, H, W);
        int cnt = 0;
        for (int i=0; i<=N-H; i++) {
            for (int j=0; j<=M-W; j++) {
                cnt = hashArr[i][j] == hashVal ? cnt + 1 : cnt;
            }
        }
        sb.append(cnt).append("\n");
    }
    static int[][] getHashArr(int[][] matrix, int height, int width) {
        int H = matrix.length;
        int W = matrix[0].length;
        int[][] horizonHashArr = new int[H][W - width + 1];
        int rowMaxP = getMax(height, COL_HASH);
        int colMaxP = getMax(width, ROW_HASH);
        for (int i=0; i<H; i++) {
            int hash = getRowHash(matrix, width, i,0);
            horizonHashArr[i][0] = hash;
            for (int j=1; j<=W-width; j++) {
                horizonHashArr[i][j] = getNext(hash, matrix[i][j-1], colMaxP, matrix[i][j-1+width], ROW_HASH);
                hash = horizonHashArr[i][j];
            }
        }

        int[][] verticalHashArr = new int[H - height + 1][W - width + 1];
        for (int j=0; j<=W-width; j++) {
            int hash = getColHash(horizonHashArr, height, 0,j);
            verticalHashArr[0][j] = hash;
            for (int i=1; i<=H-height; i++) {
                verticalHashArr[i][j] = getNext(hash, horizonHashArr[i-1][j], rowMaxP, horizonHashArr[i-1+height][j], COL_HASH);
                hash = verticalHashArr[i][j];
            }
        }
        return verticalHashArr;
    }
    static int getMax(int len, int shift) {
        long result = 1;
        for (int i=0; i < len - 1; i++) {
            result = (result << shift) + result;
        }
        return (int)(result & HASH_SIZE);
    }

    static int getRowHash(int[][] matrix, int len, int row,int col) {
        long result = 0;
        for (int i=0; i<len; i++) {
            result = (result << ROW_HASH) + result + matrix[row][col+i];
        }
        return (int)(result & HASH_SIZE);
    }

    static int getColHash(int[][] matrix, int len, int row,int col) {
        long result = 0;
        for (int i=0; i<len; i++) {
          result = (result << COL_HASH) + result + matrix[row+i][col];

        }
        return (int)(result & HASH_SIZE);
    }

    static int getNext(int prev, int del, int maxPower, int add, int shift) {
        long result = prev - ( del * maxPower);
        result = (result << shift) + result + add;
        return (int)(result & HASH_SIZE);
    }


}


