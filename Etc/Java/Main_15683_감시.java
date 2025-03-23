import java.util.*;
import java.io.*;
public class Main_15683_감시 {
    static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int ans =Integer.MAX_VALUE;
    static int [][] origin;
    static int [] dx= {-1,0,1,0,-1,0,1,0};  // 0 1 2 3  좌 상 우 하  좌 상 우 하
    static int [] dy= {0,-1,0,1,0,-1,0,1};
    static List <Integer[]> cctv;
    public static void main(String[] args) throws Exception{
        input();
        search(0,origin);
        System.out.println(ans);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new int [N][M];
        cctv = new ArrayList<>();
        for(int y=0; y<N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                origin[y][x] = Integer.parseInt(st.nextToken());
                if(origin[y][x] !=0  && origin[y][x] != 6){
                    cctv.add(new Integer [] {y,x,origin[y][x]});
                }
            }
        }

    }
    static void get_ans(int [][] status){
        int cur =0;
        for(int y=0;y<N;y++){
            for(int x=0; x<M; x++){
                if(status[y][x] ==0){
                    cur+=1;
                }
            }
        }
        ans = Math.min(cur,ans);
    }

    static void search(int idx, int [][] status){
        if(idx ==cctv.size()){
            get_ans(status);
            return;
        }
        for(int dir=0;dir<4; dir++) {
           search(idx+1,change_status(cctv.get(idx),dir,status));
        }


    }
    static int [][]change_status(Integer[] cctv_info, int direction,int [][] cp){
        int [][] status = new int[N][M];
        for(int y=0;y<N;y++){
            for(int x=0; x<M; x++){
                status[y][x] = cp[y][x];
            }
        }
        int x = cctv_info[1];
        int y = cctv_info[0];
        int cctv_num = cctv_info[2];
        if (cctv_num ==1){
            switch (direction){
                case 0:
                    //우
                    int cx =x+1;
                    while(true){
                        if(!in_range(cx,y)){
                            break;
                        }

                        if(status[y][cx] == 0){
                            status[y][cx] = -1;
                        }else if (status[y][cx] == 6){
                            break;
                        }
                        cx+=1;

                    }
                    break;
                case 1: // 아래
                    int cy =y+1;
                    while(true){
                        if(!in_range(x,cy)){
                            break;
                        }

                        if(status[cy][x] == 0){
                            status[cy][x] = -1;
                        }else if (status[cy][x] == 6){
                            break;
                        }
                        cy+=1;
                    }
                    break;
                case 2:  //좌
                    int left_x =x-1;
                    while(true){
                        if(!in_range(left_x,y)){
                            break;
                        }

                        if(status[y][left_x] == 0){
                            status[y][left_x] = -1;
                        }else if (status[y][left_x] == 6){
                            break;
                        }
                        left_x-=1;

                    }
                    break;
                case 3: //위
                    int up_y =y-1;
                    while(true){
                        if(!in_range(x,up_y)){
                            break;
                        }

                        if(status[up_y][x] == 0){
                            status[up_y][x] = -1;
                        }else if (status[up_y][x] == 6){
                            break;
                        }
                        up_y-=1;

                    }
                    break;
            }
        }else if (cctv_num ==2){
            switch (direction){
                //좌 우 똑같음
                case 0:
                case 2:
                    for(int i=0; i<4;i=i+2){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                // 상 하 똑같음
                case 1:
                case 3:
                    for(int i=1; i<4;i=i+2){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
            }

        }
        else if (cctv_num ==3){
            switch (direction){
                case 0:  //상우
                    for(int i=1; i<3;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 1:  //우하
                    for(int i=2; i<4;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 2:  // 하좌
                    for(int i=3; i<5;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 3: // 좌상
                    for(int i=0; i<2;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
            }

        }
        else if (cctv_num ==4){
            switch (direction){
                case 0:  //좌 상 우
                    for(int i=0; i<3;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 1: //상 우 하
                    for(int i=1; i<4;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 2: // 우 하 좌
                    for(int i=2; i<5;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
                case 3:  // 상 좌 하
                    for(int i=3; i<6;i++){
                        int cx = x;
                        int cy = y;
                        while(true){
                            cx+=dx[i];
                            cy+=dy[i];
                            if(!in_range(cx,cy)){
                                break;
                            }
                            if(status[cy][cx] == 0){
                                status[cy][cx] = -1;
                            }else if (status[cy][cx] == 6){
                                break;
                            }
                        }
                    }
                    break;
            }

        } else if (cctv_num ==5){
            for(int i=0; i<4;i++){
                int cx = x;
                int cy = y;
                while(true){
                    cx+=dx[i];
                    cy+=dy[i];
                    if(!in_range(cx,cy)){
                        break;
                    }
                    if(status[cy][cx] == 0){
                        status[cy][cx] = -1;
                    }else if (status[cy][cx] == 6){
                        break;
                    }
                }
            }

        }
        return status;
    }
    static boolean in_range(int x, int y){
        return 0<=x && x<M && 0<=y && y<N;
    }
 }
