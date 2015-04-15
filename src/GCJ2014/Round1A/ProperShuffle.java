package GCJ2014.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-14.
 */
public class ProperShuffle {
    final static String PROBLEM_NAME = "C-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    ArrayList<LinkedList<Integer>> neighborhoods;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        int[] a = new int[N];
        for(int i = 0; i < N; i++){
            a[i] = sc.nextInt();
        }

        if(getScore(a) < (472+500)/2){
            pw.println("BAD");
        }else{
            pw.println("GOOD");
        }
    }

    public static int getScore(int[] a){
        int score = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] <= i){
                score ++;
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new ProperShuffle().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
