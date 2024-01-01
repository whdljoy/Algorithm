package algorithm;
import java.util.*;
import java.io.*;

public class Main_Samsung_원자충돌 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int K,N,M;
	static List <Atom> origin [][];
	static int time =0;
	static int ans =0;
	static int [] dx= {0,1,1,1,0,-1,-1,-1  };
	static int [] dy = {-1,-1,0,1,1,1,0,-1 };
	static StringBuilder sb = new StringBuilder();
	static class Atom{
		int weight;
		int direction;
		int speed;
		Atom(int weight,  int speed,int direction){
			this.weight = weight;
			this.direction = direction;
			this.speed =speed;
			
		}
		
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	static void run() throws Exception{
		input();
		simulate();
	}
	// m+1번재 줄까지 원자의 정보가 한 줄씩 주어집니다. 원자의 정보는 위치 x, y, 질량 m, 속력 s, 방향 d이 공백을 사이에 두고 주어집니다.
	//방향 d는 0부터 7까지 순서대로 ↑, ↗, →, ↘, ↓, ↙, ←, ↖ 의미합니다.
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer (br.readLine());
		N = Integer.parseInt(st.nextToken());  
		M = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken());
		origin = new ArrayList [N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				origin[y][x] = new ArrayList<>();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y =Integer.parseInt(st.nextToken())-1;
			int x =Integer.parseInt(st.nextToken())-1;
			int weight =Integer.parseInt(st.nextToken());
			int s =Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			origin[y][x].add(new Atom(weight,s,d));
			
		}
		
		
	}
/**
 * 모든 원자는 1초가 지날 때마다 자신의 방향으로 자신의 속력만큼 이동합니다.

이동이 모두 끝난 뒤에 하나의 칸에 2개 이상의 원자가 있는 경우에는 다음과 같은 합성이 일어납니다.

a. 같은 칸에 있는 원자들은 각각의 질량과 속력을 모두 합한 하나의 원자로 합쳐집니다.

b. 이후 합쳐진 원자는 4개의 원자로 나눠집니다.

c. 나누어진 원자들은 모두 해당 칸에 위치하고 질량과 속력, 방향은 다음 기준을 따라 결정됩니다.

질량은 합쳐진 원자의 질량에 5를 나눈 값입니다.

속력은 합쳐진 원자의 속력에 합쳐진 원자의 개수를 나눈 값입니다.

방향은 합쳐지는 원자의 방향이 모두 상하좌우 중 하나이거나 대각선 중에 하나이면, 각각 상하좌우의 방향을 가지며 그렇지 않다면 대각선 네 방향의 값을 가집니다.

편의상 나눗셈 과정에서 생기는 소숫점 아래의 수는 버립니다.

d. 질량이 0인 원소는 소멸됩니다.

이동 과정 중에 원자가 만나는 경우는 합성으로 고려하지 않습니다.
 */
	
	static void simulate() {
		while(true) {
			time +=1;
			move();
			check_multi();
			
			if(time ==K) {
				break;
			}
		}
		get_ans();
		System.out.println(ans);
	}
	
	static void move() {   //자산의 방향으로 자신의 속력만큼 이동 // 넘어서면 넘어선 대로간
		List <Atom> tmp [][] = new ArrayList[N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				tmp[y][x] = new ArrayList<>();
			}
		}		
		for(int y=0; y<N;y++) {
			for(int x=0; x<N; x++) {
				if(origin[y][x].size() !=0) {
					change(x,y, tmp);
				}
			}
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				origin[y][x] = tmp[y][x];
			}
		}			
	}
	static void change(int x,int y,List <Atom> tmp [][]) {

		for(Atom each : origin[y][x]) {
			int cx = x + each.speed *dx[each.direction];
			int cy = y + each.speed *dy[each.direction];
			while(true) {
				if( cx >=N ) {
					cx = cx-N;
				}
				else if (cx < 0) {
					cx = cx +N;
				}else {
					break;
				}
			}
			
			while(true) {
				if (cy >=N) {
					cy = cy -N;
					
				}else if (cy < 0) {
					cy = cy +N;
				}else {
					break;
				}
			}			
			
			tmp[cy][cx].add(each);
			
		}
	}
	static void check_multi() {
		for(int y=0; y<N;y++) {
			for(int x=0; x<N; x++) {
				if(origin[y][x].size() >=2) {
					plus(x,y);
				}
			}
		}		
	}
	
	/**
	 * 이동이 모두 끝난 뒤에 하나의 칸에 2개 이상의 원자가 있는 경우에는 다음과 같은 합성이 일어납니다.
	
	a. 같은 칸에 있는 원자들은 각각의 질량과 속력을 모두 합한 하나의 원자로 합쳐집니다.
	
	b. 이후 합쳐진 원자는 4개의 원자로 나눠집니다.
	
	c. 나누어진 원자들은 모두 해당 칸에 위치하고 질량과 속력, 방향은 다음 기준을 따라 결정됩니다.
	
	질량은 합쳐진 원자의 질량에 5를 나눈 값입니다.
	
	속력은 합쳐진 원자의 속력에 합쳐진 원자의 개수를 나눈 값입니다.
	
	방향은 합쳐지는 원자의 방향이 모두 상하좌우 중 하나이거나 대각선 중에 하나이면, 각각 상하좌우의 방향을 가지며 그렇지 않다면 대각선 네 방향의 값을 가집니다.
	
	편의상 나눗셈 과정에서 생기는 소숫점 아래의 수는 버립니다.
	
	d. 질량이 0인 원소는 소멸됩니다.
	/방향 d는 0부터 7까지 순서대로 ↑, ↗, →, ↘, ↓, ↙, ←, ↖ 의미합니다.
	 *                     상  : 0  우 : 2 하 :4 우 6

	 */
	static void plus(int x, int y) {
		int t_w =0;
		int t_s=0;
		boolean dir [] = new boolean [8];
		int cross = 0;
		int found = 0;
		for(Atom cur : origin[y][x]) {
			t_w += cur.weight;
			t_s += cur.speed;
			dir[cur.direction] = true;
		}
		if(dir[0] && dir[2] &&  dir[4] && dir[6])	found = 1;  //전부 상하좌우
		if(!dir[0] && !dir[2] &&  !dir[4] && !dir[6]) found =2;  // 하나도 상하좌우x  
		if(dir[1] &&  dir[3] &&  dir[5] && dir[7]) cross = 1;
		if(!dir[1] &&  !dir[3] &&  !dir[5] && !dir[7]) cross = 2;		
				
	 
		List <Atom> current = new ArrayList<>();
		if(t_w /5 >0) {
			if( (found == 2 && cross !=2 )|| (found !=2 && cross==2)) {
				for(int i=0; i<8;i +=2) {
					current.add(new Atom(t_w/5, t_s/origin[y][x].size(),i));
				}
			}else {
				for(int i=1; i<8;i +=2) {
					current.add(new Atom(t_w/5, t_s/origin[y][x].size(),i));
				}				
			}
		}
		origin[y][x] = current;
	}
	
	static  void get_ans() {
		for(int y=0; y<N;y++) {
			for(int x=0; x<N; x++) {
				if(origin[y][x].size() !=0) {
					for( Atom  cur : origin[y][x]) {
						ans += cur.weight;
					}
				}
			}
		}	
	}
	
	static void print() {
		for(int y=0; y<N;y++) {
			for(int x=0; x<N; x++) {
				if(origin[y][x].size() !=0) {
					sb.append(y).append(" ").append(x);
				}
			}
		}	
		System.out.println(sb);
	}
}
