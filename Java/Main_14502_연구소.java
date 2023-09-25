

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class Main_14502_연구소 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb= new StringBuilder();
	static final int WALL = 3;
	static int N;  //세로
	static int M; // 가로
	static int info[][];
	static int ans;
	static List <Integer[]> em;
	static List <Integer[]> virus;
	static int dx[]  = {-1,0,1,0};
	static int dy[] =  {0,-1,0,1};
	static boolean check[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	public static void run() throws Exception{
		input();
		selectWall(0, 0);
		System.out.println(ans);
	}
	public static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		em = new ArrayList<>();
		virus = new ArrayList<>();
		info = new int[N][M];
		for(int y=0; y<N;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0;x<M;x++) {
				info[y][x] = Integer.parseInt(st.nextToken());
				if(info[y][x] == 0) {
					em.add(new Integer[] {y,x});
				}else if (info[y][x]==2) {
					virus.add(new Integer[] { y,x});
				}
			}
		}
		check = new boolean[em.size()];
	}
	
	public static void selectWall(int num,int idx) {
		if(num == WALL) {
			calVirus();
			return;
		}
		for(int i=idx; i<em.size(); i++) {
			if(!check[i]) {
				check[i] = true;
				selectWall(num+1, i+1);
				check[i] = false;
			}
		}
	}
	public static boolean in_range(int x, int y) {
		return 0<= x && x<M && 0<=y && y<N;
	}
	public static void calVirus() {
		boolean visited [][ ] = new boolean[N][M];
		for(int i=0; i<em.size();i++) {
			if(check[i]) {
				Integer [] cur= em.get(i);
				visited[cur[0]][cur[1]] = true;
			}
		}
		
		Deque <Integer[]> dq = new ArrayDeque<Integer[]>();
		for(Integer [] cur : virus) {
			dq.add(cur);
		}
		while(!dq.isEmpty()) {
			Integer [] cur = dq.getFirst();
			dq.pollFirst();
			for(int dir=0;dir<4;dir++) {
				int cx = cur[1] + dx[dir];
				int cy = cur[0] + dy[dir];
				if(in_range(cx, cy)&& info[cy][cx] == 0 && !visited[cy][cx]) {
					dq.add(new Integer[] {cy,cx});
					visited[cy][cx] = true;
				}
			}
		}
		int current =0;
		for(int y=0;y<N;y++) {
			for(int x=0; x<M;x++) {
				if(!visited[y][x] && info[y][x]==0) {
					current+=1;
				}
			}
		}
		ans = Math.max(current, ans);
		
	}

}
