import java.util.*;
import java.io.*;

public class Solution_Closest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) {
                return Long.compare(this.x, o.x);
            }
            return Long.compare(this.y, o.y);
        }


    }
    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb.toString());
    }

    static void input() throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }


        Arrays.sort(points);


        TreeSet<Point> set = new TreeSet<>();
        set.add(points[0]);
        set.add(points[1]);


        long answer = distance(points[0], points[1]);

        int start = 0;

        for (int idx = 2; idx < N; idx++) {
            Point cur = points[idx];

            while (start < idx) {
                Point point = points[start];
                long xDiff = cur.x - point.x;
                if (xDiff * xDiff > answer) {
                    set.remove(point);
                    start++;
                } else {
                    break;
                }
            }

            int d = (int) Math.sqrt((double) answer) + 1;
            Point from = new Point(-100001, cur.y - d);
            Point to = new Point(100001, cur.y + d);
            for (Point point : set.subSet(from, to)) {
                long distance = distance(cur, point);
                answer = Math.min(answer, distance);
            }
            set.add(cur);
        }

        sb.append(answer).append("\n");
    }

    private static long distance(Point A, Point B) {
        return (A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y);
    }

}