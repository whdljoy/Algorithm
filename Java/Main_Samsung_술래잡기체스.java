
import java.util.*;

import java.io.*;
public class Main_Samsung_술래잡기체스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static class Thief{
		int dir;
		int x;
		int y;
		boolean isCatch;
		Thief(int y,int x, int dir,boolean isCatch){
			this.x =x;
			this.y=y;
			this.dir=dir;
			this.isCatch =isCatch;
		}
	}
	static class Target{
		int x;
		int y;
		int point;
		int dir;
		Target(int y,int x, int point,int dir){
			this.x=x;
			this.y=y;
			this.point=point;
			this.dir = dir;
		}
	}
	
	static class Cell{
		int num;
		int dir;
		Cell(int num,int dir){
			this.num = num;
			this.dir=dir;
		}
	}
	static int ans=0;
	static Thief [] info;
	static Cell [][] origin;
	static Target cat;
	static int [] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int [] dy = {0,-1,-1,0,1,1,1,0,-1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
		System.out.println(ans);
	}
	
	static void run()throws Exception{
		input();
		dfs(cat,origin,info );
	}
	static void input() throws Exception{
		info=new Thief[17];
		origin = new Cell [4][4];
		for(int y=0;y<4;y++) {
			for(int x=0;x<4;x++) {
				origin[y][x] =new Cell(0, 0);
			}
		}
		for(int y=0;y<= 16;y++) {
			info[y] = new Thief(-1,-1,0,false);
		}
		for(int y=0;y<4;y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<4 ;x++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				info[num] = new Thief(y,x,dir,false);
				origin[y][x].num =num;
				origin[y][x].dir= dir;
			}
		}
		
		//첫번째 0,0 잡기
		cat= new Target(0, 0, origin[0][0].num, origin[0][0].dir);
		info[origin[0][0].num].isCatch=true;
		origin[0][0].num =0;
		origin[0][0].dir =0;
	}
	
	static void dfs(Target cur, Cell [][] status,Thief[] current) {
		move(cur, status, current);  //  도둑 이동
		udt(cur, status, current);  // 이동을 토대로 도둑 정보 업데이트
		//print_move(status);
		move_target(cur,status,current);
	}
	
	static void move (Target cur, Cell [][] status,Thief[] current){	
//		System.out.println(cur.x + " "+cur.y +cur.dir);
		for(int i=1;i<=16;i++) {
			if(!current[i].isCatch) {
				//System.out.println(i +" "+current[i].x +" "+current[i].y);
				for(int dir=0;dir<8; dir++) {
					int direction = current[i].dir +dir;
					if(direction >8) {
						direction = direction %8;
					}
					int cx = current[i].x + dx[direction];
					int	cy = current[i].y + dy[direction];				
					if(in_range(cy, cx) && !(cy == cur.y && cx == cur.x)) {
						int num = status[current[i].y][current[i].x].num;
						int dis = direction;
						status[current[i].y][current[i].x].num =status[cy][cx].num;
						status[current[i].y][current[i].x].dir =status[cy][cx].dir;
						current[status[cy][cx].num] =new Thief(current[i].y , current[i].x, status[cy][cx].dir, current[status[cy][cx].num].isCatch);
						status [cy][cx].num = num;
						status [cy][cx].dir = dis;
						
						current[num]=new Thief(cy,cx, direction, false);
						
						//print_move(status);
						break;
					}
				}
			}
		}
					
	}
	
	static void udt (Target cur, Cell [][] status, Thief[] current) {
		Thief [] tmp = new Thief[17];
		for(int y=0;y<=16;y++) {
			tmp[y] = new Thief(-1,-1, 0,true);
		}
		for(int y=0;y<4;y++) {
			for(int x=0;x<4;x++) {
				if(status[y][x].num !=0) {
					int num = status[y][x].num;
					int dir = status[y][x].dir;
					tmp[num].x=x;
					tmp[num].y= y;
					tmp[num].dir =dir;
					tmp[num].isCatch =false;
				}
			}
		}
		
		for(int y=0;y<=16;y++) {
			current[y]=tmp[y];
		}		
		
	}
	static boolean in_range(int y,int x) {
		return 0<=x && x<4 && 0<=y && y<4;
	}
	static void print_move(Cell [][] cur) {
		for(int y=0; y<4;y++) {
			for(int x=0;x<4;x++) {
				System.out.print(cur[y][x].num +" dir " +cur[y][x].dir +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void move_target (Target cur, Cell [][] status, Thief[] current) {
		boolean isThief = false;
		for(int i=1;i<=3;i++) {
			int cx = cur.x + dx[cur.dir] *i;
			int cy = cur.y +dy[cur.dir]*i;
			if(in_range(cy, cx) && status[cy][cx].num !=0) {
				isThief =true;
				Cell [][]tmp= new Cell [4][4];
				for(int y=0;y<4;y++) {
					for(int x=0;x<4;x++) {
						tmp[y][x] =new Cell(status[y][x].num, status[y][x].dir);
					}
				}	
				
				Thief [] tmp_t = new Thief[17];
				for(int y=0;y<=16;y++) {
					tmp_t[y] = new Thief(current[y].y,current[y].x, current[y].dir,current[y].isCatch);
				}	
				Target tar= new Target(cy, cx, cur.point+tmp[cy][cx].num, tmp[cy][cx].dir);
				tmp_t[tmp[cy][cx].num].isCatch =true;
				tmp[cy][cx].num =0;
				dfs(tar, tmp, tmp_t);
			}
		}
		if(!isThief) {
			ans = Math.max(ans, cur.point);
		}
			
	}
}
