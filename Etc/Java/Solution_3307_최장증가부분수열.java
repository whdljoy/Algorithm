
import java.util.*;
import java.io.*;
/**
 * 
 * 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
	
	각 테스트 케이스의 첫째 줄에는 수열의 길이 N(1≤N≤1,000)이 주어진다. 
	
	둘째 줄에는 수열의 원소 N개가 공백을 사이에 두고 순서대로 주어진다.
	
	각 수열의 원소는 1 이상 231-1 이하의 자연수이다.
 * @author SSAFY
 *
 */
public class Solution_3307_최장증가부분수열 {
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int tc;
	static int N;
	static int info [];
	static List <Integer> save;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	public static void run() throws Exception{
		tc = Integer.parseInt(br.readLine());
		for(int i=1; i<=tc; i++) {
			sb.append("#").append(i).append(" ");
			input();
//			logest_dp();
			binary();
		}
		System.out.println(sb);
		
	}
	
	public static void input() throws Exception{
		N = Integer.parseInt(br.readLine());
		info = new int[N];
		StringTokenizer st =  new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}
		save = new ArrayList<Integer>();
	}
	
	//dp  log(n 제곱)
	public static void logest_dp() {
		int [] dp = new int [N];
		dp[0] =1;
		for(int i=1;i< N; i++) {
			dp[i] = 1;
			for(int j=0; j<i;j++) {
				if(info[j]<info[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		sb.append( Arrays.stream(dp).max().getAsInt()).append("\n");
	}
	
	// binary search
	public static int search(int start,int last,int val) {
	    while (start < last) {
	        int mid = (start + last) / 2;
	        if (val > save.get(mid)) start = mid + 1;
	        else last = mid;
	    }
	    return last;		
	}
	public static void binary() {
		save = new ArrayList<Integer>();
		save.add(info[0]);
		for(int i=1;i<N;i++) {
			int last = save.size()-1;
			if(info[i] > save.get(last)) {
				save.add(info[i]);
			}else if (info[i]< save.get(last)) {// binary search
				int idx =search(0,save.size()-1,info[i]);
				save.set(idx, info[i]);
			}
		}
		sb.append(save.size()).append("\n");
	}

}
