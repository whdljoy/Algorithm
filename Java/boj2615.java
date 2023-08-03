
import java.util.*;


import java.io.*;

public class boj2615 {
	public static class Point implements Comparable<Point>{
		public int x;
		public int y;
		Point(int x,int y){
			this.x = x;
			this.y =y;
		}
		@Override
		public int compareTo(Point p) {
			if(this.x > p.x) {
				return 1;
			}
			if(this.x == p.x) {
				if(this.y > p.y) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return -1;
			}
			
		}
	}
	public static final int GRID = 19;
	public static int [][] info;
	public static int ans =0;
	public static int [] dx = {-1,0,1,0,-1,-1,1,1};
	public static int [] dy = {0,-1,0,1,-1,1,-1,1};
	public static List<Point> cur;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		info = new int[GRID][GRID];
		input(br);
		cur = new LinkedList<Point>();
		ans = cal();
		System.out.println(ans);
		Collections.sort(cur);
		StringBuilder sBuilder = new StringBuilder();
		if(ans!=0) {
			sBuilder.append(cur.get(0).y+1).append(" ").append(cur.get(0).x+1);
			System.out.println(sBuilder);
		}


				
		

	}
	public static void input(BufferedReader br) throws IOException {
		StringTokenizer stk;
		for(int y=0; y<GRID; y++) {
			stk = new StringTokenizer(br.readLine());
			for(int x=0; x<GRID; x++) {
				info[y][x] = Integer.parseInt(stk.nextToken());
			}
		}		
	}
	
	public static boolean in_range(int x,int y) {
		return 0<=x && x<GRID && 0<=y && y<GRID;
	}
	
	
	public static int cal() {
		for(int y=0;y<GRID; y++) {
			for(int x=0; x<GRID; x++) {
				if(info[y][x] !=0) {
					for(int dir=0;dir<8;dir++) {
						cur = new LinkedList<Point>();
						cur.add(new Point(x, y));
						for(int dis=1;dis<5; dis++) {
							int cx = dx[dir]*dis +x;
							int cy = dy[dir]*dis +y;
							if(in_range(cx, cy) && (info[y][x] == info[cy][cx])) {
								cur.add(new Point(cx, cy));
							}else {
								break;
							}
						}
						if(cur.size() == 5) {
							int px = dx[dir]*5 +x;
							int py = dy[dir]*5 +y;
							if(in_range(px, py) && (info[y][x] == info[py][px])) {
								cur.add(new Point(px, py));
							}
							int mx = (dx[dir]*-1) +x;
							int my = (dy[dir]*-1) +y;
							if(in_range(mx, my) && (info[y][x] == info[my][mx])) {
								cur.add(new Point(mx, my));
							}
							if(cur.size() ==5) {
								return info[y][x];
							}
						}
					}
					
				}
			}
		}
		return 0;
	}
}
