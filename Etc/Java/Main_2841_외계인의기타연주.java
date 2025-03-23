import java.util.*;
import java.io.*;
public class Main_2841_외계인의기타연주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,P;
    static int ans=0;
    static Stack [] info;
    static boolean [][] status;
    public static void main(String[] args) throws Exception{
        input();
        System.out.println(ans);
    }
    static void input () throws Exception{ // 6개의 줄 P 개의 프렛 P 300000 N 개의 음
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        info= new Stack[7];
        status = new boolean[7][P+1];
        for(int y=0;y<=6;y++){
            info[y] = new Stack<Integer>();
        }
        for(int y=0; y<=6;y++){
            for(int x=0;x<=P;x++){
                status[y][x] =false;
            }
        }
        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int li = Integer.parseInt(st.nextToken());
            int lp = Integer.parseInt(st.nextToken());
            if(!status[li][lp]){
                ans +=1;
                status [li][lp] = true;
                if(!info[li].isEmpty() && lp < (int)info[li].peek()){
                    while(!info[li].isEmpty()){
                        int cur =(int)info[li].peek();
                        if(lp >= cur){
                            break;
                        }else{
                            status[li][cur] = false;
                            info[li].pop();
                            ans+=1;
                        }
                    }
                }
                info[li].add(lp);
            }else{
                if(!info[li].isEmpty() && lp < (int)info[li].peek()){
                    while(!info[li].isEmpty()){
                        int cur =(int)info[li].peek();
                        if(lp >= cur){
                            break;
                        }else{
                            status[li][cur] = false;
                            info[li].pop();
                            ans+=1;
                        }
                    }
                }
            }
        }
    }
}
