
import java.util.*;
import java.io.*;

public class Main_Samsung_불안한무빙워크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb =new StringBuilder();
	static int N,K;
	static int belt[];
	static boolean visited [];
	static int cnt =0;
	static int time =0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
	}
	
	static void run() throws Exception{
		input();
		simulate();
		System.out.println(time);
	}
	static void input() throws Exception{
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int [N*2+1];
		visited = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N*2;i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	static void simulate() {
		while(true) {
			time +=1;
			rotate();
			person();
			onBelt();
			if(cnt  >=K) {
				break;
			}
		}
	}
	
	static void rotate() {
		belt[0] = belt[N*2];
		for(int i=N*2;i>0;i--) {
			belt[i] =belt[i-1];
		}
		visited[0] = visited[N];
		for(int i=N;i>0;i--) {
			visited[i] =visited[i-1];
		}		
		if(visited[N]) {
			visited[N]=false;
		}
	}
	
	static void person() {
		for(int idx =N-1; idx>0;idx--) {
			if(visited[idx]) {
				if(visited[idx+1] || belt[idx+1] ==0) continue;
				
				visited[idx+1]=true;
				visited[idx] = false;
				belt[idx+1] -=1;
				if(belt[idx+1]==0) {
					cnt+=1;
				}
			}
		}
		visited[N]=false;
	}
	static void onBelt() {
		if(!visited[1]  && belt[1] !=0) {
			visited[1] = true;
			belt[1] -=1;
			if(belt[1] == 0) {
				cnt+=1;
			}
		}
	}
}
