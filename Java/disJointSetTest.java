import java.util.*;
import java.io.*;
public class disJointSetTest {
    static int N;
    static int [] parents;

    static void make(){
        parents = new int[N];
        for(int i=0; i<N; i++){
            parents[i] = i;
        }
    }

    static int  find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);

    }
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot ==bRoot) return false;
        parents[bRoot] =aRoot;
        return true;
    }

    public static void main(String[] args) {
        N=5;
        make();
        System.out.println(Arrays.toString(parents));
        System.out.println(union(0,1));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(1,2));
    }

}
