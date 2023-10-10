package algorithm;
import java.util.*;
import java.io.*;
public class Main_Samsung_토끼와경주 {
	static BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int Q;
	static int M;
	static int P;
	static final int MAX_P =2001;
	static HashMap <Integer,Integer> rabbit;
	static int [] dx ={-1,0,1,0};
	static int [] dy = {0,-1,0,1};
	static class Rabbit_in implements Comparable <Rabbit_in>{
		int x;
		int y;
		int pid;
		int jump;
		Rabbit_in (int x,int y, int pid, int jump){
			this.x = x;
			this.y = y;
			this.pid = pid;
			this.jump =jump;
		}
		// (현재까지의 총 점프 횟수가 적은 토끼, 현재 서있는 행 번호 + 열 번호가 작은 토끼, 행 번호가 작은 토끼, 열 번호가 작은 토끼, 고유번호가 작은 토끼) 
		@Override
		public int compareTo (Rabbit_in another) {
			if(this.jump < another.jump) return -1;
			else if (this.jump ==another.jump) {
				if(this.x+this.y < another.x +another.y) return -1;
				else if ((this.x+this.y) ==( another.x+another.y)) {
					if(this.y <another.y) return -1;
					else if (this.y ==another.y) {
						if(this.x <another.x) return -1;
							else if (this.x ==another.x) {
								if(this.pid < another.pid) return -1;
								else return 1;
						}else return 1;
					}else return 1;
				}else return 1;
			}else 	return 1;
			
		}
		
	}
	static class Rabbit_out{
		int x;
		int y;
		int pid;
		int point;
		int L;
		Rabbit_out (int x,int y, int pid, int point ,int L){
			this.x =x;
			this.y =y;
			this.pid =pid;
			this.point = point;
			this.L =L;
		}
		
	}
	static Rabbit_out [] out ;
	static PriorityQueue <Rabbit_in> race;
	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	static void run() throws Exception{
		input();
	}
	/**
	 * 100 N M P pid_1 d_1 pid_2 d_2 ... pid_p d_p 형태로 공백을 사이에 두고 주어집니다. 
	 * 이는 P 마리의 토끼가 N×M 크기의 격자 위에서 경주를 진행하며 i번 토끼의 고유 번호는 pid 
 , 이동해야 하는 거리가 d 임을 뜻합니다. 이 명령은 항상 첫 번째 명령으로만 주어지며, 항상 주어집니다. 또한, 이 명령에 대해서는 출력할 값이 없습니다.
	 * @throws Exception
	 */
	
	static void input() throws Exception{
		Q  = Integer.parseInt(br.readLine());
		StringTokenizer st; 
		for(int i =0; i<Q;i++) {
			 st = new StringTokenizer (br.readLine());
			 int direct = Integer.parseInt(st.nextToken());
			 if(direct ==100) {
				 N = Integer.parseInt(st.nextToken());
				 M = Integer.parseInt(st.nextToken());
				 P = Integer.parseInt(st.nextToken());
				 race = new PriorityQueue<>();
				 out = new Rabbit_out[MAX_P];
				 rabbit = new HashMap<>();
				 for(int p1=1; p1<=P;p1 ++) {
					 int pid = Integer.parseInt(st.nextToken());
					 int d = Integer.parseInt(st.nextToken());
					 race.add(new Rabbit_in(1,1,pid,0));
					 out[p1] = new Rabbit_out(1,1,pid,0,d);	
					 rabbit.put(pid, p1);
				 }
			 }else if (direct ==200) {
				 int K = Integer.parseInt(st.nextToken());
				 int S = Integer.parseInt(st.nextToken());
				 start(K,S);
			 }else if (direct ==300) {
				 int pid = Integer.parseInt(st.nextToken());
				 int L = Integer.parseInt(st.nextToken());
				 change_dis(pid,L);
			 }else if (direct ==400) {
				 get_best();
			 }
			 /**
			  * 경주 진행

				200 K S 형태로 공백을 사이에 두고 주어집니다. 이 명령은 최대 2000번까지만 주어집니다.
				
				이동거리 변경
				
				300 pid_t L 형태로 공백을 사이에 두고 주어집니다. 이 명령은 최대 2000번까지만 주어집니다.
				
				최고의 토끼 선정
				
				400 형태로 주어집니다. 이 명령어는 정확히 마지막 명령으로만 주어지며, 항상 주어집니다.
			  */
		}
		
	}
	
	static void get_best() {
		int ans =0;
		for(int i=1;i<=P;i++) {
			//System.out.println(out[i].point);
			ans =Math.max(ans, out[i].point);
		}
		System.out.println(ans);
	}
	
