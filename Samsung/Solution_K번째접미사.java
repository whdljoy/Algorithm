import java.util.*;
import java.io.*;
public class Solution_K번째접미사 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int K;
    static String word;
    static class Node {
        Map <Character, Node> child = new HashMap<>();
        boolean last;
        Map <Character, Node> getChild(){
            return this.child;
        }
        void setLast (boolean last){
            this.last =last;
        }
    }

    static class Trie {
        Node rootNode;
        Trie(){
            rootNode = new Node();
        }
        void insert(String wrd){
            Node thisNode = this.rootNode;
            for(int i=0;i <wrd.length();i++){
                thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i),c-> new Node());
            }
        }
        boolean contains(String wrd){
            Node thisNode = this.rootNode;
            for(int i=0; i< wrd.length();i++){
                char cur = word.charAt(i);
                Node node = thisNode.getChild().get(cur);
                if(node == null) return false;

                thisNode = node;

            }
            return thisNode.last;
        }
    }
    public static void main(String[] args) throws Exception{
        run();
    }
    static void run() throws Exception {
        TC = Integer.parseInt(br.readLine());
        for(int i=1;i<=TC;i++) {
            sb.append("#").append(i).append(" ");
            input();
        }
        System.out.print(sb);
    }
    static void input() throws Exception{
        K = Integer.parseInt(br.readLine());
        word = br.readLine();

    }
}
