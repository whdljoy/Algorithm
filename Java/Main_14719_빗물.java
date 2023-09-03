import java.util.*;
import java.io.*;
public class Main_14719_빗물 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int H;
    static int W;
    static int [] block;
    static int [][] water;
    public static void main(String[] args) throws Exception{
        input();
        make_block();
        remove_water();
        cal_water();
    }
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        block = new int[W];
        for(int i=0;i<W;i++){
            block[i] =Integer.parseInt(st.nextToken());
        }
        water = new int [H][W];
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                water [y][x] =2;  //물
            }
        }
    }
    static void make_block(){
        for(int x=0;x<W; x++){
            int cur =block[x];
            for(int y=0;y<cur;y++){
                water[y][x] = 1; // 블록 만들기
            }
        }
    }
    static void remove_water(){
        for(int y=H-1; y>-1; y--){
            for(int x=0;x<W;x++){
                int cx= x-1;
                if(water[y][x] !=1){
                    if(!in_range(cx,y) || water[y][cx] ==0){
                        water[y][x] =0;
                    }
                }

            }
        }
        for(int y=H-1; y>-1; y--){
            for(int x=W-1;x>-1;x--){
                int cx= x+1;
                if(water[y][x] !=1){
                    if(!in_range(cx,y) || water[y][cx] ==0){
                        water[y][x] =0;
                    }
                }

            }
        }
    }
    static void cal_water(){
        int ans=0;
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                if (water [y][x] ==2){
                    ans+=1;
                }
            }
        }
        System.out.println(ans);
    }
    static boolean in_range(int x,int y){
        return 0<= x && x<W && 0<=y &&y<H;
    }

}
