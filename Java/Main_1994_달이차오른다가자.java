

import java.util.*;
import java.io.*;
public class Main_1994_달이차오른다가자 {
	static int N;
	static char origin[][];
	static int M;
	static int sx,sy;
	static class Inter{
		int x;
		int y;
		int dis;
		boolean key[] = new boolean[6];
		public Inter(int x, int y, int dis, boolean[] key) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.key = key;
		}
		public Inter(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}		
		
	}
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,-1,0,1};
	static Deque<Inter> can;
	static int ans =Integer.MAX_VALUE;
	static boolean tmp[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	static void run() throws Exception{
		input();
		simulate();
		if(ans ==Integer.MAX_VALUE) {
			ans =-1;
		}
		System.out.println(ans);
	}
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		origin = new char[N][M];
		for(int y=0;y<N;y++) {
			String ip = br.readLine();
			for(int x=0; x<M;x++) {
				origin[y][x] = ip.charAt(x);
				if (origin[y][x] == '0') {
					sx = x;
					sy = y;
				}
			}
		}
		can = new ArrayDeque<>();
		tmp=new boolean[6];
		can.add(new Inter(sx,sy,0));
	}
	
	static boolean in_range(int x,int y) {
		return 0<=x && x< M && 0<=y && y<N;
	}
	
	static void simulate() {
		while(!can.isEmpty()) {
			bfs(can.pollFirst());
		}
	}
	static void bfs(Inter pt) {
		boolean visited [][] = new boolean[N][M];
		
		Deque <Inter> q = new ArrayDeque<>();
		q.add(pt);;
		visited[pt.y][pt.x]=true;
		
		while(!q.isEmpty()) {
			Inter cur= q.pollFirst();
			for(int dir =0; dir<4;dir++) {
				int cx = cur.x + dx[dir];
				int cy = cur.y + dy[dir];
				if(in_range(cx, cy) && !visited[cy][cx]) {
					if(origin[cy][cx] =='.' || origin[cy][cx] =='0') {
						q.add(new Inter(cx,cy,cur.dis+1,cur.key));
						visited[cy][cx]= true;	
					}else if (origin[cy][cx] == '1') {
						ans = Math.min(ans, cur.dis+1);
					}
					
					else if ('a' <= origin[cy][cx] && origin[cy][cx]<='f') {
						for(int i=0;i<6;i++) {
							tmp[i] = cur.key[i];
						}
						if(!tmp[origin[cy][cx] - 'a']) {
							tmp[origin[cy][cx] - 'a']=true;						
							can.add(new Inter(cx,cy,cur.dis+1,tmp));
						}
					} 
					else if ('A' <= origin[cy][cx] && origin[cy][cx]<='F') {
						if(cur.key[origin[cy][cx]-'A']) {
							q.add(new Inter(cx,cy,cur.dis+1,cur.key));
							visited[cy][cx]= true;	
						}
					} 					
			}
		}
		}

	}
}
