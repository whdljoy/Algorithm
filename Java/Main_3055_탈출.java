
import java.util.*;

import java.awt.Point;
import java.io.*;
public class Main_3055_탈출 {
	static final int MAX_N =51;
	static final int MAX_TIME =3000;
	static class Move{
		Point position;
		int time=0;
		Move(Point position, int time){
			this.position = position;
			this.time = time;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int R,C;
	static int origin[][];
	static int water[][][];
	static Point start;
	static int dx[] = {0 ,0,1,-1};
	static int dy[] = {-1,1,0,0};
	static int ans =Integer.MAX_VALUE;
	static boolean flag = false;
 	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
 		input();
 		increase_water();
 		bfs();
 		
 		if(ans ==Integer.MAX_VALUE) {
 			sb.append("KAKTUS");

 		}else {
 			sb.append(ans);
 		}
 		System.out.println(sb);
	}
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		origin = new int[ MAX_N][ MAX_N];
		water = new int[MAX_TIME][MAX_N][MAX_N];
		for(int y=0; y<R; y++) {
			String ip = br.readLine();
			for(int x=0; x<C; x++) {
				char cur = ip.charAt(x);
				if(cur == 'S') {
					start = new Point(x,y);
					origin[y][x] =-1;
				}else if (cur == 'D') origin[y][x] = 2;
				else if (cur == '*') origin[y][x] = 1;
				else if (cur == 'X') origin[y][x] = 3;
				else origin[y][x] = 0;
			}
		}
	}
	
	static void bfs() {
		boolean visited[][] = new boolean [MAX_N][MAX_N];
		Deque <Move> dq = new ArrayDeque<>();
		dq.push(new Move(start, 0));
		visited[start.y][start.x] = true;
		while(!dq.isEmpty() && !flag) {
			Move cur = dq.pollFirst();
			int [][] tmp = water[cur.time+1];
			print(tmp);
			for(int dir=0;dir<4;dir++) {
				int cy = cur.position.y +dy[dir];
				int cx= cur.position.x +dx[dir];
				if(in_range(cy, cx) && !visited[cy][cx]) {
					if( tmp[cy][cx] == 0 ) {
						dq.push(new Move(new Point(cx,cy),cur.time+1));
						visited[cy][cx]= true;
					}
					if(tmp[cy][cx] ==2) {
						ans = cur.time+1;
						flag =true;
					}
				}
			}
			
		}
	}
	static void print(int water [][]) {
		for(int y=0;y<R;y++) {
			for(int x=0;x<C;x++) {
				System.out.print(water[y][x]);
			}
			System.out.println();
		}
	}
	static void increase_water() {	
		water[0]=cp_arr(origin);
		for(int i=1;i<=MAX_TIME;i++) {
			int [][] cp = cp_arr(water[i-1]);
			for(int y=0;y<R;y++) {
				for(int x=0; x<C; x++) {
					if(water[i-1][y][x]==1) {
						for(int dir=0; dir<4; dir++) {
							int cx = x+dx[dir];
							int cy = y+dy[dir];
							if(in_range(cy, cx) && water[i-1][cy][cx] == 0) {
								cp[cy][cx]= 1;
							}
						}
					}
				}
			
			}
			water[i]=cp_arr(cp);
		}
		
	}
	static int [][] cp_arr (int [][] arr) {
		int cp[][] =new int[MAX_N][MAX_N];	
		for(int y=0;y<R;y++) {
			for(int x=0; x<C; x++) {
				cp[y][x] = arr[y][x];
			}
		}	
		return cp;
	}
	static boolean in_range(int y,int x) {
		return 0<=y && y< R && 0<=x && x<C;
	}

}
