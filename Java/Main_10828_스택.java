import java.util.*;
import java.io.*;
public class Main_10828_스택 {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            switch (st.nextToken()) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    stack.push(x);
                    break;
                case "pop":
                    if (stack.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(stack.pop() + "\n");
                    }
                    break;
                case "size":
                    sb.append(stack.size() + "\n");
                    break;
                case "empty":
                    if (stack.isEmpty()) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "top":
                    if (stack.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(stack.peek() + "\n");
                    }
                    break;

            }
        }
        System.out.print(sb);

    }
}
