
import java.util.*;

import java.io.*;


public class Main_Samsung_격자숫자놀이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static final int MAX_N =100;
	static int origin[][];
	static int R,C,K;
	static int Row,Column;
	static int ans=0;
	static class Vtx implements Comparable<Vtx>{
		int data;
		int cnt;
		Vtx(int data,int cnt){
			this.data = data;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Vtx cur) {
			if(cur.cnt ==this.cnt) {
				if (cur.data > this.data) {
					return -1;
				}
				else {
					return 1;
				}
			}
			return Integer.compare(this.cnt, cur.cnt);
		}
	}
	static PriorityQueue<Vtx> q;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	
	}
	
	static void input() throws Exception{
		origin = new int [MAX_N][MAX_N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		R= Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int y=0;y<3;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0;x<3;x++) {
				origin[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		q = new PriorityQueue<>();
		Row =3;
		Column=3;
	}
	static void run() throws Exception{
		input();
		simulate();
		System.out.println(ans);
	}
	
	static void simulate() {
		int time =0;
		while(true) {
			if(origin[R-1][C-1] == K) {
				ans =time;
				break;
			}
			if(time >100) {
				ans =-1;
				break;
			}
			if(Row >= Column) {
				rowcal();
			}else {
				colcal();
			}
			time+=1;
		}
	}
	
	static void rowcal() {
		int  tmp [][] = new int [MAX_N][MAX_N];
		for(int y=0; y<Row; y++) {
			q.clear();
			int info[] = new int [101];  //각 숫자별 갯수
			for(int i=0;i<100; i++) {
				if(origin[y][i] !=0) {
					info[origin[y][i]] +=1;
				}
			}
			for(int i=1;i<=100;i++) {
				if(info[i]!=0) {
					q.add(new Vtx(i,info[i]));
				}
			}
			int x=0;
			while(!q.isEmpty()) {
				if(x==MAX_N) {
					break;
				}
				Vtx cur = q.poll();
				tmp[y][x++] = cur.data;
				tmp[y][x++] = cur.cnt;
				Column = Math.max(Column, x);
			}
		}
		for(int y=0;y<100;y++) {
			for(int x=0; x<100;x++) {
				origin[y][x] = tmp[y][x];
			}
		}
	}
	
	static void colcal() {
		int  tmp [][] = new int [MAX_N][MAX_N];
		for(int x=0; x<Column; x++) {
			q.clear();
			int info[] = new int [101];  //각 숫자별 갯수
			for(int i=0;i<100; i++) {
				if(origin[i][x] !=0) {
					info[origin[i][x]] +=1;
				}
			}
			for(int i=1;i<=100;i++) {
				if(info[i]!=0) {
					q.add(new Vtx(i,info[i]));
				}
			}
			int y=0;
			while(!q.isEmpty()) {
				if(y==MAX_N) {
					break;
				}
				Vtx cur = q.poll();
				tmp[y++][x] = cur.data;
				tmp[y++][x] = cur.cnt;
				Row = Math.max(Row, y);
			}
		}
		for(int y=0;y<100;y++) {
			for(int x=0; x<100;x++) {
				origin[y][x] = tmp[y][x];
			}
		}		
	}
	static void print() {
		for(int y=0;y<Row;y++) {
			for(int x=0; x<Column;x++) {
				sb.append(origin[y][x]).append(" ");
			}
			sb.append("\n");
		}
		sb.append("\n");
	}

}
