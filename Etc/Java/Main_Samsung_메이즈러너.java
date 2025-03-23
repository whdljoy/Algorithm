
import java.util.*;
import java.io.*;

public class Main_Samsung_메이즈러너 {
	static BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,K;
	static class Atom{
		int person;
		int data;
		Atom(int person,int data){
			this.person = person;
			this.data = data;
			
		}
	}
	static Atom [][] origin;
	static int [] dx= {-1,1,0,0};
	static int [] dy = {0,0,1,-1};
	static int exit_x;
	static int exit_y;
	static int ans_dis=0;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	
	static void run () throws Exception{
		input();
		simulate();
	}
/**
 * 첫 번째 줄에 N, M, K가 공백을 사이에 두고 주어집니다.

다음 N개의 줄에 걸쳐서 N×N 크기의 미로에 대한 정보가 주어집니다.

0이라면, 빈 칸을 의미합니다.
1이상 9이하의 값을 갖는다면, 벽을 의미하며, 해당 값은 내구도를 뜻합니다.
다음 M개의 줄에 걸쳐서 참가자의 좌표가 주어집니다. 모든 참가자는 초기에 빈 칸에만 존재합니다.

다음 줄에 출구의 좌표가 주어집니다. 출구는 빈 칸에만 주어집니다.

N: 미로의 크기 (4≤N≤10)
M: 참가자 수 (1≤M≤10)
K: 게임 시간 (1≤K≤100)	
 * @throws Exception
 */
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer (br.readLine());
		N  = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		origin = new Atom[N][N];
		for(int y=0; y<N;y++) {
			st = new StringTokenizer (br.readLine());
			for(int x=0; x<N; x++) {
				origin[y][x] = new Atom(0,Integer.parseInt(st.nextToken()));
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			origin[y][x].person +=1;
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken())-1;
		int x = Integer.parseInt(st.nextToken())-1;
		exit_x= x;
		exit_y =y;
		origin[y][x].data =10;  // 출구 
	}
	
/**
 * 1초마다 모든 참가자는 한 칸씩 움직입니다. 움직이는 조건은 다음과 같습니다.

두 위치 (x1,y1), (x2,y2)의 최단거리는 ∣x1−x2∣+∣y1−y2∣로 정의됩니다.
모든 참가자는 동시에 움직입니다.
상하좌우로 움직일 수 있으며, 벽이 없는 곳으로 이동할 수 있습니다.
움직인 칸은 현재 머물러 있던 칸보다 출구까지의 최단 거리가 가까워야 합니다.
움직일 수 있는 칸이 2개 이상이라면, 상하로 움직이는 것을 우선시합니다.
참가가가 움직일 수 없는 상황이라면, 움직이지 않습니다.
한 칸에 2명 이상의 모험가가 있을 수 있습니다.
모든 참가자가 이동을 끝냈으면, 다음 조건에 의해 미로가 회전합니다.

한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡습니다.
가장 작은 크기를 갖는 정사각형이 2개 이상이라면, 좌상단 r 좌표가 작은 것이 우선되고, 그래도 같으면 c 좌표가 작은 것이 우선됩니다.
선택된 정사각형은 시계방향으로 90도 회전하며, 회전된 벽은 내구도가 1씩 깎입니다.
K초 동안 위의 과정을 계속 반복됩니다. 만약 K초 전에 모든 참가자가 탈출에 성공한다면, 게임이 끝납니다. 게임이 끝났을 때, 모든 참가자들의 이동 거리 합과 출구 좌표를 출력하는 프로그램을 작성해보세요.	
 */
	static void simulate() {
		for(int time =0; time <K;time++) {
			move();
			print();
			select_square();
			print();
		}
//		sb.append(ans_dis).append("\n").append(exit_y).append(" ").append(exit_x).append("\n");
		System.out.println(sb);
		
//		print();
	}
	static  void move() {
		for(int y=0; y< N; y++) {
			for(int x=0; x<N;x++) {
				if(origin[y][x].person >0) {
					select_pos(y,x);
				}
			}
		}
	}
	static void select_pos(int y, int x) {
		int tx = x;
		int ty= y;
		int dis =Math.abs(x-exit_x) + Math.abs(y-exit_y);
		for(int dir=0; dir<4;dir++) {
			int cx = x + dx[dir];
			int cy = y + dy[dir];
			if(in_range(cy,cx) && origin[cy][cx].data ==0 ) {
				int cur_dis =Math.abs(cx-exit_x) + Math.abs(cy-exit_y);
				if(dis <= cur_dis) { // 
					dis = cur_dis;
					tx = cx;
					ty = cy;
				}	
			}
		}
		if(tx !=x && ty != y) {
			
			origin[ty][tx].person += origin[y][x].person;
			ans_dis +=origin[y][x].person;
			origin[y][x].person =0;		
		}
		
		
	}
	static boolean in_range(int y , int x) {
		return 0<= y && y<N && 0<=x && x<N;
	}
	
	static void print() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				sb.append(origin[y][x].person).append(" ");
			}
			sb.append("\n");
		}
		sb.append("\n");
	}
	static void select_square() {
		for(int sz =2; sz <=N;sz++) {
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					if(x<= exit_x && exit_x <x+sz && y<=exit_y  && exit_y<y+sz && in_range(x+sz-1,y+sz-1)) {
						for(int i=y;i<y+sz;i++) {
							for(int j=x; j<x+sz;j++) {
								if(origin[i][j].person >0) {
									rotate(y,x,sz);
									return ;
								}
							}
						}
					}
				}
			}
		}
	}
	static void rotate(int y,int x, int sz) {
		Atom [][] tmp = new Atom[sz][sz];
		for(int i =0; i< sz;i++) {
			for(int j=0; j< sz;j++) {
				tmp[j][sz-1-i] = origin[i+y][j+x];
			}
		}
		for(int i =y; i< y+sz;i++) {
			for(int j=x; j< x+ sz;j++) {
				origin[i][j] = tmp[i-y][j-x];
				if(origin[i][j].data==10) {
					exit_x=j;
					exit_y=i;
				}
			}
		}	
		sb.append(x).append(" ").append(y).append(" ").append(sz).append("\n").append(exit_x).append(" ").append(exit_y).append("\n");		
	}
}
