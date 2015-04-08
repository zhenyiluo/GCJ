package GCJ2014.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-7.
 */
public class DeceitfulWar {
    final static String PROBLEM_NAME = "D-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        double[] a = new double[N];
        double[] b = new double[N];
        TreeSet<Double> tsa = new TreeSet<Double>();
        TreeSet<Double> tsb = new TreeSet<Double>();
        for(int i = 0; i < N; i ++){
            a[i] = sc.nextDouble();
            tsa.add(a[i]);
        }
        for(int i = 0; i < N; i++){
            b[i] = sc.nextDouble();
            tsb.add(b[i]);
        }
        int retA = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        for(int i = 0; i < N; i++){
            if(tsa.ceiling(b[i]) != null){
                tsa.remove(tsa.ceiling(b[i]));
                retA++;
            }
        }
        for(int i = 0; i < N; i++){
            if(tsb.ceiling(a[i]) != null){
                tsb.remove(tsb.ceiling(a[i]));
            }
        }
        pw.println(retA + " " + tsb.size());
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new DeceitfulWar().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
