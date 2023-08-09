

import java.util.*;
import java.io.*;


public class Solution_5215_햄버거다이어트 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int tc;
	static int [][] food; //재료 정보 배열
	static int num_f; // 재료 갯수
	static int calories; // 제한 칼로리
	static boolean [] check;
	static int ans=0;
	public static void main(String[] args)throws Exception {
		tc = Integer.parseInt(br.readLine());
		run();

	}
	
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		num_f = Integer.parseInt(st.nextToken());
		calories = Integer.parseInt(st.nextToken());
		food = new int[num_f][2];
		for(int i=0; i<num_f; i++) {
			st = new StringTokenizer(br.readLine());
			food [i][0] = Integer.parseInt(st.nextToken());
			food [i][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void run() throws Exception{
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<tc; j++) {
			sb.append("#").append(j+1).append(" ");
			ans =0;
			input();
			for(int i=1; i<=num_f;i++) {
				check= new boolean[num_f];
				cal(i,0, 0);
			}
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	static void cal(int num,int food_c,int sum) {
		if(num==0) {
			if(food_c<=calories) {
				ans = Math.max(ans, sum);
			}
			return;
		}
		for(int i=0;i<num_f;i++) {
			if(!check[i]) {
				check[i]= true;
				sum += food[i][0];
				food_c += food[i][1];
				cal(num--, food_c,sum);
				check[i] = false;
				sum -= food[i][0];
				food_c -= food[i][1];
			}
		}
	}

}
