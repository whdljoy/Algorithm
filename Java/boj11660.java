package com.ssafy.bj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 *
 * 문제 :
 * N×N개의 수가 N×N 크기의 표에 채워져 있다. 
 * (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. 
 * (x, y)는 x행 y열을 의미한다.
 *
 *
 */
public class boj11659 {
	static int N=0;
	static int M=0;
	static int [][] num;
	public static void main(String[] args) throws IOException {
		// 입력
		//
		//	첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. 
		//	(1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 
		//	둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 
		//	다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, 
		//	(x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 
		//	표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. 
		//	(x1 ≤ x2, y1 ≤ y2)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		num = new int[N+1][N+1];
		for(int y=0;y<=N;y++) {
			num[0][y]=0;
			num[y][0]=0;
			
		}
		
		// 입력받으면서 x축으로 누적합 
		for(int y=1;y<=N; y++) {
			str = new StringTokenizer(br.readLine());
			for(int x=1;x<=N;x++) {
				num[y][x] = num[y][x-1] + Integer.parseInt(str.nextToken());
			}
		}
		// 누적된 것으로 y축으로 누적합
		for(int x=1;x<=N; x++) {
			for(int y=1;y<=N;y++) {
				num[y][x] = num[y-1][x] +num[y][x];
			}		
		}
		

		for(int i=0; i<M; i++) {
			str = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(str.nextToken()); //행 
			int y1 = Integer.parseInt(str.nextToken()); //열 
			int x2 = Integer.parseInt(str.nextToken());//행  
			int y2 = Integer.parseInt(str.nextToken());//열 
			int ans =0;
			ans = (num[x2][y2]-num[x1-1][y2]) - (num[x2][y1-1]-num[x1-1][y1-1]);
			System.out.println(ans);
		}
	}

}