import java.util.*;
import java.io.*;



public class Main_Samsung_미로타워디펜스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int center_y;
	static int center_x;
	static int N;
	static int M;
	static int [][] status;
	static int point;
	static int [] dx= {1,0,-1,0};
	static int [] dy = {0,1,0,-1};
	static int [][] player;
	static Deque<Integer> info;
	static int [][]tmp;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	static void run()throws Exception {
		input();
		for(int i=0; i<M; i++) {
			attack(i);
			get_num();
			check_num();
			fill_status();
			for(int y=0;y<N;y++) {
				for(int x=0;x<N;x++) {
					status[y][x] = tmp[y][x];
				}
			}
//			print();
	//		System.out.println(point);
		}
		System.out.println(point);
	}
	
	/**
	 * 첫번째 줄에는 격자의 크기 n, 총 라운드 수 m이 주어집니다.
		
		이후 두번째 줄부터 n+1번째 줄까지 몬스터의 종류가 주어집니다. 0은 비어있는 칸을 의미합니다.
		
		이후 m개의 줄에는 각 라운드마다의 플레이어의 공격 방향 d과 공격 칸 수 p가 주어집니다.
		
		d는 0번부터 3번까지 각각 → ↓ ← ↑으로 주어집니다.


	 * @throws Exception
	 */
	static void input() throws Exception{
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		status = new int [N][N];
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				status[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		player = new int [M][2];
		for(int i=0; i<M ;i ++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			player [i][0] = direction;
			player [i][1] = P;
		}
		center_x = N/2;
		center_y = N/2;
		info = new ArrayDeque<> ();
	}
	static void attack(int idx) {
		int [] monster = new int [4];
		for(int dis=1; dis<= player[idx][1]; dis++) {
			int num = status[center_y+dy[player[idx][0]]*dis][center_x + dx[player[idx][0]]*dis];
			if( num !=0) {
				monster[num] +=1;
				status[center_y+dy[player[idx][0]]*dis][center_x + dx[player[idx][0]]*dis] = 0 ;				
			}

		}
		for(int i=1;i<=3;i++) {
			point = point + i*monster[i];
		}
	}
	static void get_num() {
		int sx = center_x;
		int sy = center_y;
		int cnt =1;
		int num =0;
		int each =0;
		int x_direction = -1;
		int y_direction = 1;
		while(true) {
			for(int x=1;x<=cnt;x++) {
				sx += x_direction;
				if(status[sy][sx] != 0) {
					info.add(status[sy][sx]);
				}
				if(sx ==0 && sy==0) {
					return;
				}	
			}		
			for (int y=1; y<=cnt; y++) {
				sy += y_direction;
				if(status[sy][sx] != 0) {
					info.add(status[sy][sx]);
				}
				if(sx ==0 && sy==0) {
					return;
				}				
			}
			y_direction *=-1;
			x_direction *=-1;
			cnt +=1;
		}		
	}
	static void check_num() {
		while(true) {
			int sz = info.size();
			int num =info.peekFirst();
			int each=0;
			for(int rp =0; rp<sz;rp++) {
				int cur_num  = info.pollFirst();
				if(cur_num != num) {
					if(each >3) {
						point += each*num;
					}else {
						for(int i=0; i<each;i ++) {
							info.add(num);
						}
						
					}
					each =1;
				}else {
					each +=1;
				}
				num = cur_num;	
			}
			
			if(each >3) {
				point += each*num;
			}else {
				for(int i=0; i<each;i ++) {
					info.add(num);
				}	
			}
			if(info.size() == sz) {
				break;
			}
		}
		
	}
	
	static void fill_status() {
		tmp = new int [N][N];
		List <Integer> current = new ArrayList<Integer>();
		int num =0;
		int each =0;
		while(!info.isEmpty()) {
			int cur_num = info.pollFirst();
			if(num==0) {
				num = cur_num;
				each +=1;
			}else {
				if(cur_num != num) {
					current.add(each);
					current.add(num);
					num =cur_num;
					each =1;
				}else {
					each+=1;
				}
			}
		}
		
		current.add(each);
		current.add(num);
		int sx = center_x;
		int sy = center_y;
		int cnt =0;
		int x_direction = -1;
		int y_direction =  1;	
		int t_cnt =1;
		while(true) {
			for(int x=1;x<=t_cnt;x++) {
				sx += x_direction;	
				tmp[sy][sx] =current.get(cnt ++);
				if((sx ==0 && sy==0) || current.size() == cnt) {
					return;
				}	
			}
		
			for(int y=1;y<=t_cnt;y++) {
				sy += y_direction;
				tmp[sy][sx] =current.get(cnt++);
				if((sx ==0 && sy==0) || current.size() == cnt) {
					return;
				}					
			}
			y_direction *=-1;
			x_direction *=-1;
			t_cnt+=1;
		}
	}
	
	static void print() {
		for(int y=0;y<N;y++) {
			for(int x=0;x<N;x++) {
				System.out.print(status[y][x] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
