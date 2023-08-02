
import java.util.*;
import java.io.*;
public class boj10773 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		Stack <Integer> info = new Stack<>();	
		int ans =0;
		for(int i=0; i<T;i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			if(cur != 0) {
				info.push(cur);
			}else {
				info.pop();
			}
		}
		while(!info.isEmpty()) {
			ans+=info.pop();
		}
		System.out.println(ans);
	}
}
