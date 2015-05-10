package GCJ2015.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-17.
 */
public class C {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;
    final static double EPS = Math.pow(10, -15);
    public void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for(int i = 0; i < n; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        double[] a = new double[2 * n];
        for(int i = 0; i < n ; i++){
            int an = 0;
            int ans = n-1;
            for(int j = 0; j < n ; j++){
                if(j != i){
                    a[an++] = Math.atan2(y[j] - y[i], x[j] - x[i]);
                }
            }
            Arrays.sort(a, 0, an);
            for(int j = 0; j < an; j++){
                a[j+an] = a[j] + 2 * Math.PI;
            }
            int l = 0;
            int r = 0;
            for(int j = 0; j < an ; j++){
                while(l+1 < 2 * an && a[l] - a[j] < EPS){
                    l++;
                }
                while(r < 2 * an && a[r] - a[j] < Math.PI - EPS){
                    r++;
                }
                ans = Math.min(ans, r - l);
            }
            pw.println(ans);
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.println("Case #" + (caseNum+1) + ": ");
            new C().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
