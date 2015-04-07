package GCJ2014.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-7.
 */
public class MagicTrick {
    final static String PROBLEM_NAME = "A-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int row1 = sc.nextInt();
        int[][] a = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j< 4; j++){
                a[i][j] = sc.nextInt();
            }
        }
        HashSet<Integer> hs = new HashSet<Integer>();

        for(int i = 0; i < 4; i++){
            hs.add(a[row1-1][i]);
        }

        int row2 = sc.nextInt();
        int[][] b = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                b[i][j] = sc.nextInt();
            }
        }
        int count = 0;
        int result = 0;
        for(int i = 0; i < 4; i ++){
            if(hs.contains(b[row2-1][i])){
                count++;
                result = b[row2-1][i];
            }
        }

        if(count == 1){
            pw.println(result);
        }else if(count == 0){
            pw.println("Volunteer cheated!");
        }else{
            pw.println("Bad magician!");
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new MagicTrick().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
