

import java.io.*;
import java.util.*;

public class Main_9205_맥주마시면서걸어가지 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int tc;
	static int n;
	static int info [][];
	static boolean dis [][];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1;i<=tc; i++) {
			input();
			floid();
		}
		System.out.println(sb);
	}
	
	static void input() throws Exception{
		n = Integer.parseInt(br.readLine());
		info = new int [n+2][2];
		dis = new boolean [n+2][n+2];
		for(int i=0;i<n+2;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0;x<2;x++) {
				info[i][x]= Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	static void floid() {
		for(int y=0;y<n+2; y++) {  //시작점 
			for(int x=0;x<n+2; x++) { // 출발지
				int cur =  Math.abs(info[y][0] -info[x][0]) + Math.abs(info[y][1] - info[x][1]);
				if(cur <= 1000) {
					dis[y][x] = true;
				}
			}
		}

		
		for(int y=0;y<n+2; y++) { // 경유지
			for(int x=0;x<n+2; x++) {  //시작점 
				for(int t=0; t<n+2; t++) { // 도착점
					if((dis[x][y] && dis[y][t])) {
						dis[x][t] =true;
					}
				}
			}
		}
	
		if(dis[0][n+1]) {
			sb.append("happy");
		}else {
			sb.append("sad");
		}
		sb.append("\n");
	
	}
}
