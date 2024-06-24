import java.util.*;
import java.io.*;
public class Main_2447_별찍기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static boolean [][] status;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        status = new boolean[N][N];
        makeStar(0, 0, N);
        StringBuilder sb = new StringBuilder();

        for (boolean[] row : status) {
            for (boolean col : row) {

                if (col)
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
    static void makeStar (int y, int x, int size) {

        if (size == 1) {
            status[y][x] = true;
            return;
        }

        int ns = size/3;
        int cycle = 1;

        for (int i=y; i<y+size; i+=ns) {
            for (int j=x; j<x+size; j+=ns) {

                if (cycle++ == 5)
                    continue;

                makeStar(i, j, ns);
            }
        }
    }

}


