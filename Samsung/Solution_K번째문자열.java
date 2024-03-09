import java.util.*;
import java.io.*;


/**
 *
 *
 *  LCP  로도 풀수있음
 */
public class Solution_K번째문자열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC=10;
    static int K;
    static String word;


    static class Node {
        HashMap <Character, Node> childNode = new HashMap<>();
        boolean isLast;

        Map <Character, Node> getChild(){
            return this.childNode;
        }
    }

    static class Trie{
        Node rootNode;

        Trie(){
            rootNode = new Node();
        }

        void insert(String word){
            Node thisNode = this.rootNode;
            for(int i=word.length()-1; i>-1;i--){
                thisNode =thisNode.getChild().computeIfAbsent(word.charAt(i),c-> new Node());
            }

        }

        String [] contains(String word){
            Node thisNode = this.rootNode;
            String [] save = new String[word.length()];
            String tmp = "";
            for(int i=word.length()-1;i>-1;i--) {
                char cur = word.charAt(i);
                Node node = thisNode.getChild().get(cur);
                if (node == null) return save;
                tmp = cur+tmp;
                save[i] = tmp;
                thisNode = node;
            }
            return save;
        }
    }
    public static void main(String[] args) throws  Exception{
        run();
    }
    static  void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++){
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.println(sb);
    }
    static void input() throws Exception{
        K = Integer.parseInt(br.readLine());
        word = br.readLine();
        String [] answer = new String[word.length()];
        Trie current = new Trie();
        current.insert(word);
        answer = current.contains(word);
        HashSet<String> total =new HashSet<>();
        for(int i=0; i< answer.length;i++){
            System.out.println(answer[i]);
//            String cur = "";
//            for(int j=0;j<answer[i].length();j++){
//                cur += answer[i].charAt(j);
//                total.add(cur);
//            }
        }
        answer = (String[]) total.toArray(new String[0]);
        Arrays.sort(answer);
        if(K >= total.size()){
            sb.append("none").append("\n");
        }else{
            sb.append(answer[K-1]).append("\n");
        }

    }
}
