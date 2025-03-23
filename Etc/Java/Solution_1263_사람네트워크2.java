
import java.util.*;
import java.io.*;
public class Solution_1263_사람네트워크2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int tc;
	static int origin[][];
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		run();
	}
	
	static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1;i<=tc;i++) {
			sb.append("#").append(i).append(" ");
			input();
			floid();
			print();
		}
		System.out.println(sb);
	}
	
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		origin = new int [N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
				if(origin[y][x]==0 && y!=x) {
					origin[y][x] = 123456789;
				}
			}
		}
	}
	static void floid() {
		for(int t=0; t<N; t++) {  //  거치는좀
			for(int y=0; y<N; y++) { //  출발지
				for(int x=0; x<N; x++) {  // 도착지 
					origin[y][x] = Math.min(origin[y][x], origin[y][t]+origin[t][x]);
				}
			}
		}
	}
	
	static void print() {
		int ans=Integer.MAX_VALUE;
		for(int y=0; y<N; y++) {
			int cur=Arrays.stream(origin[y]).sum();
			ans= Math.min(ans, cur);
		}	
		sb.append(ans).append("\n");
	}
}
