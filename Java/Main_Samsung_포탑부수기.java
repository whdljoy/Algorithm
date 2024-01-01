package workshop;
import java.util.*;
import java.io.*;
public class Main_Samsung_포탑부수기 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,K;
	static int attacker_x;
	static int attacker_y;
	static int attacked_x;
	static int attacked_y;
	static int [] dx = {1,0,-1,0, 1,1,-1,-1};
	static int [] dy = {0,1,0,-1, 1,-1,-1,1};
	static class Tower{
		int x;
		int y;
		int turn;
		int attack;
		Tower (int x,int y,int turn, int attack){
			this.x = x;
			this.y = y;
			this.turn = turn;
			this.attack =attack;
		}
	}
	static class Pair{
		int x;
		int y;
		Pair(int x,int y){
			this.x =x;
			this.y=y;
		}
	}
	static Tower[][] status;
	static boolean [][] route;
	static Pair [][] number_route;
	static boolean isRazor;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	static void run () throws Exception{
		input();
		for(int t=1; t<=K;t++) {
			set_attacker(t);
		///	System.out.println(attacker_x + " "+attacker_y );
			set_attacked();
		//	System.out.println(attacked_x + " "+attacked_y );
			start_attack();			
			cal_tower();
			if(cal_exit()) {
				break;
			}
//			System.out.println(isRazor);
//			print();	
//			print_route();
		}
		cal_ans();
	}	
	
	static void input () throws Exception{
		StringTokenizer st  =new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		status = new Tower[N][M];
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<M; x++) {
				int attack = Integer.parseInt(st.nextToken());
				status[y][x] = new Tower(x, y, 0, attack);
			}
		}
	}
	static void set_attacker(int turn) {
		attacker_x=-1;
		attacker_y=-1;
		for(int y=0;y<N;y++) {
			for(int x=0;x<M;x++) {
				if(status[y][x].attack !=0) {
					if(attacker_x == -1 && attacker_y == -1) {
						attacker_y=y;
						attacker_x=x;
					}else {
						if(cmp_attacker(status[y][x])) {
							attacker_y=y;
							attacker_x=x;
						}						
					}
				}

			}
		}
		status[attacker_y][attacker_x].attack += (N+M);
		status[attacker_y][attacker_x].turn=turn;
	}
	static boolean cmp_attacker(Tower cur) {
		Tower atk =status[attacker_y][attacker_x];
		if(atk.attack > cur.attack) return true;
		else if( atk.attack == cur.attack) {
			if(atk.turn < cur.turn) return true;
			else if(atk.turn == cur.turn) {
				if( (atk.x +atk.y) < (cur.x+cur.y)) return true;
				else if((atk.x +atk.y) == (cur.x+cur.y)) {
					if(atk.x <cur.x) {
						return true;
					}
				}
			}
		}
		return false;
	}
	static void set_attacked() {
		attacked_x=-1;
		attacked_y=-1;
		for(int y=0;y<N;y++) {
			for(int x=0;x<M;x++) {
				if(status[y][x].attack !=0 ) {
					if( attacked_x == -1 && attacked_y == -1 && !(y == attacker_y && x == attacker_x)) {
						attacked_y=y;
						attacked_x=x;
					}else {	
						if(!(y == attacker_y && x == attacker_x)) {
							if(cmp_attacked(status[y][x])) {
								attacked_y=y;
								attacked_x=x;
							}
						}	
					}	
				}
			}
		}
	}
	static boolean cmp_attacked(Tower cur) {
		Tower atd =status[attacked_y][attacked_x];
		if(atd.attack < cur.attack) return true;
		else if( atd.attack == cur.attack) {
			if(atd.turn > cur.turn) return true;
			else if(atd.turn == cur.turn) {
				if( (atd.x +atd.y) > (cur.x+cur.y)) return true;
				else if((atd.x +atd.y) == (cur.x+cur.y)) {
					if(atd.x >cur.x) {
						return true;
					}
				}
			}
		}
		return false;
	}
	static void start_attack() {
		route = new boolean[N][M];
		isRazor = false;
		number_route = new Pair [N][M];
		search_route();
		route[attacker_y][attacker_x]= true;
		route[attacked_y][attacked_x]= true;
		if(!isRazor) {  //포탑공격
			bomb_attack();
		}		
		else {
			cal_razor();
		}
		//print_route();
		for(int y=0; y<N; y++) {
			for(int x=0; x<M;x++) {
				if(route[y][x]) {
					if(!(y == attacker_y && x==attacker_x)) {
						if(y == attacked_y && x== attacked_x) {
							status[y][x].attack -= status[attacker_y][attacker_x].attack;
						}else {
							status[y][x].attack -= (status[attacker_y][attacker_x].attack/2);
						}						
					}
				}
			}
		}		
		
		
		//포탑 부서짐
		for(int y=0; y<N; y++) {
			for(int x=0; x<M;x++) {
				if(status[y][x].attack <0) {
					status[y][x].attack=0;
				}
			}
		}
	}
	
	static void bomb_attack() {
		for(int dir=0;dir<8;dir++) {
			int cx = attacked_x +dx[dir];
			int cy = attacked_y +dy[dir];
			if(!in_range(cx, cy)) {
				if(cy < 0) {
					cy += N;
				}else if (cy >= N) {
					cy -=N;
				}
				if(cx < 0) {
					cx += M;
				}else if (cx >= M) {
					cx -=M;
				}	
			}
			if(status[cy][cx].attack !=0) {
				route[cy][cx] =true;
			}
		}
	}
	static void search_route() {
		boolean [][] visited = new boolean [N][M];
		Deque<Integer []> dq = new ArrayDeque<Integer[]>();
		dq.add(new Integer[] {attacker_y,attacker_x});
		visited[attacker_y][attacker_x]= true;
		
		boolean isEnd= false;
		
		while(!dq.isEmpty()) {
			Integer [] cur =dq.pollFirst();
			for(int dir=0; dir<4;dir++) {
				int cy = cur[0] +dy[dir];
				int cx = cur[1] +dx[dir];
				// 공격자가 위치가 가로 세로
				if(!in_range(cx, cy)) {
					if(cy < 0) {
						cy += N;
					}else if (cy >= N) {
						cy -=N;
					}
					if(cx < 0) {
						cx += M;
					}else if (cx >= M) {
						cx -=M;
					}	
				}
				if(status[cy][cx].attack !=0 && !visited[cy][cx]) {
					number_route [cy][cx] = new Pair(cur[1] ,cur[0]);
					if(cy == attacked_y && cx == attacked_x) {
						isRazor =true;
						return ;
					}
					visited[cy][cx]=true;
					dq.add(new Integer[] {cy,cx});
				}				
			}			
		}
		


	}
	
	static boolean in_range(int x,int y) {
		return 0<=x && x<M && 0<=y && y<N;
	}
	static void cal_tower() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(status[y][x].attack !=0 && !route[y][x]) {
					status[y][x].attack +=1;
				}
			}
		}
	}
	
	static void cal_ans() {
		int ans=0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				ans = Math.max(ans, status[y][x].attack);
			}
		}
		System.out.println(ans);
	}
	static void print_route() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				System.out.print(route[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void print() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				System.out.print(status[y][x].attack + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean cal_exit() {
		int cnt =0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(status[y][x].attack !=0) {
					cnt+=1;
				}
			}
		}
		if(cnt <=1) {
			return true;
		}
		return false;
	}
	
	static void cal_razor() {
		Pair cur = number_route[attacked_y][attacked_x];
		while(true) {
			if(cur.y ==attacker_y && cur.x ==attacker_x) {
				break;
			}
			route[cur.y][cur.x]= true;
			cur = number_route[cur.y][cur.x];
		}
	}
}
