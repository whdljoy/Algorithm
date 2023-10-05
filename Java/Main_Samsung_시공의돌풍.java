package algorithm;


import java.util.*;
import java.io.*;


/**
 * 첫번째 줄에는 방의 크기 n, m과 시간 t가 공백을 사이에 두고 주어집니다.
	
	두번째 줄부터 n개의 줄까지는 방안에 있는 먼지의 양이 공백을 사이에 두고 주어집니다. 시공의 돌풍이 설치되어 있는 칸은 -1로 표시되며 항상 맨 왼쪽에 위치하며,
	 두 칸을 차지합니다. 시공의 돌풍은 맨 윗 행과 맨 아랫 행과 적어도 두 칸 이상 떨어져 있습니다.
	
	6 ≤ n, m ≤ 50
	
	1 ≤ t ≤ 1,000
	
	0 ≤ 처음 각 칸에 주어지는 먼지의 양 ≤ 1,000
 */
public class Main_Samsung_시공의돌풍 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,T;
	static int up,down;
	static int [][] origin;
	static int dx[] = { -1,0,1,0};
	static int dy[] = {0,-1,0,1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	static void run () throws Exception{
		input();

		for(int i=0; i<T;i++) {
			spread();
			clean_up();
			clean_down();
		}
		cal_ans();
	}
	
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer (br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		
		origin = new int [N][M];
		
		
		for(int y=0; y<N; y++) {
			st = new StringTokenizer ( br.readLine());
			for(int x=0; x<M; x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
				if(origin[y][x] ==-1 && up ==0 && down ==0) {
					up = y;
					down =y+1;
				}
			}
		}
		
	}
	
/**
 * 먼지가 인접한 4방향의 상하좌우 칸으로 확산됩니다.

a. 인접한 방향에 시공의 돌풍이 있거나, 방의 범위를 벗어난다면 해당 방향으로는 확산이 일어나지 않습니다.

b. 확산되는 양은 원래 칸의 먼지의 양에 5를 나눈 값이며, 편의상 소숫점은 버립니다.

c. 각 칸에 확산될 때마다 원래 칸의 먼지의 양은 확산된 먼지만큼 줄어듭니다.

d. 확산된 먼지는 방에 있는 모든 먼지가 확산을 끝낸 다음에 해당 칸에 더해지게 됩니다.
 */
	static void spread() {
		int tmp [][]  = new int [N][M];
		for(int y=0;y<N;y++) {
			for(int x=0; x<M;x++) {
				int sp_num = origin[y][x] /5;
				int cnt=0;
				for(int dir=0; dir<4;dir++) {
					int cx= x+dx[dir];
					int cy = y+ dy[dir];
					if(in_range(cy,cx) && origin[cy][cx] !=-1) {
						cnt +=1;
						tmp[cy][cx] +=sp_num;
					}
				}
				tmp[y][x] = tmp[y][x]+ (origin[y][x] -(sp_num*cnt));
			}
		}
		for(int y=0; y<N;y++) {
			for(int x=0; x<M;x++) {
				origin[y][x] = tmp[y][x];
			}
		}
	}
	static void clean_up() {
		//위쪽
		Deque <Integer> cur = new ArrayDeque<>();
		cur.add(0);
		for(int x=1; x<M;x++) {
			cur.add(origin[up][x]);// 아래 일
		}
		for(int y=up-1; y>-1;y--) {
			cur.add(origin[y][M-1]);  
		}
		for(int x=M-2; x>-1;x--) {
			cur.add(origin[0][x]);// 
		}
		for(int y=1; y<up-1 ;y++) {
			cur.add(origin[y][0]);  //내려가기 
		}

		for(int x=1; x<M;x++) {
			origin[up][x] = cur.pollFirst();// 아래 일
		}
		for(int y=up-1; y>-1;y--) {
			origin[y][M-1] =cur.pollFirst();  //위
		}
		for(int x=M-2; x>-1;x--) {
			origin[0][x]= cur.pollFirst();// 위
		}
		for(int y=1; y<up ;y++) {
			origin[y][0] = cur.pollFirst();  //내려가기 
		}		
	}
	
	static void clean_down() {
		//아 
		Deque <Integer> cur = new ArrayDeque<>();
		cur.add(0);
		for(int x=1; x<M;x++) {
			cur.add(origin[down][x]);//  일
		}
		for(int y=down+1; y<N;y++) {
			cur.add(origin[y][M-1]);  //아
		}
		for(int x=M-2; x>-1;x--) {
			cur.add(origin[N-1][x]);// 위
		}
		for(int y=N-2; y>down+1 ;y--) {
			cur.add(origin[y][0]);  //내려가기 
		}

		for(int x=1; x<M;x++) {
			origin[down][x] = cur.pollFirst();// 아래 일
		}
		for(int y=down+1; y<N;y++) {
			origin[y][M-1] =cur.pollFirst();  //위
		}
		for(int x=M-2; x>-1;x--) {
			origin[N-1][x]= cur.pollFirst();// 위
		}
		for(int y=N-2; y>down ;y--) {
			origin[y][0] = cur.pollFirst(); 
		}	
	}
	static boolean in_range(int y , int x) {
		return 0<= y && y<N && 0<=x && x<M;
	}
	static void cal_ans() {
		int ans=0;
		for(int y=0;y<N;y++) {
			for(int x=0;x<M;x++) {
				if(origin[y][x] !=-1) {
					ans+=origin[y][x];					
				}

			}
		}
		System.out.println(ans);
	}
	static void print() {
		for(int y=0; y<N;y++) {
			for(int x=0; x<M;x++) {
				sb.append(origin[y][x]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
