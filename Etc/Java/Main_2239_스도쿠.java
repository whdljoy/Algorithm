



import java.util.*;
import java.io.*;

public class Main_2239_스도쿠 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static boolean row [][];
	static boolean col [][];
	static boolean area [][];
	static int origin[][];
	static boolean flag = false;
	public static void main(String[] args)throws Exception {
		run();
	}
	
	static void run() throws Exception{
		input();
		dfs(1, 1);
		
	}
	
	static void init() {
		row = new boolean[10][10];
		col = new boolean[10][10];
		area = new boolean[10][10];
		origin = new int[10][10];
	}
	static void input() throws Exception{
		init();
		for(int y=1;y<=9;y++) {
			String line = br.readLine();
			for(int x=0;x<9;x++) {
				int num = line.charAt(x)-'0';
				origin[y][x+1] = num;
				row[y][num] = true;
				col[x+1][num] = true; 
				area[(y-1)/3*3 +x/3+1][num] =true;
			}
		}
	}
	
	static void dfs(int x,int y) {
		if(x ==1 && y==10) {
			if(!flag) {
				flag = true;
				for(int c=1;c<=9;c++) {
					for(int r=1;r<=9;r++) {
						System.out.print(origin[c][r]);
					}
					System.out.println();
				}
			}
			return;
		}
		if(!flag) {
			if(origin[y][x] == 0 ) {
				for(int i=1;i<=9;i++) {
					int n_area = (y-1)/3*3 +(x-1)/3+1;
					if(!row[y][i] && !col[x][i] && !area[n_area][i]) {
						row[y][i]= true;
						col[x][i] = true;
						area[n_area][i] = true;
						origin[y][x] = i;
						if(x<9) {
							dfs(x+1, y);
						}else {
							dfs(1, y+1);
						}
						row[y][i]= false;
						col[x][i] = false;
						area[n_area][i] = false;
						origin[y][x] = 0;				
						
					}
				}	
			}else {
				if(x<9) {
					dfs(x+1, y);
				}else {
					dfs(1, y+1);
				}			
			}			
		}
	}
	
}
