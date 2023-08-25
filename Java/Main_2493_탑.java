
import java.util.*;
import java.io.*;


public class Main_2493_탑 {
	static List <Integer> ans;
	static List <tp> check;
	static class tp{
		int idx;
		int num;
		tp(int idx,int num){
			this.idx = idx;
			this.num =num;
		}
	}
	static int N;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	
		input(br);
		cal();
		print_ans();
	}
	
	
	public static void input(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = new ArrayList<Integer>();
		check = new ArrayList<tp>();
		for(int i=0; i<N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			check.add(new tp(i,cur));
		}
			
	}
	
	public static void cal() {
		for(int i=0; i<N; i++) {
			if(ans.isEmpty()) {
				ans.add(-1);
			}else {
				int idx = i-1;
				tp change = check.get(idx);
				tp cur = check.get(i);
				while(true) {
					if(change.num > cur.num) {
						ans.add(change.idx);
						break;
					}else {
						if(ans.get(change.idx)==-1) {
							ans.add(-1);
							break;
						}						
						change = check.get(ans.get(change.idx));
					}
				}
				
			}
		}

	}
	public static void print_ans() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<N;i++) {
			sb.append(ans.get(i)+1);
		
			if(i != N-1) {
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}
}
