
import java.util.*;
import java.io.*;

public class Main_16929_배열돌리기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int M;
	static int N;
	static int R;
	static int [][] origin;
	static int [][] tmp;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();

	}
	public static void run() throws Exception{
		input();
		for(int i=0;i<R;i++) {
			rotate();
		}
		print_a();
	}
	public static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		origin = new int[N+1][M+1];
		tmp  = new int[N+1][M+1];
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1;x<=M; x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
			}
		}		
		br.close();
	}
	
	public static void rotate() {
		int [] check = {N-1,M-1,N-1,M-1};
		int num = Math.min(N, M);
		int sx = 0;
		int sy =0;
		int cx =sx;
		int cy= sy;
		for(int i=0; i<num/2; i++) {
			sx+=1;
			sy+=1;
			for(int dir=0;dir<4;dir++) {
				for(int hw =0; hw<check[dir];hw++) {
					cx = sx+dx[dir];
					cy = sy+ dy[dir];
					tmp[cy][cx] = origin[sy][sx];
					sx = cx;
					sy = cy;
				}
				
			}
			for(int idx =0; idx<4;idx++) {
				check[idx] -=2;
			}	
		}
		for(int y=1; y<=N; y++) {
			for(int x=1;x<=M; x++) {
				origin[y][x] = tmp[y][x];
			}
		}
	}
	public static void print_a() throws Exception{
		StringBuilder sb =new StringBuilder();
		for(int y=1; y<=N; y++) {
			for(int x=1;x<=M; x++) {
				sb.append(origin[y][x]).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	
	}
}

