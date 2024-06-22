import java.util.ArrayList;
import java.util.Scanner;

public class Main_15649_N과M_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer> sel = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            sel.add(i);
        }

        ArrayList<ArrayList<Integer>> result = genPermutations(sel, m);
        for (ArrayList<Integer> i : result) {
            for (int a : i) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> genPermutations(ArrayList<Integer> arr, int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (n == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        for (int i = 0; i < arr.size(); i++) {
            int elem = arr.get(i);
            ArrayList<Integer> remaining = new ArrayList<>(arr);
            remaining.remove(i);
            for (ArrayList<Integer> P : genPermutations(remaining, n - 1)) {
                ArrayList<Integer> temp = new ArrayList<>(P);
                temp.add(0, elem);
                result.add(temp);
            }
        }
        return result;
    }
}
