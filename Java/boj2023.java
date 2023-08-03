
import java.util.*;
import java.io.*;


public class boj2023 {
	static ArrayList<Integer > ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ans = new ArrayList<>();
		
		
		dfs(0, N);
		for(int answer : ans) {
			System.out.println(answer);
		}
		
	}
	static boolean isPrime(int num) {
		if(num < 2) return false;
		for(int i=2 ; i*i<=num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	static void dfs(int start, int N) {
		if(N == 0) {
			ans.add(start);
			return;
		}
		for(int i=1; i<10; i++) {
			int cur_n = 10 * start +i;
			if(isPrime(cur_n)) {
				dfs(cur_n, N-1);	
			} 
		}
	}	
}
