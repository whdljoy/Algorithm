
import java.util.*;

import java.io.*;



// 모든칸에 디폴트 양분 5
// (r,c) ==(y,x) 좌표 1부터 시작
// N*N  
//사계절을 보낸다 



//가을 : 나무 번식 -> 번식하는 나무는 나이가 5의 배수 인접한 8개의 칸에 나이가 1인 나무 생김
// 

//겨울 : 땅에 양분 추가 입력되는 만큼 


//총 k 년이 지난 후 상도의 땅에 살아있는 나무의 갯수

public class  boj16235{
	static int M,N,K;
	static Deque<Integer> [][] ground;
	static List<Integer> [][] dead;
	static int [][] food;
	static int [][] start;
	static int [] dx = {-1, 0,1,0,-1,-1,1,1};
	static int [] dy = {0,-1,0,1,1,-1,1,-1};
	static int ans;
	static final int MAX_END=101;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		for(int i=0; i<K ;i ++) {
			spring();
			summer();
			fall();
			winter();
		}
		print_ans();
	}
	
	public static void input(BufferedReader br) throws IOException{
		StringTokenizer st;
		st =new StringTokenizer(br.readLine());
		N  = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new ArrayDeque[N+1][N+1];
		food = new int[N+1][N+1];
		start = new int[N+1][N+1];
		for(int y=0;y<=N;y++) {
			for(int x=0;x<=N;x++) {
				ground[y][x] = new ArrayDeque<Integer>();
			}
		}
		for(int y=1;y<=N; y++) {
			st =new StringTokenizer(br.readLine());
			for(int x=1;x<=N; x++) {
				food[y][x] = 5;
				start[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<M;i++) {
			st= new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			ground[y][x].addLast(z);
		}
	}
	
	// 봄: 나무가 자신의 나이 만큼 +양분, 나이 1 증가,
	//하나의 칸에 여러개의 나무가 있다면 나의가 어린나무부터 양분
	//양분이 부족하다면  사망	
	public static void spring() {
		dead = new ArrayList[N+1][N+1];
		for(int y=0;y<=N;y++) {
			for(int x=0;x<=N;x++) {
				dead[y][x] = new ArrayList<Integer>();
			}
		}		
		for(int y=1;y<=N; y++) {
			for(int x=1; x<=N;x++) {
				if(!ground[y][x].isEmpty()){
					int num = ground[y][x].size();
					for(int idx=0; idx<num; idx++) {
						int tree = ground[y][x].pollFirst();
						if(tree <= food[y][x]) {
							food[y][x] -= tree;
							ground[y][x].addLast(tree+1);
						}else{
							dead[y][x].add(tree);
						}
					}
				}
			}
		}
	}
	//여름 : 죽은 나무가 양분으로 변함 죽은 나무의 나이를 2로나눈 값이 양분 추가 소수점 아래 버림
	public static void summer() {
		for(int y=1;y<=N; y++) {
			for(int x=1; x<=N; x++) {
				if(dead[y][x].size()>0) {
					for(int d : dead[y][x]) {
						food[y][x] = food[y][x]+ d/2;
					}
				}
			}
		}
	}
	public static boolean in_range(int x,int y) {
		return 1<= x && x<=N && 1<=y && y<=N;
	}
	//가을 : 나무 번식 -> 번식하는 나무는 나이가 5의 배수 인접한 8개의 칸에 나이가 1인 나무 생김
	public static void fall() {
		int [][] count = new int[N+1][N+1];
		for(int y=1;y<=N; y++) {
			for(int x=1;x<=N; x++) {
				if(!ground[y][x].isEmpty()) {
					int num = ground[y][x].size();
					for(int idx=0; idx<num; idx++) {
						int tree =ground[y][x].pollFirst();
						if(tree % 5 ==0) {
							for(int dir=0; dir<8;dir++) {
								int cx = x+ dx[dir];
								int cy = y + dy[dir];
								if(in_range(cx, cy)) {
									count[cy][cx]++;
								}
							}
						}
						ground[y][x].addLast(tree);
					}
				}
			}
		}
		for(int y=1;y<=N; y++){
			for(int x=1;x<=N; x++){
				if(count[y][x] !=0){
					for(int a=0; a<count[y][x];a++){
						ground[y][x].addFirst(1);
					}
				}
			}
		}
	}
	////겨울 : 땅에 양분 추가 입력되는 만큼 
	public static void winter() {
		for(int y=1; y<=N;y++) {
			for(int x=1;x<=N; x++ ) {
				food[y][x] += start[y][x];
			}
		}
	}
	
	//마지막에 살아 있는 나무 갯수
	public static void print_ans() {
		ans =0;
		for(int y=1;y<=N; y++) {
			for(int x=1;x<=N; x++) {
				if(!ground[y][x].isEmpty()) {
					ans += ground[y][x].size();
				}
			}
		}
		System.out.println(ans);
	}

}
