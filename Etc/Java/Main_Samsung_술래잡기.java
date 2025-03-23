
import java.util.*;
import java.io.*;



public class Main_Samsung_술래잡기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; // 격자 크기
	static int M;// 도망자
	static int K; //k번 반복
	static int H; // H개의 나무
	static final int MAX_N =100;
	static class Thief{
		int y;
		int x;
		int direction;
		boolean isCatched;
		public Thief(int y, int x, int direction,boolean isCatched) {
			super();
			this.y = y;
			this.x = x;
			this.direction = direction;
			this.isCatched = isCatched;
		}
	}
	static Thief  [] thiefs;
	static int [][] backRoutes = new int [MAX_N][MAX_N];
	static int [][] frontRoutes = new int [MAX_N][MAX_N];
	static boolean [][] trees;
	static int [] dx = {1,0,-1,0};
	static int [] dy = {0,1,0,-1};
	static int grip_x;
	static int grip_y;
	static int total_p;
	static boolean isFront;
	public static void main(String[] args) throws Exception{
		run();
	}


	static void run() throws Exception{
		input();
		setRoutes();
		for(int t=1; t<=K; t++) {
			move_theif();
			move_grip();
			catch_thief(t);
		}
		System.out.println(total_p);
	}

	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		thiefs = new Thief[M];
		for(int i=0; i<M;i++) {
			st =new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			thiefs[i] = new Thief(y, x, direction-1,false);
		}
		trees = new boolean[MAX_N][MAX_N];
		for(int i=0; i<H; i++) {
			st =new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			trees[y][x] =true;
		}

		grip_x = N/2+1;
		grip_y = N/2+1;
		total_p = 0;
		isFront = true;
	}


	static void setRoutes() {
		//우 하 좌 상

		int sx = N/2 +1;
		int sy = N/2 +1;
		int dis =1;
		int move_dir =3;
		while(true) {

			for(int s =1; s<=dis; s++) {
				frontRoutes[sy][sx] = move_dir;
				sx = sx +dx[move_dir];
				sy = sy +dy[move_dir];
				backRoutes[sy][sx] = (move_dir + 2) % 4;
				if(sx ==1 && sy ==1) {
					break;
				}
			}
			move_dir = (move_dir + 1) % 4;

			if(move_dir ==1 || move_dir ==3) {
				dis +=1;
			}
			if(sx ==1 && sy ==1) {
				break;
			}
		}
	}


	static void move_theif() {
		for(int i=0; i<M ;i++) {
			Thief cur = thiefs[i];
			int dis = Math.abs(grip_x - cur.x) + Math.abs(grip_y - cur.y);
			if(!cur.isCatched) {
				if(dis <=3) {
					thiefs[i]= get_pos(cur);
				}
			}
		}
	}

	static Thief get_pos(Thief cur) {
		//우 하 좌 상
		int cx = cur.x + dx[cur.direction];
		int cy = cur.y +dy[cur.direction];
		if(in_range(cy, cx)) {
			if(cy == grip_y && cx == grip_x) {
				return cur;
			}else {
				return new Thief(cy, cx, cur.direction,false);
			}
		}
		else {
			int direction = (cur.direction +2) %4;
			cx = cur.x +dx[direction];
			cy = cur.y +dy[direction];
			if(cy == grip_y && cx == grip_x) {
				return cur;
			}else {
				return new Thief(cy, cx, direction,false);
			}
		}
	}

	static void move_grip() {

		if(isFront) {
			int direction = frontRoutes[grip_y][grip_x];
			grip_x += dx[direction];
			grip_y += dy[direction];
		}else {
			int direction = backRoutes[grip_y][grip_x];
			grip_x += dx[direction];
			grip_y += dy[direction];
		}

		if(grip_x == 1 && grip_y ==1) {
			isFront =false;
		}

		if (grip_x == (N/2+1) && grip_y == (N/2+1)) {
			isFront =true;
		}
	}

	static boolean in_range(int y,int x) {
		return 1<= x && x<=N && 1<=y && y<=N;
	}

	static void catch_thief(int turn){
		int [][] can_see =new int [3][2];
		int direction =-1;
		if(isFront) {
			direction = frontRoutes[grip_y][grip_x];
		}else {
			direction = backRoutes[grip_y][grip_x];
		}
		for(int i=0; i<3;i++) {
			int sy = grip_y + dy[direction] *i;
			int sx = grip_x + dx[direction] *i;
			can_see[i][0]= sy;
			can_see[i][1] =sx;
		}
		int cnt =0;
		for(int tf =0; tf<M; tf++) {
			Thief cur = thiefs[tf];
			if(!cur.isCatched) {
				for(int c=0; c<3;c++) {
					int cx = can_see[c][1];
					int cy = can_see[c][0];
					if(in_range(cy, cx) && !trees[cy][cx]){
						if(cy == cur.y && cx == cur.x) {
							thiefs[tf].isCatched=true;
							cnt+=1;
							break;
						}
					}
				}
			}
		}


		total_p += (turn *cnt);
	}
	///////////////////////출력

	static void print_routes() {
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				System.out.print(frontRoutes[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void print_backroutes() {
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				System.out.print(backRoutes[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
