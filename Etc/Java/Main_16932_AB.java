import java.util.*;
import java.io.*;



/**
 * 자료형 잘 보기 계속 int로 넣어서 틀렸다.
 *
 * + Integer, Long type 의 경우 equals 를 써야한다.
 *
 * int ,long 은  ==로 비교해도된다.
 */
public class Main_16932_AB {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long A;
    static long B;
    static long ans;
    public static void main(String[] args) throws Exception {
        input();
        ans=0;
        bfs();
        if(ans == 0){
            ans =-1;
        }
        System.out.println(ans);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B =  Integer.parseInt(st.nextToken());

    }

    static void bfs(){
        Queue <long[]> q = new ArrayDeque<>();
        q.add(new long[]{A,1});
        while(!q.isEmpty()){
            long[] cur_arr = q.poll();
            long cal = cur_arr[1];
            long first = cur_arr[0]*2;
            long second = cur_arr[0]*10+1;
            if(cur_arr[0] == B){
                ans = cal;
                break;
            }
            if(first <=B ){
                q.add(new long []{first,cal+1});
            }
            if(second <=B){
                q.add(new long []{second,cal+1});
            }
        }
    }
}
