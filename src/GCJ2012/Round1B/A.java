package GCJ2012.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-30.
 */
public class A {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw) {
        int n = sc.nextInt();
        int[] a = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }

        for (int i = 0; i < n; i++) {
            double l = 0;
            double h = 1;
            double mid = 0;
            while (h - l > 0.0000000001) {
                mid = (l + h) / 2;
                if (check(i, mid, sum, a, n)) {
                    l = mid;
                } else {
                    h = mid;
                }
            }
            pw.printf("%.6f", mid * 100);
            pw.print(" ");
        }
        pw.println();
    }

    public boolean check(int i, double p, int sum, int[] a, int n) {
        double sumP = 0;
        double v = a[i] + p * sum;
        for (int k = 0; k < n; k++) {
            if (k == i) {
                continue;
            }
            sumP += Math.max(0, (v - a[k]) / sum);
        }
        return sumP <= 1 - p;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new A().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
