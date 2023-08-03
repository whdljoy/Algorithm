
import java.io.*;
import java.util.*;

public class boj2961{
	static int N;
	static int[][] taste;
	static boolean[] selected;
	static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		taste = new int[N][2];
		selected = new boolean[N];


		for(int i =0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		cal(0);
		System.out.println(min);
	}

	public static void cal(int cnt) {
		int sour = 1;
		int bitter = 0;
		if(cnt == N) {
			for(int i =0 ; i < N ; i++) {
				if(selected[i]) {
					sour *= taste[i][0];
					bitter += taste[i][1];
				}
			}
			if( bitter!=0 && sour!= 1 ) {
				min = Math.min(min, Math.abs(sour-bitter));
			}
			return;
		}
		selected[cnt] = true;
		cal(cnt+1);
		selected[cnt] = false;
		cal(cnt+1);
	}
}