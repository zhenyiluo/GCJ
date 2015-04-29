package GCJ2012.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-28.
 */
public class A {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int A = sc.nextInt();
        int B = sc.nextInt();
        double[] p = new double[A];
        for(int i = 0; i < A; i++){
            p[i] = sc.nextDouble();
        }
        double best = B + 2;
        double x = 1;
        for(int i = 0; i < A; i++){
            x *= p[i];
            best = Math.min(best, (B - i) + (A - i - 1) + (B + 1) * (1 - x));
        }
        pw.println(best);
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new A().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
