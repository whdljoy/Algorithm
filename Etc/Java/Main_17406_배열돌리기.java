
import java.util.*;
import java.io.*;

public class Main_17406_배열돌리기 {
	static int N;
	static int M;
	static int K;
	static int [][] origin;
	static List<Integer[]> info;
	static int ans = Integer.MAX_VALUE;
	static int [][] change;
	static int [][] tmp;
	static int [] arr;
 	static int [] dx = {1,0,-1,0};
	static int [] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException{

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		input(br);
		arr = new int[K];
		for(int i=0;i<K;i++) {
			arr[i] =i;
		}
		cal(arr, 0, info.size(), info.size());
		System.out.print(ans);
	}
	static void cal(int[] arr, int depth, int n, int r) {
	    if (depth == r) {
	    	copy_arr();
	    	for(int i=0; i<arr.length;i++) {
	    		change_arr(info.get(arr[i]));
	    	}
	    	get_min();
	        return;
	    }
	 
	    for (int i=depth; i<n; i++) {
	        swap(arr, depth, i);
	        cal(arr, depth + 1, n, r);
	        swap(arr, depth, i);
	    }
	}
	static void swap(int[] arr, int depth, int i) {
	    int temp = arr[depth];
	    arr[depth] = arr[i];
	    arr[i] = temp;
	}	
	public static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		origin = new int[N+1][M+1];
		info =new ArrayList<Integer[]>();
		for(int y=1; y<=N; y++) {
			st =new StringTokenizer(br.readLine());
			for(int x=1;x<=M; x++) {
				origin[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<K;i++) {
			st= new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c  = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			info.add(new Integer[]{r, c, s});
		}
		
	}
	public static void copy_arr() {
		tmp = new int[N+1][M+1];
		change = new int[N+1][M+1];
		for(int y=1;y<=N;y++) {
			for(int x=1;x<=M;x++) {
				change[y][x] = origin[y][x];
				tmp[y][x] = origin[y][x];
			}
		}		
	}
	public static void change_arr(Integer [] point){
		int dir =0;
		int sx = Math.abs(point[1]-point[2]);
		int sy= Math.abs(point[0]-point[2]);
		int ex = Math.abs(point[1]+point[2]);
		int ey =Math.abs(point[0]+point[2]);
		int end_x = (sx+ex)/2;
		int end_y = (sy+ey)/2;
		int bx = sx;
		int by = sy;
		int cx = sx;
		int cy = sy;
		while(true) {
			bx = cx;
			by = cy;
			cx =bx + dx[dir];
			cy = by + dy[dir];
			change[cy][cx] = tmp[by][bx];
			if( (cx ==sx && cy ==sy) || (cx==sx && cy==ey ) ||(cx == ex && cy ==ey) ||(cx==ex && cy==sy)){
				dir++;
			}
			if(dir ==4) {
				cx += 1;
				sx+=1;
				sy +=1;
				ey -=1;
				ex -=1;
				cy += 1;
				dir = 0;
				if(cx==end_x && cy==end_y) {
					break;
				}
			}
		}
		for(int y=1;y<=N;y++) {
			for(int x=1;x<=M;x++) {
				tmp[y][x] = change[y][x];
			}
		}	
		
	}
	public static void p_check() {
		for(int y=1;y<=N;y++) {
			for(int x=1;x<=M;x++) {
				System.out.print(change[y][x]);
			}
			System.out.println();
		}
	}
	public static void get_min() {	
		for(int y=1;y<=N;y++) {
			int sum =0;
			for(int x=1;x<=M;x++) {
				sum = sum+change[y][x];
			}
			ans = Math.min(sum,ans);
		}
	}

}
