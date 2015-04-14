package GCJ2015.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-10.
 */
public class StandingOvation {
    final static String PROBLEM_NAME = "A-large";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int sMax = sc.nextInt();
        String s = sc.next();
        int len = s.length();
        int ret = 0;
        int sum = 0;
        for(int i = 0; i < len; i++){
            if(i > sum){
                ret ++;
                sum ++;
            }
            sum += s.charAt(i)- '0';
        }
        pw.println(ret);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new StandingOvation().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
