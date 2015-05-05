package GCJ2015.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-5-2.
 */
public class A {
    final static String PROBLEM_NAME = "A-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        long n = sc.nextLong();
        long ans = 0;
        for(long base  = 10; base <= n; base = base * 10){
            ans += calc(base-1) + 1;
        }
        ans += calc(n);
        pw.println(ans);
    }

    public long calc(long n){
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder(s);
        int part1 = Integer.valueOf(sb.substring(sb.length() /2));
        sb.reverse();
        int part2;
        String tmp = sb.substring((sb.length() + 1)/2);
        if(tmp.equals("")){
            part2 = 0;
        }else{
            part2  = Integer.valueOf(tmp);
        }
        if(part1 == 0 && part2 ==1){
            return 0;
        }
        if(part1 == 0){
            return calc(n-1) + 1;
        }

        return part1 + (part2 <= 1 ? 0 : part2);
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
