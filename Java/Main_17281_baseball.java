import java.util.*;
import java.io.*;
public class Main_17281_baseball {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int inning=0;
    static int [][] info;
    static int [] perm;
    static boolean [] check;
    static int ans=0;
    public static void main(String[] args) throws Exception {
        input();
        dfs(1);
        System.out.println(ans);
    }

    static void input() throws Exception{
        inning = Integer.parseInt(br.readLine());

        info = new int[inning+1][10];  // 이닝정보
        StringTokenizer st;
        for(int i=1; i<=inning; i++){
            st = new StringTokenizer(br.readLine());
            for(int a=1;a<=9;a++ ){
                info[i][a] = Integer.parseInt(st.nextToken());
            }
        }

        perm = new int [9];
        check = new boolean [10];
    }
    static void dfs(int idx){
        if(idx ==  9){
            cal();
            return;
        }
        for(int i=2;i<=9;i++){
            if(!check[i]){
                check[i]= true;
                perm[idx] =i;
                dfs(idx+1);
                check[i]=false;
            }

        }
    }
    static void cal(){
        int [][] status= new int [inning+1][10];
        for(int i=1; i<=inning; i++){
            for(int a=1;a<=3;a++ ){
                status[i][a] = info[i][perm[a]];
            }
            status[i][4] =info[i][1];
            for(int a=5;a<=9;a++ ){
                status[i][a] = info[i][perm[a-1]];
            }
        }
        int start =0;
        int current_inning =1;
        int score =0;
        int [] inning_status= new int [5];
        int out =0;
        while(current_inning <= inning){
            start+=1;
            if(start==10){
                start=1;
            }
            switch (status[current_inning][start]){
                case 0:
                    out +=1;
                    if (out == 3){
                        current_inning += 1;
                        out = 0;  //아웃 초기화
                        for(int i=0;i<5;i++){
                            inning_status[i]=0;  //주자 초기화
                        }
                    }
                    break;
                case 1:
                    for(int i=4;i>1;i--){
                        inning_status[i] = inning_status[i-1];  //주자 한칸씩 앞으로
                    }
                    inning_status[1]=1;  ///타자 1루로
                    score += inning_status[4];  //홈으로 들어온 주자
                    inning_status[4]=0;  //베이스 초기화
                    break;
                case 2:
                    for(int i=2;i<=3;i++){
                        score += inning_status[i];  //2,3 루 주자 들어옴
                        inning_status[i] =0;
                    }
                    inning_status[3] = inning_status[1];  //1루주자 3루
                    inning_status[1]=0;
                    inning_status[2] = 1;  //타자 2루
                    break;
                case 3:
                    for(int i=1; i<=3;i++){
                        score += inning_status[i];  //모든 주자 들어옴
                        inning_status[i] = 0;
                    }
                    inning_status[3] = 1;  //타자 3루
                    break;
                case 4:
                    score += 1; // 타자 점수
                    for(int i=1; i<=3;i++){
                        score += inning_status[i];  //주자가 있으면 더해주고
                        inning_status[i] = 0;  // 모든 주자 초기화
                    }
                    break;
            }
        }

        ans = Math.max(ans,score);
    }

}
