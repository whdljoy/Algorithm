import java.io.*;
import java.util.*;

public class Main_1152_단어의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        System.out.println(st.countTokens());
    }
}
