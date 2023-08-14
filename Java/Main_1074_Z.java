package com.ssafy.bj;

import java.util.*;
import java.io.*;


public class Main_1074_Z {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int r;
	static int cur;
	static int c;
	static int [][] origin;
	static int [] dx = {0,1,0,1};
	static int [] dy = {0,0,1,1};
 	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
 		input();
 		int cur = (int)Math.pow(2, N);
 		dfs(0,N,cur,cur);// N=3 
		
		
	}
	
	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r  = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		origin = new int[N+1][N+1];
	}
	
	static void dfs(int start,int num,int x,int y) {
		if((r+1) ==y && (c+1) ==x ) {
			System.out.print(start);
		}
		int current = (int) Math.pow(2, num);
		current = current*current;
		if( start<=N  && N <current*1) { //1사분면
			dfs(start, num-1,x/2,y/2);
		}
		if (current*1 <=start&& start<current*2 ){ //2사분면
			dfs(start+current*1, num-1,x,y/2);
		}
		if (current*2 <=start && start<current*3 ){ //3사분면
			dfs(start+current*2 , num-1,x/2,y);	
		}
		if (current*3 <=start && start<current*4 ){ //4사분면
			dfs(start+current*3, num-1,x,y);
		}
			
		
	}
}
