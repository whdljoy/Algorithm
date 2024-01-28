import java.util.*;
import java.io.*;
public class Solution_K번째접미사 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int TC =10;
    static int K;
    static String word;

    static Trie current;
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
            for(int i=wrd.length()-1;i >-1;i--){
                thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i),c-> new Node());
            }
        }
        String [] contains(String wrd){
            Node thisNode = this.rootNode;
            String [] ans = new String[wrd.length()];
            String tmp = "";
            for(int i=wrd.length()-1; i>-1;i--){
                char cur = word.charAt(i);
                tmp = cur +tmp;
                ans [i]=tmp;
                Node node = thisNode.getChild().get(cur);
                if(node == null) return ans;
                thisNode = node;


            }

            return ans;
        }
        void delete(String word){
            delete(this.rootNode,word,0);
        }
        private void delete(Node thisNode ,String word, int index){
            char character = word.charAt(index);
            if(!thisNode.getChild().containsKey(character)) return;

            Node childNode = thisNode.getChild().get(character);
            index++;
            if(index == word.length()){
                if(!childNode.last) return;

               childNode.setLast(false);

                if(childNode.getChild().isEmpty()) {
                    thisNode.getChild().remove(character);
                }
            }else{
                delete(childNode,word,index);
                if(!childNode.last && childNode.getChild().isEmpty()){
                    thisNode.getChild().remove(character);
                }
            }
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
        current = new Trie();
        current.insert(word);
        String [] answer = current.contains(word);
        Arrays.sort(answer);

        sb.append(answer[K-1]).append("\n");
    }
}
