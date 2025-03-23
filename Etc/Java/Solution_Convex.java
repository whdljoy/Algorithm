import java.util.*;
import java.io.*;

public class Solution_Convex {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static Point first;

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
        int N = Integer.parseInt(br.readLine());
        first = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }

        Stack<Point> cvh = convexHull(points);
        sb.append(cvh.size()).append("\n");
    }

    private static Stack<Point> convexHull(List<Point> points) {
        for (Point p : points) {
            if (p.y < first.y) {
                first = p;
            } else if (p.y == first.y) {
                if (p.x < first.x) {
                    first = p;
                }
            }
        }


        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point second, Point third) {
                int result = ccw(first, second, third);
                if (result > 0) return -1;
                else if (result < 0) return 1;
                else {
                    if (dist(first, second) > dist(first, third)) return 1;
                }
                return -1;
            }
        });


        Stack<Point> stack = new Stack<>();
        stack.add(first);
        for (int i = 1; i < points.size(); i++) {
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
                stack.pop();
            }
            stack.add(points.get(i));
        }
        return stack;
    }

    private static int ccw(Point a, Point b, Point c) {
        long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }

    private static long dist(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }
}
