package GCJ2014.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-7.
 */
public class CookieClickerAlpha {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        double c = sc.nextDouble();
        double f = sc.nextDouble();
        double x = sc.nextDouble();
        double prev = x/2;
        double cur = c/2 + x/(2+f);
        int count = 1;
        while(prev > cur){
            prev = cur;
            cur -= x/(2 + f * count);
            cur += c/(2 + f * count);
            cur += x/(2 + f * (count + 1));
            count ++;
        }
        DecimalFormat df = new DecimalFormat("#.0000000");
        pw.println(df.format(prev));
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new CookieClickerAlpha().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
