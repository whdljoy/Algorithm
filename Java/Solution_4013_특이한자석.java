import java.util.*;
import java.io.*;
public class Solution_4013_특이한자석 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static StringBuilder sb = new StringBuilder();
	static int tc;
	static int [][] info;
	static int k;
	static int [][] status;
	static int ans =0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1;i <=tc;i++) {
			sb.append("#").append(i).append(" ");
			input();
			simulate();
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		
	}
	static void input() throws Exception{
		k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		info = new int [k][2];
		status = new int [5][9];
		ans=0;
		for(int y=1;y<=4;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1;x<=8;x++) {
				status[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<k;i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] =Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void simulate() {
		for(int i=0;i<k;i++) {
			rotate(i);
		}
		get_point();
	}
	static void rotate (int num) {
		int info_num = info[num][0];
		int direction = info[num][1];
		int [] rt_info = new int[5];
		rt_info[info_num] = direction;
		for(int i=info_num-1;i>0;i--) {
			if(status[i][3] !=status[i+1][7]) {
				rt_info[i] = -rt_info[i+1];
			}
			else {
				break;
			}
		}	
		for(int i=info_num+1;i<=4;i++) {
			if(status[i-1][3] !=status[i][7]) {
				rt_info[i] = -rt_info[i-1];
			}
			else {
				break;
			}
		}		
		change(rt_info);
	}
	static void change(int [] rt_info) {
		for(int i=1;i<=4;i++) {
			if(rt_info[i] ==1) {
				status[i][0] = status[i][8];
				for(int x=8; x>0;x--) {
					status[i][x] = status[i][x-1];
				}
				
			}
			else if(rt_info[i] == -1) {
				for(int x=1;x<=8;x++) {
					status[i][x-1] =status[i][x];
				}
				status[i][8] = status[i][0];
			}
		}
	}
	static void print() {
		for(int i=1;i<=4;i++) {
			for(int x=1;x<=8;x++) {
				System.out.print(status[i][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void get_point() {
		if(status[1][1] == 1) {
			ans +=1;
		}
		if(status[2][1] == 1) {
			ans +=2;
		}
		if(status[3][1] == 1) {
			ans +=4;
		}
		if(status[4][1] == 1) {
			ans +=8;
		}
	}
}
