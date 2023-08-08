

import java.util.*;
import java.io.*;


public class Main_16935_배열돌리기 {
	
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int M;
	static int R;
	static int []cal;
	static int [][] origin;
	static int [][] ans;
	static int arr_max;
	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	public static void run() throws Exception{
		input();
		for(int i=0;i<R; i++) {
			switch (cal[i]) {
			case 1:
				first();
				break;
			case 2:
				second();
				break;
			case 3:
				third();
				break;
			case 4:
				fourth();
				break;	
			case 5:
				fifth();
				break;
			case 6:
				sixth();
				break;				
			}
		}
		print_ans();
	}
	public static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr_max = Math.max(N, M);
		origin = new int[arr_max+1][arr_max+1];
		ans = new int[arr_max+1][arr_max+1];
		for(int y=1; y<=N; y++) {
			st =new StringTokenizer(br.readLine());
			for(int x=1; x<=M;x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		cal = new int[R];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void first() {
		for(int x =1;x<=M;x++) {
			for(int y=1;y<=N; y++) {
				ans[N+1-y][x] =origin[y][x];
			}
		}
	}
	
	public static void second() {
		for(int y =1;y<=N;y++) {
			for(int x=1;x<=M; x++) {
				ans[y][M+1-x] =origin[y][x];
			}
		}
	}
	
	public static void third() {
		for(int y=1; y<=arr_max; y++) {
			for(int x=1;x<=arr_max; x++) {
				ans[x][arr_max+1-y] =origin[y][x];
			}
		}
	}
	public static void fourth() {
		for(int y=1; y<=arr_max; y++) {
			for(int x=1;x<=arr_max; x++) {
				ans[arr_max+1-x][y] =origin[y][x];
			}
		}
	}	
	public static void fifth() {
		int fd_x = M/2;
		int fd_y = N/2;
		for(int y=1;y<=fd_y; y++) {  //1번을 2번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y][fd_x+x]= origin[y][x];
			}
		}
		for(int y=fd_y;y<=arr_max; y++) { //4번을 1번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y-fd_y][x]= origin[y][x];
			}
		}
		for(int x=fd_x;x<=M; x++) { //2번을 3번으로
			for(int y=1;y<=fd_y; y++) {
				ans[y+fd_y][x]= origin[y][x];
			}
		}		
		for(int x=fd_x;x<=M; x++) { //3번을 4번으로
			for(int y=fd_y;y<=N; y++) {
				ans[y][x-fd_x]= origin[y][x];
			}
		}			
		
	}
	public static void sixth() {
		int fd_x = M/2;
		int fd_y = N/2;
		
		
		for(int y=1;y<=fd_y; y++) {  //1번을 4번으로
			for(int x=1; x<=fd_x; x++) {
				ans[y+fd_y][x]= origin[y][x];
			}
		}
		for(int y=fd_y;y<=N; y++) { //4번을 3번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y][x+fd_x]= origin[y][x];
			}
		}
		for(int x=fd_x;x<=M; x++) { //2번을 1번으로
			for(int y=1;y<=fd_y; y++) {
				ans[y][x-fd_x]= origin[y][x];
			}
		}			
		for(int x=fd_x; x<=M; x++) { //3번을 2번으로
			for(int y=fd_y; y<=N; y++) {
				ans[y-fd_y][x]= origin[y][x];
			}
		}	
	}
	public static void print_ans() throws Exception{
		StringBuilder sbb =new StringBuilder();
		for(int y=1;y<=arr_max;y++) {
			for(int x=1;x<=arr_max; x++) {
				sbb.append(origin[y][x]).append(" ");
			}
			sbb.append("\n");
		}
		bw.write(sbb.toString());
		StringBuilder sb =new StringBuilder();
		for(int y =1;y<=arr_max;y++) {
			for(int x=1;x<=arr_max; x++) {
				//if(ans[y][x]!=0) {
					sb.append(ans[y][x]).append(" ");
				//}
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
