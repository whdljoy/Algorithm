

import java.util.*;

import java.awt.Point;
import java.io.*;
public class Main_3055_탈출 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R,C;
	static char origin[][];
	static Deque <Point> water;
	static Deque <Integer []> move;
	
	static int dx[] = {0 ,0,1,-1};
	static int dy[] = {-1,1,0,0};
	static int ans =Integer.MAX_VALUE;
	static boolean flag = false;
	static boolean visited[][];
	
 	public static void main(String[] args) throws Exception{
 		input();
 		bfs();
 		
 		if(ans ==Integer.MAX_VALUE) {
 			System.out.println("KAKTUS");

 		}else {
 			System.out.println(ans);
 		}
	}
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		origin = new char[ R][C];
		water = new ArrayDeque<>();
		move = new ArrayDeque<>();
		visited= new boolean[R][C];
		for(int y=0; y<R; y++) {
			String ip = br.readLine();
			for(int x=0; x<C; x++) {
				char cur = ip.charAt(x);
				origin[y][x] = cur;
				if(cur == 'S') {
					move.push(new Integer[] {x,y,0});
					visited[y][x] = true;
				}
				else if (cur == '*') {
					water.add(new Point(x,y));
				}
			}
		}
	}
	
	static void bfs() {
		while(!move.isEmpty()) {
			int cur_water = water.size();
			for(int i=0;i<cur_water;i++) {
				Point water_p = water.pollFirst();
				for(int dir=0;dir<4;dir++) {
					int cy = water_p.y +dy[dir];
					int cx = water_p.x +dx[dir];
					if(in_range(cy, cx) && origin[cy][cx]== '.') {
						water.add(new Point(cx,cy));
						origin[cy][cx] = '*';
					}
				}
			}
			int cur_move = move.size();
			for(int i=0;i<cur_move;i++) {
				Integer[] move_p = move.pollFirst();
				for(int dir=0;dir<4;dir++) {
					int cy = move_p[1] +dy[dir];
					int cx = move_p[0] +dx[dir];
					int time = move_p[2];
					if(in_range(cy, cx) &&!visited[cy][cx]) {
						if(origin[cy][cx] == 'D') {
							ans =time+1;
							return;
						}
						else if(origin[cy][cx]=='.') {
							visited[cy][cx] = true;
							move.add(new Integer[] {cx,cy,time+1});
						}						
					}
				}				
			}
		}

	}

	static boolean in_range(int y,int x) {
		return 0<=y && y< R && 0<=x && x<C;
	}

}
