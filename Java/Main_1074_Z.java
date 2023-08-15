

import java.util.*;
import java.io.*;

public class Main_1074_Z {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int r;
	static int c;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		input();
		int cur = (int) Math.pow(2, N);
		dfs(0, N - 1, cur, cur);//

	}

	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		r += 1;
		c = Integer.parseInt(st.nextToken());
		c += 1;
	}

	static void dfs(int start, int num, int x, int y) {
		int  current = (int) Math.pow(2, num);
		int pow_c = current * current;
		if ( num==0) {
			if ((c <= x -current) && (r <= y -current)){
				System.out.println(start);
			}
			else if ((c > x -current) && (r <= y -current)) { // 2사분면
				System.out.println(start+1);
			}
			else if ((c <= x -current) && (r > y -current)) { // 3사분면
				System.out.println(start+2);
			}
			else if ((c > x -current) && (r > y -current)) { // 4사분면
				System.out.println(start+3);
			}
			return;
		}
		if ((c <= x -current) &&  (r <= y -current)) { // 1사분면
			dfs(start, num - 1, x -current, y -current);
		}
		else if ((c > x -current) && (r <= y -current)) { // 2사분면
			dfs(start + pow_c , num - 1, x, y -current);
		}
		else if ((c <= x -current) && (r > y -current)) { // 3사분면
			dfs(start + pow_c  * 2, num - 1, x -current, y);
		}
		else if ((c > x -current) && (r > y -current)) { // 4사분면
			dfs(start + pow_c  * 3, num - 1, x, y);
		}

	}
}
