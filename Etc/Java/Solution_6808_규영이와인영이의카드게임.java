

import java.io.*;
import java.util.*;


public class Solution_6808_규영이와인영이의카드게임 {
	
	public static int T;
	public static int [] origin;
	public static int [] another;
	public static boolean [] check;
	public static int [] val;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int winner;
	static int lose;
	public static void main(String[] args) throws Exception{
		
		run();
		
	}
	
	static void run() throws Exception{
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			sb.append("#").append(i+1).append(" ");
			input();
			cal(0);
			sb.append(winner).append(" ").append(lose).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void input() throws Exception{
		winner =0;
		lose =0;
		origin = new int [9];
		another = new int[9];
		check = new boolean [9];
		val = new int[9];
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean []cur = new boolean[19];
		for(int i=0;i<9;i++) {
			origin[i]=Integer.parseInt(st.nextToken());
			cur[origin[i]] =true;
		}
		int idx =0;
		for(int j=1;j<19;j++) {
			if(!cur[j]) {
				val[idx++] = j;
			}
		}
		
	}
	
	static void cal(int num) {
		if (num == 9) {
			cal_ans();
			return;
		}
		for(int i=0; i<9;i++) {
			if(!check[i]) {
				check[i]= true;
				another[num] = val[i];
				cal(num+1);
				check[i]=false;
			}
		}
		
		
	}
	
	static void cal_ans() {
		int cur_gu=0;
		int cur_in=0;
		for(int i=0;i<9;i++) {
			int gu = origin[i];
			int in = another[i];
			if(gu > in) {
				cur_gu = cur_gu +gu+in;
			}else {
				cur_in = cur_in + gu+in;
			}
		}
		
		
		if(cur_gu >cur_in)	winner++;
		else  lose++;
	
	}
	
}
