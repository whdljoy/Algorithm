

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
				copy_arr();
				break;
			case 2:
				second();
				copy_arr();
				break;
			case 3:
				third();
				copy_arr();
				break;
			case 4:
				fourth();
				copy_arr();
				break;	
			case 5:
				fifth();
				copy_arr();
				break;
			case 6:
				sixth();
				copy_arr();
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
		origin = new int[N+1][M+1];
		ans = new int[N+1][M+1];
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

	public static void swap(){
		int tmp =N;
		N =M;
		M =tmp;
	}

	public static void copy_arr(){
		origin = new int[N+1][M+1];
		for(int y=1; y<=N;y++){
			for(int x=1;x<=M; x++){
				origin[y][x] =ans[y][x];
			}
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
		ans = new int[M+1][N+1];
		for(int y=1; y<=N; y++) {
			for(int x=1;x<=M; x++) {
				ans[x][N+1-y] =origin[y][x];
			}
		}
		swap();
	}
	public static void fourth() {
		ans = new int[M+1][N+1];
		for(int y=1; y<=N; y++) {
			for(int x=1;x<=M; x++) {
				ans[M+1-x][y] =origin[y][x];
			}
		}
		swap();
	}	
	public static void fifth() {
		int fd_x = M/2;
		int fd_y = N/2;
		ans = new int[N+1][M+1];
		for(int y=1;y<=fd_y; y++) {  //1번을 2번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y][fd_x+x]= origin[y][x];
			}
		}
		for(int y=fd_y;y<=N; y++) { //4번을 1번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y-fd_y][x]= origin[y][x];
			}
		}
		for(int x=fd_x+1;x<=M; x++) { //2번을 3번으로
			for(int y=1;y<=fd_y; y++) {
				ans[y+fd_y][x]= origin[y][x];
			}
		}
		for(int x=fd_x+1;x<=M; x++) { //3번을 4번으로
			for(int y=fd_y+1;y<=N; y++) {
				ans[y][x-fd_x]= origin[y][x];
			}
		}
		
	}
	public static void sixth() {
		int fd_x = M/2;
		int fd_y = N/2;
		ans = new int[N+1][M+1];
		for(int y=1;y<=fd_y; y++) {  //1번을 4번으로
			for(int x=1; x<=fd_x; x++) {
				ans[y+fd_y][x]= origin[y][x];
			}
		}
		for(int y=fd_y+1;y<=N; y++) { //4번을 3번으로
			for(int x=1;x<=fd_x; x++) {
				ans[y][x+fd_x]= origin[y][x];
			}
		}
		for(int x=fd_x;x<=M; x++) { //2번을 1번으로
			for(int y=1;y<=fd_y; y++) {
				ans[y][x-fd_x]= origin[y][x];
			}
		}
		for(int x=fd_x+1; x<=M; x++) { //3번을 2번으로
			for(int y=fd_y+1; y<=N; y++) {
				ans[y-fd_y][x]= origin[y][x];
			}
		}
	}
	public static void print_ans() throws Exception{
		StringBuilder sb =new StringBuilder();
		for(int y =1;y<=N;y++) {
			for(int x=1;x<=M; x++) {
				sb.append(ans[y][x]).append(" ");

			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
