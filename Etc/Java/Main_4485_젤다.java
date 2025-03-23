
import java.util.*;
import java.io.*;


public class Main_4485_젤다 {
	static class Vtx implements Comparable<Vtx>{
		int cost;
		int x;
		int y;
		Vtx(int cost, int x, int y){
			this.cost = cost;
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Vtx cur) {
			return Integer.compare(this.cost,cur.cost);
		}
		
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int origin[][];
	static int dis [][];
	static boolean visited[][];
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	static void run() throws Exception{
		int tc= 0;
		while(true) {
			tc +=1;
			N = Integer.parseInt(br.readLine());
			if(N==0) {
				break;
			}else {
				sb.append("Problem ").append(tc).append(": ");
				input();
				cal();
				sb.append(dis[N-1][N-1]).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void input() throws Exception{
		origin = new int [N][N];
		StringTokenizer st;
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
				
			}
		}
	}
	
	static void cal() {
		dis = new int [N][N];
		visited = new boolean[N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				dis[y][x] = Integer.MAX_VALUE - 10000;
			}
		}
		dis[0][0]= origin[0][0];
		PriorityQueue<Vtx> cost  = new PriorityQueue<>();
		cost.add(new Vtx(origin[0][0], 0, 0));
		
		while(!cost.isEmpty()) {
			Vtx cur = cost.poll();
			if(visited[cur.y][cur.x]) continue;
			
			visited[cur.y][cur.x] = true;
			
			for(int dir =0; dir<4; dir++) {
				int cx = cur.x + dx[dir];
				int cy = cur.y + dy[dir];
				if(!in_range(cy, cx)) continue;
				
				
				int n_cost = cur.cost + origin[cy][cx];
				if(n_cost < dis[cy][cx]) {
					cost.add(new Vtx(n_cost, cx, cy));
					dis[cy][cx] = n_cost;
				}
			}
			
		}
		
	}
	
	static boolean in_range(int y, int x) {
		return 0<= y && y< N && 0<=x && x<N;
	}
	static void print() {
		for(int y=0; y<N;y++) {
			for(int x=0;x<N;x++) {
				System.out.print(dis[y][x]+" ");
			}
			System.out.println();
		}
	}

}
