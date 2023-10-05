
import java.util.*;
import java.io.*;

public class Solution_5658_보물상자비밀번호 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int tc;
	static char origin[];
	static int N,K;
	static int sz;
	static Set <Integer> ans;
	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	public static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1;i<=tc; i++) {
			sb.append("#").append(i).append( " ");
			input();
			simulate();
			
		}
		System.out.println(sb);
	}
	public static void input() throws Exception{
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String ip = br.readLine();
		origin = new char[N];
		for(int i=0; i<N;i++) {
			origin[i] = ip.charAt(i);
		}
		ans = new TreeSet<>();
		sz = N /4;
	}
	
	public static void simulate() {
		for(int rp=0; rp<sz; rp++) {
			cut_size();
			move_origin();
			
		}
		check_ans();
	}
	public static void move_origin() {
		char tmp = origin[N-1];
		for(int i=N-1; i>0; i--) {
			origin[i]= origin[i-1];
		}
		origin[0]=tmp;
	}
	public static void cut_size() {
		for(int i=0; i<N;i=i+sz) {
			changeTo(i);
		}
	}
	public static void changeTo(int start) {
		String cur ="";
		for(int i = 0; i<sz; i++) {
			cur+= origin[start+i];
		}
		ans.add(-Integer.parseInt(cur,16));
	}
	public static void check_ans() {
		Object [] cur = ans.toArray();
		Integer current = (Integer) cur[K-1] *-1;
		sb.append(current).append("\n");
	}

}
