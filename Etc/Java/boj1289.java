

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1289 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		int Tc = Integer.parseInt(str.nextToken());
		for(int i=0; i<Tc; i++) {
			char [] target = br.readLine().toCharArray();
			char []  cur=new char [target.length];
			for(int a=0;a<cur.length;a++) {
				cur[a]='0';
			}
			int ans=0;
			for(int x=0;x <cur.length;x++) {
				if( cur[x] != target[x]) {
					ans ++;
					for(int a=x+1; a<cur.length;a++) {
						cur[a]= target[x];
					}
				}
			}
			
			
			System.out.println("#"+(i+1)+" "+ans);
		}
	}

}
