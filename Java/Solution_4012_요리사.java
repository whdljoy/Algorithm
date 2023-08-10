
import java.util.*;

import java.io.*;


public class Solution_4012_요리사 {
	
	static int [][] info;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int tc;
	static int N;
	static int [] per;
	static boolean [] check;
	static int ans;
	public static void main(String[] args) throws Exception{
		run();

	}
	public static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1; i<=tc; i++) {
			sb.append("#").append(i).append(" ");
			input();
			ans =Integer.MAX_VALUE;
			cal(0, -1);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void input() throws Exception{
		N = Integer.parseInt(br.readLine());
		info = new int [N][N];
		StringTokenizer st;
		for(int y=0;y<N;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0;x<N; x++) {
				info[y][x] =  Integer.parseInt(st.nextToken());
			}
		}
		per = new int [N];
		check = new boolean[N];
	}
	
	public static void cal(int num,int idx) {
		if(num == N/2) {
			cook();
			return ;
		}
		for(int i=idx+1; i<N; i++) {
			if(!check[i]) {
				check[i]= true;
				cal(num+1, i);
				check[i]= false;
			}
		}
	}
	
	public static void cook() {
		int [] first = new int [N/2];
		int [] second = new int [N/2];
		int idx =0;
		int s_idx =0;
		for(int i=0; i<N; i++) {
			if(check[i]) {
				first[idx++] = i;
			}else {
				second[s_idx++]=i;
			}
		}
		int a_food=0;
		int b_food=0;
		for(int y=0; y<N/2; y++) {
			for(int x=0; x<N/2; x++) {
				a_food += info[first[y]][first[x]];
			}
		}

		for(int y=0; y<N/2; y++) {
			for(int x=0; x<N/2; x++) {
				b_food += info[second[y]][second[x]];
			}
		}
		ans = Math.min(ans, Math.abs(a_food-b_food));
	}
}
