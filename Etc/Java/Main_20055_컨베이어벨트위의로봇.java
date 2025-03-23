
import java.util.*;
import java.io.*;

public class Main_20055_컨베이어벨트위의로봇 {
    static int N;
    static int K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [] data;
    static Robot [] info;
    static int k_num;
    static int turn;
    static class Robot {
    	boolean check;
    	int position;
    	Robot(boolean check){
    		this.check = check;
    	}
    }
    public static void main(String[] args) throws Exception{

    	run();
    }
    static void run() throws Exception{
    	input();
    	while(k_num<K) {
        	move_belt();
        	move_robot();
        	add_robot();
        	turn+=1;
    	}
    	System.out.println(turn);
    	
    	
/**
 * 	
 * 	벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
	가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
	로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
	올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
	내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 */
    }
    static void input() throws Exception{
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        data =new int[2*N+1];
        info = new Robot[N+1];
        for(int i=0;i<=N; i++) {
        	info[i] = new Robot(false);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=2*N; i++) {
        	data[i] =Integer.parseInt(st.nextToken());
        }
        turn=0;
        k_num=0;
    }
    static void move_belt() {
    	data[0]=data[2*N];
    	for(int i=2*N; i> 0; i-- ) {
    		data[i] = data[i-1]; 
    	}
    
    	for(int i=N; i>1;i--) {
    		info[i] = info[i-1];
    	}
    	info[1] =new Robot(false);
    	
    }
    static void move_robot() {
    	info[N] = new Robot(false); //내리기
    	for(int i=N-1;i>0 ; i--) {
    		if(info[i].check) {
    			int move_position =  i+1;
    			if(!info[move_position].check && data[move_position]>=1) {
    				info[move_position] = info[i];
    				info[i] =new Robot(false);
    				data[move_position]-=1;
    				if(data[move_position]==0) {
    					k_num+=1;
    				}
    			}
    		}
    	}
    }
    static void add_robot() {
    	if(data[1] != 0) {
    		info[1] =new Robot(true);
    		data[1]-=1;
    		if(data[1]==0) {
    			k_num+=1;
    		}
    	}
    }
    
}
