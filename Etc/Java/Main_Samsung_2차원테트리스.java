
import java.util.*;


import java.io.*;

public class Main_Samsung_2차원테트리스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int K;
	static int [][] info;
	static final int MAX_K =100000;
	static int [][] blue;
	static int [][] yellow;
	static int [][] red;
	static int point =0;
	static int b_num =0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		run();
	}
	
	
	static void run() throws Exception{
		input();
		for (int i=0;i<K;i++) {	
			move_red(i);
			move_yellow(i);
			
			//print();
			get_point();
			move_down();
			//System.out.println("move");
			//print();
		}
		get_b_num();
		sb.append(point).append("\n").append(b_num);
		System.out.println(sb);
		
	}
	static void input() throws Exception{
		K = Integer.parseInt(br.readLine());
		info = new int[MAX_K][3];
		StringTokenizer st;
		blue = new int [4][4];
		yellow = new int [6][4];
		red = new int [6][4];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken()); //type
			info[i][1] = Integer.parseInt(st.nextToken()); // y 
			info[i][2] = Integer.parseInt(st.nextToken()); // x	
		}
	}
	
	static void move_red(int num) {
		//red 에서 는 반대로 
		int y = info[num][1];
		
		
		int x = 3 - info[num][1];	

		int typ = info[num][0];
		boolean isTop =false;
		if (typ == 1) {
			for(int sy=2; sy<6;sy++) {
				if(red[sy][x] ==1) {
					red[sy-1][x] = 1;
					isTop =true;
					break;
				}
			}
			if(!isTop) {
				red[5][x] =1;
			}
		}else if (typ == 3) {
			for(int sy=2; sy<6;sy++) {
				if(!(red[sy][x] ==0 && red[sy][x-1] ==0)) {
					red[sy-1][x] = 1;
					red[sy-1][x-1] = 1;
					isTop =true;
					break;
				}
			}			
			if(!isTop) {
				red[5][x] =1;
				red[5][x-1] =1;
			}		
		}else if (typ ==2) {
			for(int sy=2; sy< 6;sy ++) {
				if(red[sy][x] ==1) {
					red[sy-2][x] =1;
					red[sy-1][x]=1; 
					isTop =true;
					break;
				}
			}
			if(!isTop) {
				red[5][x] =1;
				red[4][x] =1;
			}				
		}
		
	}
	static void move_yellow(int num) {
		// yello에서는 그대로
		int y = info[num][1];
		
		
		int x = info[num][2];
		int typ = info[num][0];
		boolean isTop =false;
		if (typ ==1) {
			for(int sy=2; sy<6;sy++) {
				if(yellow[sy][x] ==1) {
					yellow[sy-1][x] = 1;
					isTop =true;
					break;
				}
			}
			if(!isTop) {
				yellow[5][x] =1;
			}				
		}else if (typ ==2) {
			for(int sy=2; sy<6;sy++) {
				if(!(yellow[sy][x] ==0 && yellow[sy][x+1] ==0)) {
					yellow[sy-1][x] = 1;
					yellow[sy-1][x+1] = 1;
					isTop =true;
					break;
				}
			}			
			if(!isTop) {
				yellow[5][x] =1;
				yellow[5][x+1] =1;
			}			
		}else if (typ ==3) {
			for(int sy=2; sy< 6;sy ++) {
				if(yellow[sy][x] ==1) {
					yellow[sy-2][x] =1;
					yellow[sy-1][x]=1; 
					isTop =true;
					break;
				}
			}
			if(!isTop) {
				yellow[5][x] =1;
				yellow[4][x] =1;
			}					
		}

	}
	static void get_point() {
		int [][] copy_yellow = new int [6][4];
		int [][] copy_red = new int [6][4];
		for(int y=0; y<6;y++) {
			for(int x=0; x<4;x++) {
				copy_red[y][x] = red[y][x];
				copy_yellow[y][x] = yellow[y][x];
			}
		}
		
		for(int y=0;y<6;y++) {
			if( yellow[y][0] ==1  && yellow[y][1] ==1 &&  yellow[y][2] ==1 && yellow[y][3] ==1  ) {
				point+=1;
				for(int sy=y-1;sy>-1;sy--) {
					for(int x=0; x<4;x++) {
						yellow[sy+1][x] = yellow[sy][x];
					}
				}
				for(int x=0; x<4;x++) {
					yellow[0][x] = 0;
				}				
			}
			if( red[y][0] ==1  && red[y][1] ==1 &&  red[y][2] ==1 && red[y][3] ==1  ) {
				point+=1;
				for(int sy=y-1; sy>-1 ;sy--) {
					for(int x=0; x<4;x++) {
						red[sy+1][x] = red[sy][x];
					}					
				}
				for(int x=0; x<4;x++) {
					red[0][x] = 0;
				}					
			}					
		}
		
		
	}
	static void move_down() {
		int y_cnt=0;
		int r_cnt=0;
		for(int y=1; y>-1;y--) {
			if( red[y][0] ==0  && red[y][1] ==0 &&  red[y][2] ==0 && red[y][3] ==0) continue;
			r_cnt+=1;
		}
		for(int y=1; y>-1;y--) {
			if( yellow[y][0] ==0  && yellow[y][1] ==0 &&  yellow[y][2] ==0 && yellow[y][3] ==0) continue;
			y_cnt+=1;
		}	
		for(int y=5;y>1;y--) {
			for(int x=0; x<4;x++) {
				red[y][x] = red[y-r_cnt][x];
				yellow[y][x] =yellow[y-y_cnt][x];
			}	
		}
		
		for(int y=1;y>-1;y--) {
			for(int x=0; x<4;x++) {
				red[y][x] = 0;
				yellow[y][x] =0;
			}	
		}		
	}
	
	static void get_b_num() {
		for(int y=5;y>1;y--) {
			for(int x=0;x<4;x++) {
				if(yellow[y][x] ==  1) {
					b_num +=1;
				}
				
				if(red[y][x]==1) {
					b_num +=1;
				}				
			}
		}
	}
	
	static void print() {
		System.out.println("red");
		for(int y=0; y<6;y++) {
			for(int x=0; x<4;x++) {
				System.out.print(red[y][x]+ " ");
			}
			System.out.println();
		}
		
		System.out.println("yellow");
		for(int y=0; y<6;y++) {
			for(int x=0; x<4;x++) {
				System.out.print(yellow[y][x]+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
