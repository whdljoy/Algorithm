
import java.util.*;
import java.io.*;

public class boj9012 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			Stack<Character> info = new Stack<>();
			String cur = br.readLine();
			char[] cur_char = cur.toCharArray();
			for (int x = 0; x < cur_char.length; x++) {
				if (cur_char[x] == '(') {
					info.push('(');
				} else {
					if (!info.isEmpty() && info.peek() == '(') {
						info.pop();
					} else {
						info.push(')');
					}
				}
			}
			if (info.isEmpty()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}
}
