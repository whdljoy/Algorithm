
import java.util.*;
import java.io.*;

public class Main_16435_스네이크버드 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int L;
	static int [] height;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	public static void input() throws Exception{
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		height = new int[N];
		st =new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	
	public static void cal() {
		Arrays.sort(height);
		for(int i=0; i<N; i++) {
			if(height[i] <=L) {
				L++;
			}
		}
	}
	
	public static void print() throws Exception{
		StringBuilder sb =new StringBuilder();
		sb.append(L);
		bw.write(sb.toString());
		bw.close();
	}
	public static void run() throws Exception{
		input();
		cal();
		print();
		
	}
}