	/**
	 * 가장 우선순위가 높은 토끼를 뽑아 멀리 보내주는 것을 K번 반복합니다.

		우선순위는 순서대로 (현재까지의 총 점프 횟수가 적은 토끼, 현재 서있는 행 번호 + 열 번호가 작은 토끼, 행 번호가 작은 토끼, 열 번호가 작은 토끼, 고유번호가 작은 토끼) 순입니다. 
		첫 번째 우선순위가 높은 토끼가 한마리 뿐이라면 바로 결정되는 것이고, 동률이라면 두 번째 우선순위를 보고, .. 이러한 규칙에 의해 가장 우선순위가 높은 토끼가 결정됩니다.
		우선순위가 가장 높은 토끼가 결정이 되면 이 토끼를 i번 토끼라 했을 때 상하좌우 네 방향으로 각각 d 만큼 이동했을 때의 위치를 구합니다. 
		이때 이동하는 도중 그 다음 칸이 격자를 벗어나게 된다면 방향을 반대로 바꿔 한 칸 이동하게 됩니다. 
		이렇게 구해진 4개의 위치 중 (행 번호 + 열 번호가 큰 칸, 행 번호가 큰 칸, 열 번호가 큰 칸) 순으로 우선순위를 두었을 때 
		가장 우선순위가 높은 칸을 골라 그 위치로 해당 토끼를 이동시킵니다. 이 칸의 위치를 (r,c)라 했을 때 i번 토끼를 제외한 나머지 P−1마리의 토끼들은 전부 r+c 만큼의 점수를 동시에 얻게 됩니다.

		이렇게 K번의 턴 동안 가장 우선순위가 높은 토끼를 뽑아 멀리 보내주는 것을 반복하게 되며, 이 과정에서 동일한 토끼가 여러번 선택되는 것 역시 가능합니다.

	 * @param K
	 * @param S
	 */
	static void start(int K, int S) {
		boolean [] visited = new boolean [MAX_P];
		for(int rp=0; rp<K; rp++) {
			Rabbit_in cur =race.poll();
			int idx = rabbit.get(cur.pid);
			int pos_x =cur.x;
			int pos_y = cur.y;
			
			//(행 번호 + 열 번호가 큰 칸, 행 번호가 큰 칸, 열 번호가 큰 칸) 
			for(int dir =0; dir<4;dir++) {
				int cx = cur.x +dx[dir] * out[idx].L;
				int cy = cur.y +dy[dir] * out[idx].L;
				if( cx < 1) {
					cx =1;
				}else if (cx >M) {
					cx =M;
				}
				if( cy < 1) {
					cy =1;
				}else if (cy >N) {
					cy =N;
				}
				
				if(pos_x +pos_y < cx+cy) {
					pos_x = cx;
					pos_y = cy;
				}else if((pos_x +pos_y) == (cx+cy)) {
					if(pos_y <cy) {
						pos_x =cx;
						pos_x =cy;
					}else if (pos_y == cy) {
						if(pos_x < cx) {
							pos_x =cx;
							pos_x =cy;
						}
					}
				}
			}
			visited[idx] =true;
			for(int i=1; i<=P;i++) {
				if(i != idx) {
					
					out[i].point = out[i].point+ pos_x+pos_y;
				}
			}
			out[idx].x=pos_x;
			out[idx].y =pos_y;
			System.out.println(pos_y + " "+pos_x);
			race.add(new Rabbit_in(pos_x,pos_y,cur.pid,cur.jump+1));
			
			for(int i=1; i<=P;i++) {
				System.out.println(out[i].point);
				
			}	
			System.out.println();
		}
		/**
		 * 		K번의 턴이 모두 진행된 직후에는 (현재 서있는 행 번호 + 열 번호가 큰 토끼, 행 번호가 큰 토끼, 열 번호가 큰 토끼, 고유번호가 큰 토끼) 순으로 
				우선순위를 두었을 때 가장 우선순위가 높은 토끼를 골라 점수 S를 더해주게 됩니다.
		 		단, 이 경우에는 K번의 턴 동안 한번이라도 뽑혔던 적이 있던 토끼 중 가장 우선순위가 높은 토끼를 골라야만 함에 꼭 유의합니다.
		 */
		int idx=0;
		for(int i=1;i<=P;i++) {
			if(visited[i]) {
				if(idx ==0) {
					idx =i;
				}else {
					if((out[idx].x + out[idx].y)< (out[i].x+ out[i].y)){
						idx =i;
					}else if((out[idx].x + out[idx].y) == (out[i].x+ out[i].y) ){
						if(out[idx].y <out[i].y) {
							idx =i;
						}else if (out[idx].y == out[i].y) {
							if(out[idx].x < out[i].x) {
								idx= i;
							}else if (out[idx].x == out[i].x) {
								if(out[idx].pid < out[i].pid) {
									idx= i;
								}
							}
						}
					}
				}
			}
		}
		out[idx].point = out[idx].point + S;
	}
	static void change_dis(int pid,int L) {
		int idx = rabbit.get(pid);
		out[idx].L = L;
	}
	static boolean in_range(int y, int x) {
		return 0<y && y<=N && 0<x && x<=M;
	}
}
