import  java.util.*;
import java.io.*;
public class Main_3109_빵집 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] origin;
    static int R;
    static int C;
    static int ans;
    static boolean[][] check;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        ans = 0;
        check = new boolean[R][C];
        for (int y = 0; y < R; y++) {
            if (origin[y][0] == '.' && dfs(0, y)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        origin = new char[R][C];
        for (int y = 0; y < R; y++) {
            String ip = br.readLine();
            for (int x = 0; x < C; x++) {
                origin[y][x] = ip.charAt(x);
            }
        }
    }

    static boolean in_range(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }

    static boolean dfs(int sx, int sy) {
        if (sx == C - 1) {
            return true;
        }
        if (check[sy][sx] || origin[sy][sx] == 'X') {
            return false;
        }
        check[sy][sx] = true;
        int rx = sx + 1; //오른쪽
        int py = sy + 1;  //오른쪽아래
        int my = sy - 1;  //오른쪽 위
        if (in_range(rx, my) && origin[my][rx] == '.' && dfs(rx,my)) {
            return true;
        }
        if (in_range(rx, sy) && origin[sy][rx] == '.' &&dfs(rx, sy)) {
            return true;
        }
        if (in_range(rx, py) && origin[py][rx] == '.'&&  dfs(rx, py)) {
            return true;
        }
        return false;
    }
}

