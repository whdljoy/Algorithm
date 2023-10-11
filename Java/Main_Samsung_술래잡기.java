package workshop;
import java.util.*;


import java.io.*;
public class Main_Samsung_술래잡기 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb =new StringBuilder();
	static int N;
	static int M; // 도망 자수 
	static int H;  // 나무 수
	static int K; 
	static boolean [][] tree;
	static int [] dx = {0,-1,0,1,0};
	static int [] dy = {0,0,1,0,-1};
	static final int MAX_N =100;
	static Deque <Pair> front;
	static Deque <Pair> back;
	static boolean isFront;  //back에서 위치를 가져올 지 front 에서가져올지
	static class Pair{
		int y;
		int x;
		int direction;
		public Pair(int y, int x,int direction) {
			super();
			this.y = y;
			this.x = x;
			this.direction =direction;
		}
		
	}
	static class Thief{
		int x;
		int y;
		int direction;
		boolean isCatched;
		Thief(int y, int x, int direction, boolean isCatched){
			this.y=y;
			this.x=x;
			this.direction =direction;
			this.isCatched = isCatched;
		}
	}
	static Thief [] thiefs;
	static class Catcher{
		int x;
		int y;
		int direction;
		int point;
		public Catcher(int y, int x, int direction, int point) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.point = point;
		}
		
	}
	static Catcher current;
	public static void main(String[] args) throws Exception {
		run();
	}
	
	static void run() throws Exception{
		input();
		set_route();
		Pair start = front.pollFirst();
		front.add(start);
		current = new Catcher(start.y, start.x, start.direction, 0);
		for(int t=1;t<=K;t++) {
			move_thief();
			move_catcher();
			catch_thief(t);
		}
		System.out.println(current.point);
	}
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		thiefs = new Thief[M];
		isFront = true;
		// d =1  좌    d =2 하    d =3 우   d =4 상
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			thiefs[i] = new Thief(y, x, direction,false);
		}
		tree = new boolean[MAX_N][MAX_N];
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			tree[y][x] = true;
		}
		front = new ArrayDeque<>();
		back = new ArrayDeque<>();
		
	}
	
	
	static void move_thief() {
		for(int i=0;i<M;i++) {
			Thief cur_thief = thiefs[i];
			if(!cur_thief.isCatched) {
				int dis = Math.abs(cur_thief.x - current.x) + Math.abs(cur_thief.y - current.y);
				if(dis <=3) {
					Thief pos= get_pos(cur_thief);
					thiefs[i] = pos;
				}	
			}
		}
	}
	
	static Thief get_pos (Thief cur_thief) {
		int cx =cur_thief.x +dx[cur_thief.direction];
		int cy= cur_thief.y + dy[ cur_thief.direction];
		if(in_range(cy, cx)) {
			//현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우	
			//움직이려는 칸에 술래가 있는 경우라면 움직이지 않습니다.
			if(cy == current.y && cx ==current.x) {
				return cur_thief;
			}else {
				return new Thief(cy, cx, cur_thief.direction,false);
			}
		}
		else {
			if(cx <1) {
				cx =1;
			}else if(cx >N ) {
				cx =N;
			}
			if(cy <1) {
				cy =1;
			}else if(cy >N ) {
				cy =N;
			}			
			if(cy == current.y && cx ==current.x) {
				return cur_thief;
			}else {
				int direction = (cur_thief.direction + 2) % 5;
				return new Thief(cy, cx, direction,false);
			}			
		}

	}
	
	
	static void move_catcher() {
		if(isFront) {
			Pair cur = front.pollFirst();
			front.add(cur);
			if(cur.x == 1 && cur.y ==1 ) {
				isFront = false;
				cur = back.pollFirst();
				back.add(cur);
			}
			current.x = cur.x;
			current.y = cur.y;
			current.direction = cur.direction;
		}else {
			Pair cur = back.pollFirst();
			back.add(cur);
			if(cur.x == N/2+1 && cur.y ==N/2+1 ) {
				isFront = true;
				cur = front.pollFirst();
				front.add(cur);
			}
			current.x = cur.x;
			current.y = cur.y;
			current.direction = cur.direction;		
		}
	}
	
	static boolean in_range(int y,int x) {
		return 1<=x && x<= N && 1<=y && y<=N;
	}
	
	static void set_route() {
		int sx = N/2+1;
		int sy = N/2+1;
		// d =1  좌    d =2 하    d =3 우   d =4 상
		int d= 4;
		int cnt =1;
		front.add(new Pair(sx, sy, d));
		while(true) {
			for(int y=1;y<=cnt;y++) {
				sy += dy[d];
				if(y ==cnt) {
					d = d -1;
					if (d ==0) {
						d =4;
					}	
				}				
				front.add(new Pair(sy, sx, d));
				if(sy ==1 &&sx ==1) {
					break;
				}
			}
			if(sy ==1 &&sx ==1) {
				break;
			}
			for(int x=1;x<=cnt;x++) {
				sx += dx[d];
				if(x ==cnt) {
					d = d -1;
					if (d ==0) {
						d =4;
					}	
				}
				front.add(new Pair(sy, sx, d));
				if(sy ==1 &&sx ==1) {
					break;
				}				
			}
			if(sy ==1 &&sx ==1) {
				break;
			}
			cnt +=1;			
		}
		
		int sz = front.size();
		for(int i=0;i<sz;i++) {
			Pair cur = front.pollFirst();
			front.add(cur);
			int direction =( cur.direction+2) %5;
			back.addFirst(new Pair(cur.y, cur.x, direction));			
		}			
	}
	static void catch_thief(int turn) {
		int [][] can = new int [3][2];
		for(int i=0; i<3;i++) {
			int cx = current.x + dx[current.direction] * i;
			int cy = current.y + dy[current.direction] * i;
			can[i][0] = cy;
			can[i][1] = cx;
			//System.out.println(cy +" :" +cx +"dir");
		}
		int cnt =0;
		print_thiefs();
		for(int i=0; i<M; i++) {
			if(!thiefs[i].isCatched) {
				for(int c =0; c<3;c++) {
					int x = can[c][1];
					int y = can[c][0];
					if (in_range(y, x) && !tree[y][x]) {
						if(x == thiefs[i].x && y==thiefs[i].y) {
							thiefs[i].isCatched=true;
							cnt+=1;
						}
					}
	
				}
			}
		}
		System.out.println(turn +" :" +cnt);
		current.point += (turn*cnt);
	}
	
	////////////////////////////출력//////////////
	static void print_front() {
		while(!front.isEmpty()) {
			Pair cur = front.pollFirst();
			System.out.println(cur.x + " "+ cur.y + " "+ cur.direction);
		}
	}
	static void print_thiefs() {
		for(int i=0; i<M;i++) {
			System.out.println("theif" + thiefs[i].x + " "+thiefs[i].y);
		}
	}
	
}
