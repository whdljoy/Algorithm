
import java.util.*;
import java.io.*;

public class Solution_5656_벽돌깨기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static StringBuilder sb = new StringBuilder();
	
	static int N,W,H;
	static int tc;
	static int origin[][];
	static int selected[];
	static int dx [] = {-1,0,1,0};
	static int dy[] = {0,-1,0,1};
	static int val;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1;i <=tc;i++) {
			sb.append("#").append(i).append(" ");
			input();
			select(0, origin);
			if(ans == Integer.MAX_VALUE) {
				ans=0;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		origin= new int[H][W];
		selected = new int [N];
		ans= Integer.MAX_VALUE;
		for(int y=0; y<H; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<W;x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void select(int num,int [][]move) {
		if(num == N) {
			int cnt= W*H;
			for(int y=0; y<H;y++) {
				for(int x=0; x<W;x++) {
					if(move[y][x]==0) {
						cnt-=1;
					}
				}
			}
			ans=Math.min(ans,cnt);
			return;
		}
		for(int x=0; x<W;x++) {
			select(num+1, change(x, move));
		}	
	}
	
	static void print(int [][] move) {
		sb.append("\n");
		for(int y=0;y<H;y++) {
			for(int x=0;x<W;x++) {
				sb.append(move[y][x]).append( " ");
			}
			sb.append("\n");
		}
	}
	static int [][] change(int idx,int move[][]){
		
		
		//벽돌 깨기 과정
		boolean visited [][] = new boolean[H][W];
		Deque <Integer[]> dq = new ArrayDeque<Integer[]>();
		int sy =0;
		for(int y=0;y<H;y++) {
			if(move[y][idx] !=0) {
				sy = y;
				break;
			}
		}
		dq.add(new Integer[] {sy,idx,move[sy][idx]-1});
		visited[sy][idx] = true;
		while(!dq.isEmpty()) {
			Integer [] cur = dq.pollFirst();
			for(int dir=0; dir<4; dir++) {   
				int dis =0;
				while(dis < cur[2]) {
					dis++;
					int cy = cur[0] + dis*dy[dir];
					int cx = cur[1] + dis*dx[dir];
					if(in_range(cy,cx) && !visited[cy][cx] && move[cy][cx]!=0) {
						visited[cy][cx]= true;
						dq.add(new Integer[] {cy,cx,move[cy][cx]-1});
					}
				}
			}	
			
		}
		int tmp [][] = new int[H][W];
		Deque<Integer> line = new ArrayDeque<Integer>();
		//벽돌 내리기
		for(int x=0;x<W;x++) {
			for(int y=0;y<H;y++) {
				if(move[y][x] !=0 && !visited[y][x]) {
					line.addFirst(move[y][x]);
				}
			}
			int cur=H-1;
			while(!line.isEmpty()) {
				tmp[cur][x]=line.pollFirst();
				cur--;
			}			
		}
		
		return tmp;
	}
	static boolean in_range(int y,int x) {
		return 0<= y && y<H && 0<=x && x<W;
	}

}
