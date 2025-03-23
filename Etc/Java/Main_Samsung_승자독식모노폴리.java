
import java.util.*;
import java.io.*;
public class Main_Samsung_승자독식모노폴리 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int MAX_N = 20;
	static final int MAX_M = 401;
	static final int MAX_K = 1000;
	static int N,K,M;
	static int [][] origin;
	static int [][] turn;
	static int [] dx = {0,0,0,-1,1};
	static int [] dy = {0,-1,1,0,0};
	static int ans=0;
	static class Person {
		int x;
		int y;
		int [][] dir;
		int direction;
		boolean in;
		Person(int x ,int y,int direction,boolean in){
			this.x=x;
			this.y=y;
			this.in = in;
			this.direction=direction;
			dir = new int [5][5];
		}
	}
	static Person [] info;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	static void run() throws Exception{
		input();
		for(int t=1;t<=1000;t++) {
			move();
			if(check_ans()) {
				ans =t;
				break;
			}
			if(!info[1].in) {
				ans =-1;
				break;
			}
			check_turn();	
		}
		if(ans ==0) {
			ans =-1;
		}
		System.out.println(ans);
	}
	static void print() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				System.out.print(origin[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		origin = new int [MAX_N][MAX_N];
		turn = new int[MAX_N][MAX_N];
		info = new Person[MAX_M];
		for(int i=0; i<MAX_M;i++) {
			info[i] = new Person(-1, -1,0,false);
		}
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N;x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());  //각 위치별 몇번 플레이어가 가지고있는지
				if(origin[y][x] !=0) {
					turn[y][x] = K;   //각 위치별 turn 
					info[origin[y][x]].x= x;  
					info[origin[y][x]].y= y;
					info[origin[y][x]].in = true;	
				}
				
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M;i++) {
			info[i].direction = Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=M;i++) {
			for(int d =1; d<=4;d++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<5;j++) {
					info[i].dir[d][j] = Integer.parseInt(st.nextToken());	
				}
			}	
		}
	}
/**
 * 턴이 한 번 진행될 때 각 플레이어들은 한 칸씩 이동합니다. 해당 칸에 이동했을 때 플레이어는 해당 칸을 독점 계약하게 됩니다. 초기 상태에 위치한 땅 역시 해당 플레이어의 독점 계약한 칸입니다.

	각 플레이어는 각 방향별로 이동 우선순위를 가지게 됩니다. 우선 플레이어는 본인에게 인접한 상하좌우 4 칸 중 아무도 독점계약을 맺지 않은 칸으로 이동하고 만약 그러한 칸이 없을 경우에는 인접한 4방향 중 본인이 독점계약한 땅으로 이동합니다. 
	이때 이동할 수 있는 칸이 여러개일 수 있음으로 이동 우선순위에 따라 움직일 칸을 결정하게 됩니다. 플레이어가 보고 있는 방향은 그 직전에 이동한 방향입니다.

	모든 플레이어가 이동한 후 한 칸에 여러 플레이어가 있을 경우에는 가장 작은 번호를 가진 플레이어만 살아남고 나머지 플레이어는 게임에서 사라지게 됩니다.
 */
	static void move() {
		int [][] player = new int [MAX_N][MAX_N];
		for(int idx=M;idx>0;idx--) {
			Person current = info[idx];
			boolean isContract =false; 
			if(current.in) {
				for(int dir=1;dir<=4;dir++) {
					int cx = current.x + dx[current.dir[current.direction][dir]];
					int cy = current.y + dy[current.dir[current.direction][dir]];
					if(in_range(cy, cx) && origin[cy][cx] == 0) {
						if(player[cy][cx] !=0) {
							int before = player [cy][cx];
							info[before].in = false;
						}
						player[cy][cx] = idx;
						current.x=cx;
						current.y=cy;
						current.direction=current.dir[current.direction][dir];	
						isContract = true;
						break;
					}
				}
				if(!isContract) {
					for(int dir=1;dir<=4;dir++) {
						int cx = current.x + dx[current.dir[current.direction][dir]];
						int cy = current.y + dy[current.dir[current.direction][dir]];
						if(in_range(cy, cx) && origin[cy][cx] == idx) {
							if(player[cy][cx] !=0) {
								int before = player [cy][cx];
								info[before].in = false;
							}
							player[cy][cx] = idx;
							current.x=cx;
							current.y=cy;
							current.direction=current.dir[current.direction][dir];	
							break;
						}
					}	
				}
				
				info[idx] = current;	
			}
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(player[y][x] !=0) {
					origin[y][x] = player[y][x];
					turn[y][x] = K;
				}
			}
		}
		
	}
	static void check_turn() {
		boolean [][] cur = new boolean[MAX_N][MAX_N];
		for(int i=1;i<=M;i++) {
			if(info[i].in) {
				cur[info[i].y][info[i].x] =true;
			}
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(turn[y][x] !=0 && !cur[y][x]) {
					turn[y][x] -=1;
					if(turn[y][x] ==0) {
						origin[y][x] =0;
					}
				}
			}
		}
	}
	static boolean in_range(int y, int x) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	static boolean check_ans() {
		for(int i=2;i<=M;i++) {
			if(info[i].in) {
				return false;
			}
		}
		
		if(info[1].in) {
			return true;
		}
		return false;
	}
}
